package de.atomspace.webapp.component.dimension;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface DimensionRepository extends CrudRepository<Dimension, ObjectId> {

	public  Dimension findOneByName(String name);

	
	
}
