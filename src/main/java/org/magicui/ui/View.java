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

import java.util.Collection;

/**
 * View is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 * @param <C> The type of the component
 */
public interface View<C> extends Component<C> {

	/**
	 * @param component
	 * @param xCounter
	 * @param yCounter
	 * @param xWeight
	 * @param yWeight
	 */
	void add(Component component, int xCounter, int yCounter, int xWeight, int yWeight);

	/**
	 * @param menu
	 */
	void addMenus(Collection<ActionItem> menu);

	/**
	 * @param toolbar
	 */
	void addToolbars(Collection<ActionItem> toolbar);

}
