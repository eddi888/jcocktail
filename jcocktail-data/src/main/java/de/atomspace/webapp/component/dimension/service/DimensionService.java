package de.atomspace.webapp.component.dimension.service;

import org.bson.types.ObjectId;

import de.atomspace.webapp.component.dimension.Dimension;

public interface DimensionService {

	Dimension findOneByName(String name);

	Dimension save(Dimension entity);

	Iterable<Dimension> save(Iterable<? extends Dimension> entities);

	Dimension findOne(ObjectId id);

	boolean exists(ObjectId id);

	Iterable<Dimension> findAll();

	long count();

	void delete(ObjectId id);

	void delete(Dimension entity);

	void delete(Iterable<? extends Dimension> entities);

	void deleteAll();

}
