package de.atomspace.webapp.component.tests;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.dimension.service.DimensionRepository;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.service.IngredientRepository;
import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class IngredientRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	DimensionRepository dimensionRepository;
	
	
	@Autowired
	IngredientRepository ingredientRepository;

	Dimension entity3;
	Unit entity4;
	
	@Before
	public void setUp() {
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
	public void createIngredient() throws Exception {
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
		ingredientRepository.save(juice);
		assertNotNull(juice.getId());
	}

}
