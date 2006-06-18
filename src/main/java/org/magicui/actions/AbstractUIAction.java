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

import java.util.Hashtable;
import java.util.Map;

/**
 * AbstractUIAction is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public abstract class AbstractUIAction
        extends AbstractAction implements UIAction {
    /**
     * The parameters <code>Map<String,String></code> field.
     */
    private Map<String, String> parameters = new Hashtable<String, String>();
    
    /**
     * The children <code>Object[]</code> field.
     */
    private Object [] children;
    
    /**
     * @see org.magicui.actions.UIAction#setParameter(java.lang.String, java.lang.String)
     */
    public void setParameter(String key, String value) {
        this.parameters.put(key, value);
    }
    
    /**
     * @see org.magicui.actions.UIAction#getParameter(java.lang.String)
     */
    public String getParameter(String key) {
        return this.parameters.get(key);
    }
    
    /**
     * @see org.magicui.actions.UIAction#setChildren(java.lang.Object[])
     */
    public void setChildren(Object[] objects) {
        this.children = objects;
    }
    
    /**
     * @see org.magicui.actions.UIAction#getChildren()
     */
    public Object[] getChildren() {
        return this.children;
    }
}
