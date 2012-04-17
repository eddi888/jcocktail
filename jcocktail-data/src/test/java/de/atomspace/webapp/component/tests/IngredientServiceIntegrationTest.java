package de.atomspace.webapp.component.tests;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.dimension.service.DimensionRepository;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.service.IngredientRepository;
import de.atomspace.webapp.component.ingredient.service.IngredientService;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-integration-tests-context.xml")
public class IngredientServiceIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	DimensionRepository dimensionRepository;
	
	@Autowired
	IngredientService ingredientService;
	
	//@Autowired
	//AuthenticationManager authenticationManager;
	
	Dimension entity3;
	Unit entity4;
	
	@Before
	public void setUp() {
		Authentication testAuthentication = new UsernamePasswordAuthenticationToken("tester", "1234");
		SecurityContextHolder.getContext().setAuthentication(testAuthentication);
		
		operations.remove(new Query(new Criteria()) , Unit.class);
		operations.remove(new Query(new Criteria()) , Dimension.class);
		operations.remove(new Query(new Criteria()) , Ingredient.class);
		
		Unit entity = new Unit();
		entity.setName("l");
		entity.setDescription("litre");
		entity.setDetached(false);
		entity.setPublished(true);
		entity = unitRepository.save(entity);
		
		Unit entity2 = new Unit();
		entity2.setName("cl");
		entity2.setDescription("centilitre");
		entity2.setDetached(false);
		entity2.setPublished(true);
		entity2 = unitRepository.save(entity2);
		
		entity3 = new Dimension();
		entity3.setDetached(false);
		entity3.setPublished(true);
		entity3.setName("volume");
		entity3.setUnit(entity);
		entity3.getDimensionRows().add(new DimensionRow(entity2, new BigDecimal(100)));
		entity3 = dimensionRepository.save(entity3);
		
		entity4 = new Unit();
		entity4.setName("bottle");
		entity4.setDescription("bottle");
		entity4.setDetached(false);
		entity4.setPublished(true);
		entity4 = unitRepository.save(entity4);
		
	}
	
	
	
	@Test
	public void createAndDeleteIngredient() throws Exception {
		Ingredient juice = new Ingredient();
		juice.setAlcohol(BigDecimal.ZERO);
		juice.setDescription("juice of oranges");
		juice.setDetached(false);
		juice.setPublished(true);
		
		entity3.getDimensionRows().add(new DimensionRow(entity4,new BigDecimal(1)));
		juice.setDimension(entity3);
		
		juice.setName("orange juice");
		juice.setPlanningFunction(true);
		assertNull(juice.getId());
		juice = ingredientService.save(juice);
		assertNotNull(juice.getId());
		assertEquals("tester",juice.getCreatedBy());
		assertEquals("tester",juice.getUpdatedBy());
		
		Authentication testAuthentication = new UsernamePasswordAuthenticationToken("modifier", "789");
		SecurityContextHolder.getContext().setAuthentication(testAuthentication);
		
		juice = ingredientService.save(juice);
		assertEquals("tester",juice.getCreatedBy());
		assertEquals("modifier",juice.getUpdatedBy());
	}
	
	
	
}
