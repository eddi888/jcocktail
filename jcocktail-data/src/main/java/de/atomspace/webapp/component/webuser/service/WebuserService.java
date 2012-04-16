package de.atomspace.webapp.component.webuser.service;

import de.atomspace.webapp.component.webuser.Webuser;



public interface WebuserService {

	Webuser findOneByUserOrInitOne(String user);

	Webuser findOneByUser(String user);

	
}
