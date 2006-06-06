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

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.magicui.Globals;
import org.magicui.ui.ActionItem;
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
    
    private int xCounter = 0;
    private int yCounter = 0;

	/**
	 * @see org.magicui.ui.Component#createComponent()
	 */
	public JPanel createComponent() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		return panel;
	}
	
	/**
	 * @see org.magicui.ui.AbstractContainer#addComponent(org.magicui.ui.Component, int, int, int, int)
	 */
	public void addComponent(Component component, int x, int y, int xWeight, int yHeight) {
		addComponent((JComponent) component.getComponent(), x, y, xWeight, yHeight);
	}
	
	private void addComponent(JComponent component, int x, int y, int xWeight, int yWeight) {
		this.gridBagConstraints.anchor = DEFAULT_ANCHOR;
        this.gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagConstraints.gridwidth = xWeight;
        this.gridBagConstraints.gridheight = yWeight;
        this.gridBagConstraints.gridx = x+1; // to make room for toolbars
        this.gridBagConstraints.gridy = y+1; // to make room for toolbars
        this.gridBagConstraints.insets = defaultInsets;
        this.gridBagConstraints.weightx = 1.0;
        ((Container) this.component).add(component, this.gridBagConstraints);
	}

	/**
	 * @see org.magicui.ui.View#addMenus(java.util.Collection)
	 */
	public void addMenus(Collection<ActionItem> menu) {
		JMenuBar bar = new JMenuBar();
	}

	/**
	 * @see org.magicui.ui.View#addToolbars(java.util.Collection)
	 */
	public void addToolbars(Collection<ActionItem> toolbar) {
		JToolBar top = null;
		JToolBar bottom = null;
		JToolBar left = null;
		JToolBar right = null;
		for (final ActionItem item : toolbar) {
			if (item.getPlace().equals(Globals.TOOLBAR_TOP)) {
				if (top == null) {
					top = new JToolBar();
					addComponent(top, 0, 0, GridBagConstraints.REMAINDER, 0);
				}
				top.add(new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						item.getAction().act(SwingContainer.this);
					}
				});
			} else if (item.getPlace().equals(Globals.TOOLBAR_LEFT)) {
				if (left == null) {
					left = new JToolBar();
					addComponent(left, 0, 0, 0, GridBagConstraints.REMAINDER);
				}
				top.add(new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						item.getAction().act(SwingContainer.this);
					}
				});
			}
		}
	}

}
