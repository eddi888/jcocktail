package de.atomspace.webapp.component.dimension.service;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.dimension.Dimension;

public interface DimensionRepository extends CrudRepository<Dimension, ObjectId> {

	public  Dimension findOneByName(String name);

	
	
}
