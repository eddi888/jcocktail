package de.atomspace.webapp.component.action.service;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.action.Action;

public interface ActionRepository extends CrudRepository<Action, ObjectId> {
	
	public Action findOneByName(String name);
	
}
