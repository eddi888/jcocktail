package de.atomspace.webapp.component.recipe.service;

import java.util.List;

import org.bson.types.ObjectId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.recipe.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, ObjectId>, QueryDslPredicateExecutor<Recipe> {
	
	public Recipe findOneByName(String name);
	
	//Not working with @DBRef annotation
	//public List<Recipe> findByRowsIngredientNameLike(String name);
	
	//Not working with @DBRef annotation
	//public List<Recipe> findByRowsIngredientName(String name);
	
	@Query("{ 'rows.ingredient': {'$ref': 'ingredient', '$id': { '$oid': ?0 } } }")
    List<Recipe> findByIngredient(String ingredientIdAsString);
	
	Page<Recipe> findAll(Pageable pageable);
	
	public Page<Recipe> findByDetached(Pageable pageable, boolean detached);
	
	
	
}
