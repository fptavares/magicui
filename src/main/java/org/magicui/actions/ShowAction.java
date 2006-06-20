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

import static org.magicui.Globals.ATTR_ACTION_PLACE;
import static org.magicui.Globals.ATTR_ACTION_VIEW;

import org.magicui.Application;
import org.magicui.State;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.View;
import org.magicui.ui.ViewState;


/**
 * ShowAction is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class ShowAction extends AbstractUIAction {

	/**
	 * @see org.magicui.Action#act(org.magicui.State)
	 */
	public void act(State state) {
		try {
            View<?> auxView =
                ((ViewState) state).getAuxiliarView(getParameter(ATTR_ACTION_VIEW));
            if (auxView == null) {
                auxView = Application.getInstance()
                    .loadWidget(getParameter(ATTR_ACTION_VIEW), getChildren());
            }
            final String place = getParameter(ATTR_ACTION_PLACE);
            if (place == null) {
                Application.getInstance().showWidget(auxView.getId(), (View) auxView);
            } else {
                state.set(place, auxView);
            }
        } catch (MagicUIException e) {
            throw new RuntimeException(e);
        }
	}

}
