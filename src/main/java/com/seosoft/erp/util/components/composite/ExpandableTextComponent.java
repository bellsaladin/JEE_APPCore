package com.seosoft.erp.util.components.composite;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent("expandableTextComponent")
public class ExpandableTextComponent extends UINamingContainer
{
    boolean expanded;

    public boolean isExpanded()
    {
        return expanded;
    }

    public void toggleExpanded()
    {
        expanded = !expanded;
    }
}
