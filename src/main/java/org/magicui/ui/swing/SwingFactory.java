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
package org.magicui.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import org.magicui.ui.ActionItem;
import org.magicui.ui.Component;
import org.magicui.ui.View;
import org.magicui.ui.factory.AbstractComponentFactory;


/**
 * SwingFactory is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class SwingFactory extends AbstractComponentFactory<JComponent> {

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createFrame()
     */
    public Component<? extends JComponent> createFrame() {
        return new SwingContainer();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createLabel()
     */
    public Component<? extends JComponent> createLabel() {
        return new SwingLabel();
    }

    /**
     * @see org.magicui.ui.factory.ComponentFactory#createWindow(java.lang.String, org.magicui.ui.View)
     */
    public Object createWindow(String title, View<JComponent> content) {
    	final JFrame frame = new JFrame(title);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	if (content.getTop() != null) {
    		frame.getContentPane().add(convert(content, content.getTop()), BorderLayout.NORTH);
    	} 
    	if (content.getBottom() != null) {
    		frame.getContentPane().add(convert(content, content.getBottom()), BorderLayout.SOUTH);
    	} 
    	if (content.getLeft() != null) {
    		frame.getContentPane().add(convert(content, content.getLeft()), BorderLayout.WEST);
    	} 
    	if (content.getRight() != null) {
    		frame.getContentPane().add(convert(content, content.getRight()), BorderLayout.EAST);
    	} 
    	if (content.getMenu() != null) {
    		final JMenuBar menuBar = new JMenuBar();
    		
    		frame.setJMenuBar(menuBar);
    	}
    	
    	frame.getContentPane().add(content.getComponent(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); //TODO
        frame.setVisible(true);
        return frame;
    }

	/**
	 * @param content 
	 * @param top
	 * @return 
	 */
	private java.awt.Component convert(final View<JComponent> content, Collection<ActionItem> items) {
		final JToolBar bar = new JToolBar();
		for (final ActionItem item : items) {
			bar.add(new AbstractAction(item.getText(), new ImageIcon(item.getIcon())) {
					public void actionPerformed(ActionEvent e) {
						item.getAction().act(content);
					}
				});
		}
		return bar;
	}

}
