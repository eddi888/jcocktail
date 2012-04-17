package de.atomspace.webapp.component.ingredient.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomspace.webapp.component.ingredient.Ingredient;

public interface IngredientService {

	Page<Ingredient> findAll(Pageable pageable);

	Ingredient delete(Ingredient entity);

	Ingredient save(Ingredient entity);

	long count();

	boolean exists(ObjectId id);

	Ingredient findOne(ObjectId id);

	Iterable<Ingredient> save(Iterable<? extends Ingredient> entities);

	Page<Ingredient> findByDetached(Pageable pageable, boolean detached);

	Ingredient findOneByName(String name);
	
}
