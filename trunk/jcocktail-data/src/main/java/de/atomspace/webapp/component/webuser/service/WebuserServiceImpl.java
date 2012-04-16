package de.atomspace.webapp.component.webuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.atomspace.webapp.component.webuser.Webuser;

@Service
public class WebuserServiceImpl implements WebuserService {
	
	@Autowired
	WebuserRepository webuserRepository; 
		
	@Override
	public Webuser findOneByUserOrInitOne(String user) {
		Webuser webuser = webuserRepository.findOneByUser(user);
		if(webuser==null){
			webuser = new Webuser();
			webuser.setDetached(false);
			webuser.setPublished(true);
			webuser.setName("Please Enter Your Nick Name");
			webuser.setUser(user);
			webuser.getRoles().add("ROLE_USER");
			webuser = webuserRepository.save(webuser);
		}
		return webuser;
	}

	@Override
	public Webuser findOneByUser(String user) {
		return webuserRepository.findOneByUser(user);

	}
	
}
