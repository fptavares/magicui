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
package org.magicui;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.View;
import org.magicui.ui.swing.SwingFactory;

/**
 * SwingApp is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class SwingApp {
	
	/**
	 * @param args
	 * @throws MagicUIException 
	 */
	public static void main(String[] args) throws MagicUIException {
		final Application<JComponent> app = new Application<JComponent>();
		app.factory = new SwingFactory();
		app.registerAction("act1", new Action() {
			public void act(View container) {
				JOptionPane.showMessageDialog(null, String.valueOf(container));
			}
		});
		app.start();
	}
	
}
