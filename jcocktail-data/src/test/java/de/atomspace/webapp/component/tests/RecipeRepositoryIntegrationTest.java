package de.atomspace.webapp.component.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.action.Action;
import de.atomspace.webapp.component.action.ActionRepository;
import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRepository;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.IngredientRepository;
import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.RecipeRow;
import de.atomspace.webapp.component.recipe.RecipeRowType;
import de.atomspace.webapp.component.recipe.service.RecipeRepository;
import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class RecipeRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	DimensionRepository dimensionRepository;
	
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	ActionRepository actionRepository;
	
	Unit l;
	Unit cl ;
	Dimension volume;
	Unit bottle;
	Ingredient ice;
	Ingredient juice;
	Ingredient wodka;
	Action pour;
	Action feel;
	
	@Before
	public void setUp() {
		operations.remove(new Query(new Criteria()) , Unit.class);
		operations.remove(new Query(new Criteria()) , Dimension.class);
		operations.remove(new Query(new Criteria()) , Ingredient.class);
		operations.remove(new Query(new Criteria()) , Action.class);
		operations.remove(new Query(new Criteria()) , Recipe.class);
		
		l = new Unit();
		l.setName("l");
		l.setDescription("litre");
		l.setDetached(false);
		l.setPublished(true);
		l = unitRepository.save(l);

		cl = new Unit();
		cl.setName("cl");
		cl.setDescription("centilitre");
		cl.setDetached(false);
		cl.setPublished(true);
		cl = unitRepository.save(cl);
		
		volume = new Dimension();
		volume.setDetached(false);
		volume.setPublished(true);
		volume.setName("volume");
		volume.setUnit(l);
		volume.getDimensionRows().add(new DimensionRow(cl, new BigDecimal(100)));
		volume = dimensionRepository.save(volume);
		
		bottle = new Unit();
		bottle.setName("bottle");
		bottle.setDescription("bottle");
		bottle.setDetached(false);
		bottle.setPublished(true);
		bottle = unitRepository.save(bottle);
		
		//Ice
		ice = new Ingredient();
		ice.setAlcohol(BigDecimal.ZERO);
		ice.setDescription("frozen water");
		ice.setDetached(false);
		ice.setPublished(true);
		ice.setDimension(volume);
		ice.setName("crushed ice");
		ice.setPlanningFunction(true);
		ice = ingredientRepository.save(ice);
		
		//Juice
		juice = new Ingredient();
		juice.setAlcohol(BigDecimal.ZERO);
		juice.setDescription("juice of oranges");
		juice.setDetached(false);
		juice.setPublished(true);
		volume.getDimensionRows().add(new DimensionRow(bottle,new BigDecimal(1)));
		juice.setDimension(volume);
		juice.setName("orange juice");
		juice.setPlanningFunction(true);
		juice = ingredientRepository.save(juice);
		
		//Juice
		wodka = new Ingredient();
		wodka.setAlcohol(new BigDecimal(40.00));
		wodka.setDescription("wodka");
		wodka.setDetached(false);
		wodka.setPublished(true);
		wodka.setDimension(volume);
		wodka.setName("wodka");
		wodka.setPlanningFunction(true);
		wodka = ingredientRepository.save(wodka);
		
		pour = new Action();
		pour.setName("pour");
		pour.setDetached(false);
		pour.setPublished(true);
		pour.setDescription("");
		pour = actionRepository.save(pour);
		
		feel = new Action();
		feel.setName("glass feel");
		feel.setDetached(false);
		feel.setPublished(true);
		feel.setDescription("");
		feel = actionRepository.save(feel);
	}


	@Ignore
	@Test
	public void createAndFindRecipe() throws Exception {
		Recipe recipe1 = new Recipe();
		recipe1.setName("cool orange juice");
		recipe1.setDescription("orance juice with ice");
		recipe1.setDetached(false);
		recipe1.setPublished(true);
		recipe1.setInstruction("bla bla bla");
		recipe1.getRows().add(new RecipeRow(feel));
		recipe1.getRows().add(new RecipeRow(ice, cl, new BigDecimal(0.04)));
		recipe1.getRows().add(new RecipeRow(pour));
		recipe1.getRows().add(new RecipeRow(juice, cl, new BigDecimal(0.10)));
		
		assertNull(recipe1.getId());
		recipe1 = recipeRepository.save(recipe1);
		assertNotNull(recipe1.getId());
		
		Recipe recipe2 = recipeRepository.findOne(recipe1.getId());
		System.out.println(recipe2.getName());
		System.out.println(recipe2.getDescription());
		ArrayList<RecipeRow> rows2 = recipe2.getRows();
		for (RecipeRow recipeRow : rows2) {
			RecipeRowType type = recipeRow.getType();
			if(type==null){
				fail("RecipeRowType is NULL");
			}
		}
		
		assertEquals(RecipeRowType.ACTION, recipe2.getRows().get(0).getType());
		assertEquals(RecipeRowType.INGREDIENT, recipe2.getRows().get(1).getType());
		assertEquals(RecipeRowType.ACTION, recipe2.getRows().get(2).getType());
		assertEquals(RecipeRowType.INGREDIENT, recipe2.getRows().get(3).getType());
		
		assertEquals("glass feel", recipe2.getRows().get(0).getAction().getName());
		assertEquals("crushed ice", recipe2.getRows().get(1).getIngredient().getName());
		assertEquals("pour", recipe2.getRows().get(2).getAction().getName());
		assertEquals("orange juice", recipe2.getRows().get(3).getIngredient().getName());
		
		assertEquals("cl", recipe2.getRows().get(1).getUnit().getName());
		assertEquals("cl", recipe2.getRows().get(3).getUnit().getName());
		
		assertEquals(0.04, recipe2.getRows().get(1).getAmount().doubleValue(), 0.0000000001);
		assertEquals(0.10, recipe2.getRows().get(3).getAmount().doubleValue(), 0.0000000001);
		
		Recipe recipe3 = new Recipe();
		recipe3.setName("scewdriver");
		recipe3.setDescription("orance juice with wodka");
		recipe3.setDetached(false);
		recipe3.setPublished(true);
		recipe3.setInstruction("bla bla bla");
		recipe3.getRows().add(new RecipeRow(feel));
		recipe3.getRows().add(new RecipeRow(ice, cl, new BigDecimal(0.04)));
		recipe3.getRows().add(new RecipeRow(pour));
		recipe3.getRows().add(new RecipeRow(juice, cl, new BigDecimal(0.10)));
		recipe3.getRows().add(new RecipeRow(wodka, cl, new BigDecimal(0.04)));
		
		assertNull(recipe3.getId());
		recipe3 = recipeRepository.save(recipe3);
		assertNotNull(recipe3.getId());
		
		Recipe recipe4 = recipeRepository.findOneByName("scewdriver");
		assertNotNull(recipe4);
		
		//Not working with @DBRef annotation
		//List<Recipe> recipes3 = recipeRepository.findByRowsIngredientNameLike("*ice*");
		//System.out.println("SIZE:"+recipes3.size());
		
		//Not working with @DBRef annotation
		//recipes3 = recipeRepository.findByRowsIngredientName("wodka");
		//System.out.println("SIZE:"+recipes3.size());
		
		List<Recipe> xx1 = operations.find(new Query(Criteria.where("name").is("scewdriver")), Recipe.class);
		assertEquals(1, xx1.size());
		
		List<Recipe> xx2 =recipeRepository.findByIngredient(wodka.getId().toString());
		assertEquals(1, xx2.size());
		
	}

	@Test
	public void listRecipes() throws Exception {
		ArrayList<Recipe> dummys = new ArrayList<Recipe>(); 
		for(int i=0;i<107;i++){
			Recipe dummy = new Recipe();
			dummy.setName("DUMMY-"+i);
			dummys.add(dummy);
		}
		recipeRepository.save(dummys);
		
		Pageable pageable = new PageRequest(0, 10);;
		Page<Recipe> page = recipeRepository.findAll(pageable );
		
		assertEquals(10,page.getContent().size());
		assertEquals(0,page.getNumber());
		assertEquals(10,page.getNumberOfElements());
		assertEquals(10,page.getSize());
		assertEquals(107,page.getTotalElements());
		assertEquals(11,page.getTotalPages());
		assertEquals(true,page.isFirstPage());
		assertEquals(false,page.isLastPage());
		
		
		
		pageable = new PageRequest(10, 10);;
		page = recipeRepository.findAll(pageable );
		
		assertEquals(7,page.getContent().size());
		assertEquals(10,page.getNumber());
		assertEquals(7,page.getNumberOfElements());
		assertEquals(10,page.getSize());
		assertEquals(107,page.getTotalElements());
		assertEquals(11,page.getTotalPages());
		assertEquals(false,page.isFirstPage());
		assertEquals(true,page.isLastPage());
		
	}
}
