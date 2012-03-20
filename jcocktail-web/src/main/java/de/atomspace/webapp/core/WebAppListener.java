/* ******************************************************************************
*    JCocktail - Webapplication for Cocktail Resource Planing and Management.   *
*    Copyright (C) 2011  Edgar Wentzlaff                                        *
*                                                                               *
*    This program is free software: you can redistribute it and/or modify       *
*    it under the terms of the GNU Affero General Public License as             *
*    published by the Free Software Foundation, either version 3 of the         *
*    License, or (at your option) any later version.                            *
*                                                                               *
*    This program is distributed in the hope that it will be useful,            *
*    but WITHOUT ANY WARRANTY; without even the implied warranty of             *
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
*    GNU Affero General Public License for more details.                        *
*                                                                               *
*    You should have received a copy of the GNU Affero General Public License   *
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.      *
*                                                                               *
****************************************************************************** */

package de.atomspace.webapp.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.lang.Library;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

public class WebAppListener implements WebAppInit, WebAppCleanup {
	protected final Log log = LogFactory.getLog(WebAppListener.class);
	
	public void init(WebApp wapp) throws Exception {
		log.info("Init WebApp Context on "+wapp.getRealPath("/"));
		//Library.setProperty("org.zkoss.theme.preferred", "classicblue");
		Library.setProperty("org.zkoss.theme.preferred", "silvertail");
		
	}

	
	public void cleanup(WebApp wapp) throws Exception {
		log.info("Cleanup WebApp Context on "+wapp.getRealPath("/"));

	}

}
