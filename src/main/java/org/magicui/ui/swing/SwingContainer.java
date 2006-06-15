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

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.magicui.ui.Component;

/**
 * SwingContainer is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class SwingContainer extends AbstractSwingContainer<JPanel> {
	
    /**
     * The top value for the default defaultInsets.
     */
    private static final int INSETS_TOP = 5;
    /**
     * The left value for the default defaultInsets.
     */
    private static final int INSETS_LEFT = 5;
    /**
     * The bottom value for the default defaultInsets.
     */
    private static final int INSETS_BOTTOM = 5;
    /**
     * The right value for the default defaultInsets.
     */
    private static final int INSETS_RIGHT = 5;
    /**
     * The defaultInsets for the components.
     */
    public static final Insets defaultInsets =
        new Insets(INSETS_TOP, INSETS_LEFT, INSETS_BOTTOM, INSETS_RIGHT);
    /**
     * The DEFAULT_ANCHOR <code>int</code>.
     */
    public static final int DEFAULT_ANCHOR = GridBagConstraints.LINE_END;
    /**
     * The <code>GridBagConstraints</code>.
     */
    private final GridBagConstraints gridBagConstraints =
        new GridBagConstraints();

	/**
	 * @see org.magicui.ui.Component#createComponent()
	 */
	public JPanel createComponent() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		return panel;
	}
	
	/**
	 * @see org.magicui.ui.AbstractView#addComponent(org.magicui.ui.Component, int, int, int, int)
	 */
	public void addComponent(Component component, int x, int y, int xWeight, int yHeight) {
		addComponent((JComponent) component.getComponent(), x, y, xWeight, yHeight);
	}
	
	private void addComponent(JComponent component, int x, int y, int xWeight, int yWeight) {
		this.gridBagConstraints.anchor = DEFAULT_ANCHOR;
        this.gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagConstraints.gridwidth = xWeight;
        this.gridBagConstraints.gridheight = yWeight;
        this.gridBagConstraints.gridx = x;
        this.gridBagConstraints.gridy = y;
        this.gridBagConstraints.insets = defaultInsets;
        this.gridBagConstraints.weightx = 1.0;
        ((Container) this.component).add(component, this.gridBagConstraints);
	}
	
}
