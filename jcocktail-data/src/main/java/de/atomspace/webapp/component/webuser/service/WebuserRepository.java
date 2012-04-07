package de.atomspace.webapp.component.webuser.service;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.webuser.Webuser;


public interface WebuserRepository extends CrudRepository<Webuser, ObjectId> {

	Webuser findOneByUser(String user);

}
