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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.action.Action;
import de.atomspace.webapp.component.action.service.ActionRepository;
import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.dimension.service.DimensionRepository;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.service.IngredientRepository;
import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.RecipeRow;
import de.atomspace.webapp.component.recipe.RecipeRowType;
import de.atomspace.webapp.component.recipe.service.RecipeRepository;
import de.atomspace.webapp.component.recipe.service.RecipeService;
import de.atomspace.webapp.component.tests.helper.Helper;
import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-integration-tests-context.xml")
public class RecipeServiceIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	DimensionRepository dimensionRepository;
	
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	//@Autowired
	//RecipeRepository recipeRepository;

	@Autowired
	RecipeService recipeService;
	
	@Autowired
	ActionRepository actionRepository;
	
	Helper helper = new Helper();
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
		Authentication testAuthentication = new UsernamePasswordAuthenticationToken("testerRecipe", "4444");
		SecurityContextHolder.getContext().setAuthentication(testAuthentication);
		
		
		operations.remove(new Query(new Criteria()) , Unit.class);
		operations.remove(new Query(new Criteria()) , Dimension.class);
		operations.remove(new Query(new Criteria()) , Ingredient.class);
		operations.remove(new Query(new Criteria()) , Action.class);
		operations.remove(new Query(new Criteria()) , Recipe.class);
		
		l = unitRepository.save(helper.getUnit03Liter());
		cl = unitRepository.save(helper.getUnit04CL());
		volume = dimensionRepository.save(helper.getDimension02Volume(l, cl));
		
		bottle = unitRepository.save(helper.getUnit05Bottle());
		
		//Ice
		ice = ingredientRepository.save(helper.getIngradient03Ice(volume));
		
		
		Dimension volumeWithBottle = helper.getDimension02Volume(l, cl);
		volumeWithBottle.getDimensionRows().add(new DimensionRow(bottle, BigDecimal.ONE));
		
		//Juice
		juice = ingredientRepository.save(helper.getIngradient02Juice(volumeWithBottle));
		
		//wodca
		wodka = ingredientRepository.save(helper.getIngradient04Wodca(volumeWithBottle));
		
		pour = actionRepository.save(helper.getAction01Pour());
		
		feel = actionRepository.save(helper.getAction01Feel());
	}

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
		recipe1 = recipeService.save(recipe1);
		assertNotNull(recipe1.getId());
		
		Recipe recipe2 = recipeService.findOne(recipe1.getId());
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
		recipe3 = recipeService.save(recipe3);
		assertNotNull(recipe3.getId());
		
		Recipe recipe4 = recipeService.findOneByName("scewdriver");
		assertNotNull(recipe4);
		
		//Not working with @DBRef annotation
		//List<Recipe> recipes3 = recipeRepository.findByRowsIngredientNameLike("*ice*");
		//System.out.println("SIZE:"+recipes3.size());
		
		//Not working with @DBRef annotation
		//recipes3 = recipeRepository.findByRowsIngredientName("wodka");
		//System.out.println("SIZE:"+recipes3.size());
		
		List<Recipe> xx1 = operations.find(new Query(Criteria.where("name").is("scewdriver")), Recipe.class);
		assertEquals(1, xx1.size());
		
		List<Recipe> xx2 =recipeService.findByIngredient(wodka.getId().toString());
		assertEquals(1, xx2.size());
		
	}

	@Ignore
	@Test
	public void listRecipes() throws Exception {
		ArrayList<Recipe> dummys = new ArrayList<Recipe>(); 
		for(int i=0;i<107;i++){
			Recipe dummy = helper.getRecipe01Dummy(juice, ice, cl, pour, feel);
			dummy.setName("DUMMY-"+i);
			dummys.add(dummy);
		}
		recipeService.save(dummys);
		
		Pageable pageable = new PageRequest(0, 10);;
		Page<Recipe> page = recipeService.findAll(pageable );
		
		assertEquals(10,page.getContent().size());
		assertEquals(0,page.getNumber());
		assertEquals(10,page.getNumberOfElements());
		assertEquals(10,page.getSize());
		assertEquals(107,page.getTotalElements());
		assertEquals(11,page.getTotalPages());
		assertEquals(true,page.isFirstPage());
		assertEquals(false,page.isLastPage());
		
		pageable = new PageRequest(10, 10);;
		page = recipeService.findAll(pageable );
		
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
