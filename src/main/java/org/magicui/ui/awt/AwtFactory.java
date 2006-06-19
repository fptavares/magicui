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
package org.magicui.ui.awt;

import java.awt.Component;
import java.awt.Frame;

import org.magicui.ui.ActionItem;
import org.magicui.ui.View;
import org.magicui.ui.factory.AbstractComponentFactory;

/**
 * AwtFactory is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class AwtFactory extends AbstractComponentFactory<Component> {

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createFrame()
     */
    public org.magicui.ui.Component<? extends Component> createFrame() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createLabel()
     */
    public org.magicui.ui.Component<? extends Component> createLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenu(java.lang.Object, java.lang.String)
     */
    public Component createMenu(Component parentMenu, String name) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenuItem(java.lang.Object, org.magicui.ui.ActionItem, org.magicui.ui.View)
     */
    public Component createMenuItem(Component menu, ActionItem item, View<? extends Component> view) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createPlaceholder()
     */
    public org.magicui.ui.Component<? extends Component> createPlaceholder() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createWindow(java.lang.String, org.magicui.ui.View)
     */
    public Object createWindow(String title, View<? extends Component> content) {
        final Frame frame = new Frame();
        frame.setTitle(title);
        frame.add(content.getComponent());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

}
