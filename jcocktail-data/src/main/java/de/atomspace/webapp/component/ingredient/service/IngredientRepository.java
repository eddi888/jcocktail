package de.atomspace.webapp.component.ingredient.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.ingredient.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, ObjectId> {
	
	public Ingredient findOneByName(String name);

	public Page<Ingredient> findByDetached(Pageable pageable, boolean detached);
	
	
	

	
	
}
