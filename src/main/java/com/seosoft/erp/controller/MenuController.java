package com.seosoft.erp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.model.entity.Menu;
import com.seosoft.erp.model.entity.Module;
import com.seosoft.erp.model.entity.Menu.MenuType;
import com.seosoft.erp.service.parametrage.MenuService;
import com.seosoft.erp.service.parametrage.ModuleService;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;
import com.seosoft.erp.util.utility.Utils;

@Named
@SpringSessionScoped
public class MenuController extends BaseController implements Serializable {
	private static final long serialVersionUID = 2696419637527507226L;

	@Autowired
	private MenuService menuService;

	@Autowired
	private ModuleService moduleService;

	private List<Module> modules;
	private String moduleSelectedId;
	private int moduleSelectedIndex;

	private MenuModel menuModel;
	private List<Menu> menus;
	private MenuType menuType;
	private int menuTypeSelectedIndex;

	private Menu menuSelected;
	private List<Menu> menusFiltred;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	@PostConstruct
	public void initialize() {
		menuType = MenuType.MENU;
		loadModulesData();
		loadMenusData();
		buildMenu();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	private void loadModulesData() {
		List<Module> allModules = moduleService.findAll();
		modules = new ArrayList<Module>();

		for (Module module : allModules) {
			// tester si l'utilisateur dispose du role nécessaire
			if (hasRole(module.getRole())) {
				if (moduleSelectedId == null) {
					moduleSelectedId = module.getId();
				}
				modules.add(module);
			}
		}
	}

	private void loadMenusData() {
		menus = menuService.findByParentMenuIsNull();
	}

	private void buildMenu() {

		// création du model
		menuModel = new DefaultMenuModel();

		for (Menu menu : menus) {

			if (moduleSelectedId.equals(menu.getModule().getId()) && menu.getMenuType() == menuType) {

				if (hasRole(menu.getRole()) && menu.getChildrenMenus().isEmpty()) {
					DefaultMenuItem menuItem = new DefaultMenuItem(Utils.getBundleByKey(menu.getLibelleKey()));
					menuItem.setIcon(menu.getIconClass());
					menuItem.setUrl(menu.getLien());
					menuModel.addElement(menuItem);
				} else {
					menuModel.addElement(getSubMenu(menu));
				}

			}
		}
	}

	private DefaultSubMenu getSubMenu(Menu menu) {
		DefaultSubMenu subMenu = new DefaultSubMenu(Utils.getBundleByKey(menu.getLibelleKey()));
		subMenu.setIcon(menu.getIconClass());

		for (Menu childMenu : menu.getChildrenMenus()) {
			if (hasRole(childMenu.getRole())) {
				if (childMenu.getChildrenMenus().isEmpty()) {
					DefaultMenuItem menuItem = new DefaultMenuItem(Utils.getBundleByKey(childMenu.getLibelleKey()));
					menuItem.setIcon(childMenu.getIconClass());
					menuItem.setUrl(childMenu.getLien());
					subMenu.addElement(menuItem);
				} else {
					subMenu.addElement(getSubMenu(childMenu));
				}
			}
		}

		return subMenu;
	}

	private void findMenuBylibelleLike(String query, Menu menu, List<Menu> menusFiltred) {
		if (hasRole(menu.getRole())) {
			if (menu.getChildrenMenus().isEmpty()) {
				if (Utils.getBundleByKey(menu.getLibelleKey()).toLowerCase().contains(query.toLowerCase().trim())) {
					menusFiltred.add(menu);
				}
			} else {
				for (Menu childMenu : menu.getChildrenMenus()) {
					if (childMenu.getChildrenMenus().isEmpty()) {
						if (Utils.getBundleByKey(childMenu.getLibelleKey()).toLowerCase().contains(query.toLowerCase().trim())) {
							menusFiltred.add(childMenu);
						}
					} else {
						findMenuBylibelleLike(query, childMenu, menusFiltred);
					}
				}
			}
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//	
	public void onModuleMenuTabChanged(TabChangeEvent event) {
		menuSelected = null;
		String moduleTabId = event.getTab().getId();
		moduleSelectedIndex = ((TabView) event.getTab().getParent()).getActiveIndex();

		// on charge les menus juste si le module a été changé
		if (moduleTabId != moduleSelectedId) {
			moduleSelectedId = moduleTabId;
			buildMenu();
		}
	}

	public void onMenuTabChanged(TabChangeEvent event) {
		menuTypeSelectedIndex = ((TabView) event.getTab().getParent()).getActiveIndex();
		menuType = (menuTypeSelectedIndex == 1) ? MenuType.RAPPORT : MenuType.MENU;

		buildMenu();
	}

	public List<Menu> onCompleteMenuLibelle(String query) {
		menusFiltred = new ArrayList<Menu>();

		for (Menu menu : menus) {
			findMenuBylibelleLike(query, menu, menusFiltred);
		}

		return menusFiltred;
	}

	public void onMenuSelected(SelectEvent event) throws IOException {
		Menu menu = (Menu) event.getObject();

		moduleSelectedId = menu.getModule().getId();
		moduleSelectedIndex = modules.lastIndexOf(menu.getModule());
		menuType = menu.getMenuType();
		menuTypeSelectedIndex = (menuType == MenuType.MENU) ? 0 : 1;
		buildMenu();

		Faces.redirect(Faces.getRequestContextPath() + menu.getLien());

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public MenuModel getMenuModel() {
		return menuModel;
	}

	public List<Module> getModules() {
		return modules;
	}

	public int getModuleSelectedIndex() {
		return moduleSelectedIndex;
	}

	public void setModuleSelectedIndex(int moduleSelectedIndex) {
		this.moduleSelectedIndex = moduleSelectedIndex;
	}

	public int getMenuTypeSelectedIndex() {
		return menuTypeSelectedIndex;
	}

	public void setMenuTypeSelectedIndex(int menuTypeSelectedIndex) {
		this.menuTypeSelectedIndex = menuTypeSelectedIndex;
	}

	public Menu getMenuSelected() {
		return menuSelected;
	}

	public void setMenuSelected(Menu menuSelected) {
		this.menuSelected = menuSelected;
	}

	public List<Menu> getMenusFiltred() {
		return menusFiltred;
	}

	public void setMenusFiltred(List<Menu> menusFiltred) {
		this.menusFiltred = menusFiltred;
	}
}