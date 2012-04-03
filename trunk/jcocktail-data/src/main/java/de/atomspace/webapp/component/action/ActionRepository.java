package de.atomspace.webapp.component.action;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action, ObjectId> {
	
	public Action findOneByName(String name);
	
}
