package de.atomspace.webapp.services;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		
		if(session.getAttribute("recipeListNumber")==null){
			session.setAttribute("recipeListNumber", 0);
		}
		if(session.getAttribute("ingredientListNumber")==null){
			session.setAttribute("ingredientListNumber", 0);
		}
		if(session.getAttribute("unitListNumber")==null){
			session.setAttribute("unitListNumber", 0);
		}
		if(session.getAttribute("dimensionListNumber")==null){
			session.setAttribute("dimensionListNumber", 0);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	
	}
	
	
}
