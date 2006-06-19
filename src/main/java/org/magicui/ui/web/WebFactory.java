/*
 * Magic UI
 * Copyright (C) 2006  Filipe Tavares, Belmiro Sotto-Mayor
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package org.magicui.ui.web;

import org.magicui.ui.ActionItem;
import org.magicui.ui.Component;
import org.magicui.ui.View;
import org.magicui.ui.factory.AbstractComponentFactory;

/**
 * WebFactory is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class WebFactory extends AbstractComponentFactory<WebTag> {

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createView()
     */
    public Component<? extends WebTag> createView() {
        return new WebView();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createLabel()
     */
    public Component<? extends WebTag> createLabel() {
        return new WebLabel();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenu(java.lang.Object, java.lang.String)
     */
    public Object createMenu(Object parentMenu, String name) {
        final WebValueTag<WebTagContainer> menu = new WebValueTag<WebTagContainer>("<menu label=\""+name+"\"><menupopup>%v</menupopup></menu>");
        menu.setValue(new WebTagContainer());
        if (parentMenu != null) {
            ((WebValueTag<WebTagContainer>) parentMenu).getValue().add(menu);
        }
        return menu;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenuItem(java.lang.Object, org.magicui.ui.ActionItem, org.magicui.ui.View)
     */
    public Object createMenuItem(Object menu, ActionItem item, View<? extends WebTag> view) {
        final WebValueTag<WebTagContainer> menuItem = new WebValueTag<WebTagContainer>("<menuitem label=\""+item.getText()+"\" />");
        ((WebValueTag<WebTagContainer>) menu).getValue().add(menuItem);
        return menuItem;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createPlaceholder()
     */
    public Component<? extends WebTag> createPlaceholder() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createWindow(java.lang.String, org.magicui.ui.View, boolean)
     */
    public Object createWindow(String title, View<? extends WebTag> content,
            final boolean isMainWindow) {
        if (content.getTop() != null) {
            final StringBuffer top = new StringBuffer("<toolbar>");
            for (ActionItem item : content.getTop()) {
                top.append("<toolbarbutton label=\"");
                top.append(item.getText());
                top.append("\"");
                if (item.getIcon() != null) {
                    top.append(" image=\"");
                    top.append(item.getIcon());
                    top.append("\"");
                }
                top.append("/>");
            }
            top.append("</toolbar>");
            ((WebView) content).getComponent().insert(new WebValueTag<String>(top.toString()));
        }
        if (content.getMenus() != null) {
            final WebValueTag<WebTagContainer> menuBar = new WebValueTag<WebTagContainer>("<menubar id=\"menubar\">%v</menubar>");
            menuBar.setValue(new WebTagContainer());
            for (Object menu : content.getMenus()) {
                menuBar.getValue().add((WebTag) menu);
            }
            ((WebView) content).getComponent().insert(new WebValueTag<String>(menuBar.toString()));
        }
        return new WebWindow(title, content);
    }

}
