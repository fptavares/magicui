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
package org.magicui.ui;

import org.magicui.State;

/**
 * ViewState is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class ViewState implements State {

	/**
	 * The view <code>View</code> field.
	 */
	private final View view;
	
	
	/**
	 * @param view
	 */
	public ViewState(final View view) {
		this.view = view;
	}


	/**
	 * @see org.magicui.State#get(java.lang.String)
	 */
	public Object get(String id) { // FIXME: handle the dots '.'!!!
		final Component component = this.view.getComponentById(id);
		if (component instanceof ValueComponent) {
			return ((ValueComponent<?, ?>) component).getValue();
		}
		return null; //throw new MagicUIException(id + " doesn't exist or isn't a value component.");
	}


	/** 
	 * @see org.magicui.State#set(java.lang.String, java.lang.Object)
	 */
	public void set(String id, Object value) {
		final Component component = this.view.getComponentById(id);
		if (component instanceof ValueComponent) {
			((ValueComponent<?, Object>) component).setValue(value);
		}/* else {
			throw new MagicUIException(id + " doesn't exist or isn't a value component.");
		}*/
	}
    
    public View<?> getAuxiliarView(String id) {
        return this.view.getAuxiliarView(id);
    }

}
