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

import org.magicui.Action;
import org.magicui.ui.AbstractValueComponent;
import org.magicui.ui.View;

/**
 * WebWindow is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class WebWindow extends AbstractValueComponent<WebValueTag<WebTag>,WebTag> {

    /**
     * Creates a new <code>WebWindow</code> instance.
     * @param title
     * @param content
     */
    public WebWindow(final String title, final View<? extends WebTag> content) {
        this.component.setParams(title);
        this.component.setValue(content.getComponent());
    }

    /**
     * @see org.magicui.ui.ValueComponent#addListener(java.lang.String, org.magicui.Action)
     */
    public void addListener(String type, Action action) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.magicui.ui.Component#createComponent()
     */
    public WebValueTag<WebTag> createComponent() {
        return new WebValueTag<WebTag>("<window title=\"%s\" xmlns:x=\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\">%v</window>");
    }

    /**
     * @see org.magicui.ui.ValueComponent#getValue()
     */
    public WebTag getValue() {
        return this.component.getValue();
    }

    /**
     * @see org.magicui.ui.ValueComponent#setValue(java.lang.Object)
     */
    public void setValue(WebTag value) {
        this.component.setValue(value);
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.component.getCode();
    };

}
