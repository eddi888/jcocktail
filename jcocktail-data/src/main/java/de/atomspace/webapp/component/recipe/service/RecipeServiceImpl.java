package de.atomspace.webapp.component.recipe.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import de.atomspace.webapp.component.recipe.Recipe;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Override
    public List<Recipe> findByIngredient(String ingredientIdAsString){
    	return recipeRepository.findByIngredient(ingredientIdAsString);
    }
	
	@Override
    public  Page<Recipe> findAll(Pageable pageable){
    	return recipeRepository.findAll(pageable);
    }

	@Override
	public Recipe findOneByName(String name) {
		return recipeRepository.findOneByName(name);
	}

	@Override
	public Recipe save(Recipe entity) {
		if(entity.getId()==null){
			entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			entity.setCreatedDate(new Date());
		}
		entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		entity.setUpdatedDate(new Date());
		return recipeRepository.save(entity);
	}

	@Override
	public Iterable<Recipe> save(Iterable<? extends Recipe> entities) {
		for (Recipe entity : entities) {
			if(entity.getId()==null){
				entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				entity.setCreatedDate(new Date());
			}
			entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			entity.setUpdatedDate(new Date());
		}
		return recipeRepository.save(entities);
	}

	@Override
	public Recipe findOne(ObjectId id) {
		return recipeRepository.findOne(id);
	}

	@Override
	public boolean exists(ObjectId id) {
		return recipeRepository.exists(id);
	}

	@Override
	public Iterable<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public long count() {
		return recipeRepository.count();
	}


	@Override
	public Recipe delete(Recipe entity) {
		if(entity.getId()==null){
			return null;
		}
		entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		entity.setUpdatedDate(new Date());
		entity.setDetached(true);
		return recipeRepository.save(entity);
	}
			
}
