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
package org.magicui.ui.awt;

import java.awt.Container;

import org.magicui.ui.AbstractView;
import org.magicui.ui.Component;

/**
 * AwtView is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class AwtView extends AbstractView<Container> {

    /**
     * @see org.magicui.ui.AbstractView#addComponent(org.magicui.ui.Component, int, int, int, int)
     */
    @Override
    protected void addComponent(Component<?> child, int x, int y, int xWeight, int yWeight) {
        this.component.add((java.awt.Component) child.getComponent());
    }

    /**
     * @see org.magicui.ui.Component#createComponent()
     */
    public Container createComponent() {
        // TODO Auto-generated method stub
        return new Container();
    }

}
