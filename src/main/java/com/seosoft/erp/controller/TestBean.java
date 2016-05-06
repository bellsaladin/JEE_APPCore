package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.component.tree.TreeNode;
import org.omnifaces.util.Messages;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultTreeNode;

import com.seosoft.erp.service.parametrage.CycleService;
import com.seosoft.erp.service.parametrage.EcoleService;
import com.seosoft.erp.service.parametrage.UserService;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;

@Named
@SpringSessionScoped
public class TestBean extends BaseController implements Serializable {
	private static final long serialVersionUID = -5244331988481715247L;

	@Inject
	EcoleService ecoleService;

	@Inject
	UserService userService;

	@Inject
	CycleService cycleService;

	private Map<String, String> filterTypes;
	private List<String> selectedFilterTypes;
	private int menuType;
	private String anneeScolaire;
	private DashboardModel model;
	private Locale locale;
	private String theme;
	private boolean filterShowed;
	private String message;
	private String darkCouleur;
	private String lightCouleur;
	
    private DefaultTreeNode root;

	@PostConstruct
	public void initialize() {

		locale = getFacesContext().getViewRoot().getLocale();
		theme = "bluesky";

		filterTypes = new HashMap<String, String>();
		filterTypes.put("المسجلين هذه السنة", "1");
		filterTypes.put("غير المسجلين هذه السنة", "2");
		filterTypes.put("المغادرين المؤسسة ", "3");
		selectedFilterTypes = new ArrayList<>();
		selectedFilterTypes.add("1");
		menuType = 1;
		anneeScolaire = "2013/2014";

		darkCouleur = "#D2D7DF";
		lightCouleur = "#FAFAFA";
		
		
		// tree test
		
		root = new DefaultTreeNode("Root", null);
		DefaultTreeNode node0 = new DefaultTreeNode("Node 0", root);
		DefaultTreeNode node1 = new DefaultTreeNode("Node 1", root);
         
		DefaultTreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        DefaultTreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
         
        DefaultTreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode("Node 2"));
	}

	public void saveTest() {
		// Cycle cycle = new Cycle();
		//
		// cycle.setLibelle("Seosoft");
		// cycle.setNumeroAutorisation("Seosoft");
		// cycle.setActif(true);
		// cycle.setOrdre(1);
		// cycle.setDateAutorisation(new Date());

		// cycleService.testTransaction();
	}

	public void doFilterShowed() {
		filterShowed = !filterShowed;

	}

	public void doSomething(ActionEvent event) {
		//FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
		
		
		/*
		 * UIComponent component = event.getComponent(); TabView tabView = (TabView) component;
		 */
		// UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

		/*
		 * ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", getFacesContext().getViewRoot().getLocale());
		 * getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, null,
		 * bundle.getString("test.affectationClasseDetail")));
		 */

		Messages.addInfo(null, "error.login.user.password.not.correct");
		System.out.println("doSomething !!!");
		// FacesContext ctx = FacesContext.getCurrentInstance();
		//
		// ExternalContext extContext = ctx.getExternalContext();
		// String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/views/test.xhtml"));
		// try {
		//
		// extContext.redirect(url);
		// } catch (IOException ioe) {
		// throw new FacesException(ioe);
		//
		// }

		// try {
		// // simulate a long running request
		// Thread.sleep(1500);
		// System.out.println("tab changed");
		// } catch (Exception e) {
		// // ignore
		// }

	}

	@PreDestroy
	public void clean() {

	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public synchronized String getDirection() {
		if (getLanguage() == "ar") {
			return "rtl";
		} else {
			return "ltr";
		}
	}

	public boolean isArabic() {
		if (getLanguage() == "ar") {
			return true;
		} else {
			return false;
		}
	}

	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: "
				+ event.getSenderColumnIndex());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

	public Map<String, String> getFilterTypes() {
		return filterTypes;
	}

	public void setFilterTypes(Map<String, String> filterType) {
		this.filterTypes = filterType;
	}

	public List<String> getSelectedFilterTypes() {
		return selectedFilterTypes;
	}

	public void setSelectedFilterTypes(List<String> selectedFilterTypes) {
		this.selectedFilterTypes = selectedFilterTypes;
	}

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public String getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(String anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean isFilterShowed() {
		return filterShowed;
	}

	public void setFilterShowed(boolean filterShowed) {
		this.filterShowed = filterShowed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDarkCouleur() {
		return darkCouleur;
	}

	public void setDarkCouleur(String darkCouleur) {
		this.darkCouleur = darkCouleur;
	}

	public String getLightCouleur() {
		return lightCouleur;
	}

	public void setLightCouleur(String lightCouleur) {
		this.lightCouleur = lightCouleur;
	}
	
	public DefaultTreeNode getRoot(){
		return this.root;
	}
}
