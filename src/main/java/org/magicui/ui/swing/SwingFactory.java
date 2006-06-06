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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
     * @see org.magicui.ui.factory.ComponentFactory#createWindow()
     */
    public Object createWindow(String title, View<JComponent> content) {
    	final JFrame frame = new JFrame(title);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(content.getComponent());
        frame.pack();
        frame.setLocationRelativeTo(null); //TODO
        frame.setVisible(true);
        return frame;
    }

}
