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
package org.magicui.ui.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.magicui.exceptions.MagicUIException;
import org.magicui.ui.web.WebApplication;

/**
 * MagicUIServlet is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class MagicUIServlet extends HttpServlet {
    private WebApplication app;
    private WebApplicationLoader loader;
    
    /**
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            this.app = new WebApplication();
            this.loader = (WebApplicationLoader) Class.forName(
                    this.getInitParameter("loader")).newInstance();
            this.loader.initialize(this.app);
        } catch (InstantiationException e) {
            throw new ServletException(e);
        } catch (IllegalAccessException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        } catch (MagicUIException e) {
            throw new ServletException(e);
        }
    }
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		// set headers before accessing the Writer
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// then write the response
		try {
            out.println(this.app.start(this.loader.mainWidgetVarValues()));
        } catch (MagicUIException e) {
            throw new ServletException(e);
        }

		out.close();
	}
}
