package de.atomspace.webapp.component.unit.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomspace.webapp.component.unit.Unit;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {
	
	@Autowired
	UnitRepository repository;
	
	@Override
	public Unit findOneByName(String name){
		return repository.findOneByName(name);
	}
	
	@Override
	public Unit findOne(ObjectId id){
		return repository.findOne(id);
	}
	
	@Override
	public Page<Unit> findAll(Pageable pageable){
		return repository.findByDetached(pageable, false);
	}
	
	@Override
	public Unit save(Unit entity){
		return repository.save(entity);
	}
	
	@Override
	public Unit delete(Unit entity){
		entity.setDetached(true);
		return repository.save(entity);
	}
	
}
