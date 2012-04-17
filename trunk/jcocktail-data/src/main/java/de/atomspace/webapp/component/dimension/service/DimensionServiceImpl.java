package de.atomspace.webapp.component.dimension.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import de.atomspace.webapp.component.dimension.Dimension;

public class DimensionServiceImpl implements DimensionService {

	@Autowired
	DimensionRepository dimensionRepository;
	
	@Override
	public Dimension findOneByName(String name) {
		return dimensionRepository.findOneByName(name);
	}

	@Override
	public Dimension save(Dimension entity) {
		return dimensionRepository.save(entity);
	}

	@Override
	public Iterable<Dimension> save(Iterable<? extends Dimension> entities) {
		return null;
	}

	@Override
	public Dimension findOne(ObjectId id) {
		
		return null;
	}

	@Override
	public boolean exists(ObjectId id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Dimension> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(ObjectId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Dimension entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Dimension> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}



}
