package de.atomspace.webapp.component.ingredient.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.corba.se.spi.activation.Repository;
import com.sun.xml.internal.stream.Entity;

import de.atomspace.webapp.component.dimension.service.DimensionRepository;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.webuser.service.WebuserRepository;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private WebuserRepository webuserRepository;
	
	@Autowired
	private DimensionRepository dimensionRepository;
	
	@Override
	public Page<Ingredient> findAll(Pageable pageable){
		return ingredientRepository.findByDetached(pageable, false);
	}
	
	public void validate(Ingredient entity){
		
	}
	
	public Ingredient getTemplate(){
		Ingredient entity = new Ingredient();
		entity.setAlcohol(BigDecimal.ZERO);
		entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		entity.setDescription("");
		entity.setName("");
		entity.setPlanningFunction(true);
		entity.setPublished(true);
		entity.setDetached(false);
		entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return entity;
	}
	
	@Override
	public Ingredient save(Ingredient entity){
		if(entity.getId()==null){
			entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			entity.setCreatedDate(new Date());
		}
		entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		entity.setUpdatedDate(new Date());
		return ingredientRepository.save(entity);
	}

	@Override
	public Ingredient findOneByName(String name) {
		return ingredientRepository.findOneByName(name);
	}

	@Override
	public Iterable<Ingredient> save(Iterable<? extends Ingredient> entities) {
		for (Ingredient entity : entities) {
			if(entity.getId()==null){
				entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				entity.setCreatedDate(new Date());
			}
			entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			entity.setUpdatedDate(new Date());	
		}
		return ingredientRepository.save(entities);
	}

	@Override
	public Ingredient findOne(ObjectId id) {
		return ingredientRepository.findOne(id);
	}

	@Override
	public boolean exists(ObjectId id) {
		return ingredientRepository.exists(id);
	}

	@Override
	public long count() {
		return ingredientRepository.count();
	}
	
	@Override
	public Ingredient delete(Ingredient entity){
		if(entity.getId()==null){
			return null;
		}
		entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		entity.setUpdatedDate(new Date());
		entity.setDetached(true);
		return ingredientRepository.save(entity);
	}

	@Override
	public Page<Ingredient> findByDetached(Pageable pageable, boolean detached) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
