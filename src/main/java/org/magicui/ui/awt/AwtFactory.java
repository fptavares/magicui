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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

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
     * @see org.magicui.ui.factory.ComponentFactory#createView()
     */
    public org.magicui.ui.Component<? extends Component> createView() {
        return new AwtView();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createLabel()
     */
    public org.magicui.ui.Component<? extends Component> createLabel() {
        return new AwtLabel();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenu(java.lang.Object, java.lang.String)
     */
    public Object createMenu(Object parentMenu, String name) {
        final Menu menu = new Menu(name);
        if (parentMenu != null) {
            ((Menu) parentMenu).add(menu);
        }
        return menu;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createMenuItem(java.lang.Object, org.magicui.ui.ActionItem, org.magicui.ui.View)
     */
    public Object createMenuItem(Object menu, final ActionItem item,
            final View<? extends Component> view) {
        final MenuItem menuItem = new MenuItem(item.getText());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                item.getAction().act(view.getState());
            }
        });
        ((Menu) menu).add(menuItem);
        return menuItem;
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createPlaceholder()
     */
    public org.magicui.ui.Component<? extends Component> createPlaceholder() {
        return new AwtPlaceholder();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createWindow(java.lang.String, org.magicui.ui.View, boolean)
     */
    public Object createWindow(String title, View<? extends Component> content,
            final boolean isMainWindow) {
        final Frame frame = new Frame();
        frame.setLayout(new BorderLayout());
        if (content.getTop() != null) {
            frame.add(convert(content, content.getTop()), BorderLayout.NORTH);
        } 
        if (content.getBottom() != null) {
            frame.add(convert(content, content.getBottom()), BorderLayout.SOUTH);
        } 
        if (content.getLeft() != null) {
            frame.add(convert(content, content.getLeft()), BorderLayout.WEST);
        } 
        if (content.getRight() != null) {
            frame.add(convert(content, content.getRight()), BorderLayout.EAST);
        } 
        if (content.getMenus() != null) {
            final MenuBar menuBar = new MenuBar();
            for (Object menu : content.getMenus()) {
                menuBar.add((Menu) menu);
            }
            frame.setMenuBar(menuBar);
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isMainWindow) {
                    System.exit(0);
                } else {
                    frame.setVisible(false);
                }
            }
        });
        frame.setTitle(title);
        frame.add(content.getComponent(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    /**
     * @param content 
     * @param top
     * @return 
     * TODO: might give sync problems because of the "final" keyword...
     */
    private Component convert(final View<? extends Component> content,
            Collection<ActionItem> items) {
        final Panel bar = new Panel();
        for (final ActionItem item : items) {
            final Button button = new Button();
            button.setLabel(item.getText());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    item.getAction().act(content.getState());
                }
            });
            bar.add(button);
        }
        return bar;
    }

}
