package com.seosoft.erp.util.components;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.extensions.model.layout.LayoutOptions;

@ApplicationScoped
@ManagedBean(eager = true)
public class LayoutParams {

	private String options;

	@PostConstruct
	protected void initialize() {
		LayoutOptions layoutOptions = new LayoutOptions();

		// for all panes
		LayoutOptions panes = new LayoutOptions();
		// panes.addOption("resizable", true);
		// panes.addOption("closable", true);
		// panes.addOption("slidable", false);
		// panes.addOption("resizeWithWindow", false);
		// panes.addOption("resizeWhileDragging", true);
		layoutOptions.setPanesOptions(panes);

		// north pane
		LayoutOptions north = new LayoutOptions();
		north.addOption("resizable", false);
		north.addOption("closable", false);
		north.addOption("size", 80);
		north.addOption("spacing_open", 0);
		layoutOptions.setNorthOptions(north);

		// center pane
		LayoutOptions center = new LayoutOptions();
		center.addOption("resizable", false);
		center.addOption("closable", false);
		center.addOption("resizeWhileDragging", false);
		center.addOption("minWidth", 200);
		center.addOption("minHeight", 60);
		layoutOptions.setCenterOptions(center);

		// west pane
		LayoutOptions west = new LayoutOptions();
		west.addOption("size", 210);
		west.addOption("minSize", 180);
		west.addOption("maxSize", 500);
		layoutOptions.setWestOptions(west);

		// south pane
		LayoutOptions south = new LayoutOptions();
		south.addOption("resizable", false);
		south.addOption("closable", false);
		south.addOption("size", 30);
		south.addOption("spacing_open", 0);
		layoutOptions.setSouthOptions(south);

		// serialize options to JSON string (increase perf.)
		options = layoutOptions.toJson();
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}
