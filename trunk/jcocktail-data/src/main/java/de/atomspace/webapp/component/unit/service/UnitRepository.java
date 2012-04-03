package de.atomspace.webapp.component.unit.service;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.unit.Unit;

public interface UnitRepository extends CrudRepository<Unit, ObjectId> {

	public Unit findOneByName(String name);

	
	
}
