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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import org.magicui.Action;
import org.magicui.ui.AbstractValueComponent;

/**
 * AbstractSwingComponent is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 * @param <T> The type of the content
 */
public abstract class AbstractSwingComponent<C extends JComponent, T> extends AbstractValueComponent<C, T> {

	/**
	 * @see org.magicui.ui.ValueComponent#addListener(String, org.magicui.Action)
	 */
	public void addListener(String type, final Action action) {
		final MouseAdapter listener;
		if (type.equals(EVENT_CLICK)) {
			listener = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					action.act(AbstractSwingComponent.this.getParent().getState());
				}
			};
		} else if (type.equals(EVENT_HOVER)) {
			listener = new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					action.act(AbstractSwingComponent.this.getParent().getState());
				}
			};
		} else {
			return;
		}
		this.component.addMouseListener(listener);
	}

}
