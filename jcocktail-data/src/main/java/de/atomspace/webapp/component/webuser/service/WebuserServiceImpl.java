package de.atomspace.webapp.component.webuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.atomspace.webapp.component.webuser.Webuser;

@Service
public class WebuserServiceImpl implements WebuserService {
	
	@Autowired
	WebuserRepository webuserRepository; 
	
	/*	
	public Webuser findOneByUser(String string) {
		Query<Webuser> query = datastore.createQuery(Webuser.class);
		query = query.limit(1);
		query = query.field("detached").equal(false);
		query = query.field("user").equal(string);
		List<Webuser> list = dao.find(query).asList();
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	public Key<Webuser> save(Webuser entity){
		 return dao.save(entity);
	}
	*/
	
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
}
