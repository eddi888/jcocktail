package de.atomspace.webapp.component.unit.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.atomspace.webapp.component.unit.Unit;

public interface UnitService {

	Unit findOneByName(String name);

	Unit findOne(ObjectId id);

	Page<Unit> findAll(Pageable pageable);

	Unit save(Unit entity);

	Unit delete(Unit entity);

}
