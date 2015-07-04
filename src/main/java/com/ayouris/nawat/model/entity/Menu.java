package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseParam;

@Entity
@Table(name = "menu")
public class Menu extends BaseParam implements Serializable {
	private static final long serialVersionUID = 2509572482258134302L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La clé du libelle permettant de récuperer la valeur à partir du bundle
	 */
	@NotNull
	private String libelleKey;

	/**
	 * Le lien url de redirection après click sur le menu
	 */
	@NotNull
	private String lien;

	/**
	 * la classe de l'icone à mettre dans l'attribut class
	 */
	@NotNull
	private String iconClass;

	/**
	 * Le role nécessaire pour accèder au menu
	 */
	@NotNull
	private String role;

	/**
	 * Le type du menu
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private MenuType menuType;

	/**
	 * Le menu parent de ce menu
	 */
	@ManyToOne
	@JoinColumn(name = "parentMenu_id")
	private Menu parentMenu;

	@ManyToOne
	@JoinColumn(name = "module_id")
	private Module module;

	/**
	 * La liste des menus fils de ce menu
	 */
	@OneToMany(mappedBy = "parentMenu", fetch = FetchType.EAGER)
	private List<Menu> menuChildren = new ArrayList<Menu>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum MenuType {
		MENU("cst.menu.typeMenu"), RAPPORT("cst.menu.typeRapport");

		private final String key;

		MenuType(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Menu() {
		super();
	}

	public Menu(String id) {
		super(id);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return libelleKey;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public String getLibelleKey() {
		return libelleKey;
	}

	public void setLibelleKey(String libelleKey) {
		this.libelleKey = libelleKey;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getChildrenMenus() {
		return menuChildren;
	}

	public void setChildrenMenus(List<Menu> menuChildrenList) {
		this.menuChildren = menuChildrenList;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}
