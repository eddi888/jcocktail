package de.atomspace.webapp.component.recipe.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.atomspace.webapp.component.recipe.Recipe;

public interface RecipeService {

	List<Recipe> findByIngredient(String ingredientIdAsString);

	Page<Recipe> findAll(Pageable pageable);

	Recipe findOneByName(String recipeName);

	Recipe save(Recipe entity);

	Iterable<Recipe> save(Iterable<? extends Recipe> entities);

	Recipe findOne(ObjectId id);

	boolean exists(ObjectId id);

	long count();

	Recipe delete(Recipe entity);
	
}
