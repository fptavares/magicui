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
package org.magicui.ui.web;

import java.util.ArrayList;
import java.util.List;

/**
 * WebTagContainer is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class WebTagContainer extends AbstractWebTag {
    /**
     * The tags <code>Collection<WebTag></code> field.
     */
    final List<WebTag> tags = new ArrayList<WebTag>();
    
    /**
     * @see org.magicui.ui.web.WebTag#getCode()
     */
    public String getCode() {
        final StringBuffer sb = new StringBuffer();
        for (WebTag tag : this.tags) {
            sb.append(tag.getCode());
        }
        return sb.toString();
    }
    
    /**
     * @param tag The tag
     */
    public final void add(WebTag tag) {
        this.tags.add(tag);
    }
    
    public final void insert(WebTag tag) {
        this.tags.add(0, tag);
    }
    
}
