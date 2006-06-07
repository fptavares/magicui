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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MagicUIServlet is a <b>cool</b> class.
 * 
 * @author Filipe Tavares
 * @author Belmiro Sotto-Mayor
 * @version $Revision$ ($Author$)
 */
public class MagicUIServlet extends HttpServlet {
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet (HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {
		// set headers before accessing the Writer
		response.setContentType("text/html");
		response.setBufferSize(8192);
		PrintWriter out = response.getWriter();

		// then write the response
		out.println("<html>" +
				"<head><title>" +
				"TitleBookDescription"
				+ "</title></head>");

		// Get the dispatcher; it gets the banner to the user
		RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher("/banner");
		if (dispatcher != null)
			dispatcher.include(request, response);

		//Get the identifier of the book to display
		String bookId = request.getParameter("bookId");
		if (bookId != null) {
			// and the information about the book
			try {
				// dgffsdh
			} catch (Exception ex) {
				response.resetBuffer();
				throw new ServletException(ex);
			}
		}
		out.println("</body></html>");
		out.close();
	}
}
