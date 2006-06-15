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
package org.magicui.actions;

import org.magicui.State;

/**
 * ShowAction is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class ShowAction extends AbstractAction {
	
	/**
	 * The view <code>String</code> field.
	 */
	private final String view;
	
	/**
	 * The place <code>String</code> field.
	 */
	private final String place;
	
	/**
	 * The vars <code>Object[]</code> field.
	 */
	private final Object [] vars;


	/**
	 * @param view
	 * @param place
	 * @param vars
	 */
	public ShowAction(final String view, final String place, final Object[] vars) {
		this.view = view;
		this.place = place;
		this.vars = vars;
	}


	/**
	 * @see org.magicui.Action#act(State)
	 */
	public void act(State state) {
		state.set("..", /* TODO: view */);
	}

}
