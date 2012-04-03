package de.atomspace.webapp.component.ingredient;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, ObjectId> {
	
	public Ingredient findOneByName(String name);
	
}
