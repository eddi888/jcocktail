package de.atomspace.webapp.component.unit.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.unit.Unit;

public interface UnitRepository extends CrudRepository<Unit, ObjectId> {

	public Unit findOneByName(String name);

	public Page<Unit> findAll(Pageable pageable);
	
	public Page<Unit> findByDetached(Pageable pageable, boolean detached);
	
	public List<Unit> findByDetached(boolean detached);
	
}
