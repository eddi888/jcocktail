package de.atomspace.webapp.component.tests.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Date;

import de.atomspace.webapp.component.action.Action;
import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.dimension.DimensionType;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.RecipeRow;
import de.atomspace.webapp.component.unit.Unit;

/**
 * 
 * Bring a Set of filled Objects
 *
 */
public class Helper {
	
	public Helper() {
	}
	
	public Unit getUnit01Kilo(){
		Unit entity = new Unit();
		entity.setName("kg");
		entity.setDescription("Kilogramm");
		entity.setDetached(false);
		entity.setPublished(true);
		return entity;
	}
	
	public Unit getUnit02Gramm(){
		Unit entity = new Unit();
		entity.setName("g");
		entity.setDescription("Gramm");
		entity.setDetached(false);
		entity.setPublished(true);
		return entity;
	}
	
	public Unit getUnit03Liter(){
		Unit entity = new Unit();
		entity.setName("l");
		entity.setDescription("Liter");
		entity.setDetached(false);
		entity.setPublished(true);
		return entity;
	}
	
	public Unit getUnit04CL(){
		Unit entity = new Unit();
		entity.setName("cl");
		entity.setDescription("Centiliter");
		entity.setDetached(false);
		entity.setPublished(true);
		return entity;
	}
	
	public Unit getUnit05Bottle(){
		Unit entity = new Unit();
		entity.setName("Bottle");
		entity.setDescription("Bottle");
		entity.setDetached(false);
		entity.setPublished(true);
		return entity;
	}
	
	public Dimension getDimension01Mass(Unit kg, Unit g){
		Dimension entity3 = new Dimension();
		entity3.setType(DimensionType.MASS);
		entity3.setDetached(false);
		entity3.setPublished(true);
		entity3.setName("Mass");
		entity3.setUnit(kg);
		entity3.getDimensionRows().add(new DimensionRow(g, new BigDecimal(1000)));
		return entity3;
	}
	
	public Dimension getDimension02Volume(Unit l, Unit cl){
		Dimension entity3 = new Dimension();
		entity3.setType(DimensionType.VOLUME);
		entity3.setDetached(false);
		entity3.setPublished(true);
		entity3.setName("Volume");
		entity3.setUnit(l);
		entity3.getDimensionRows().add(new DimensionRow(cl, new BigDecimal(100)));
		return entity3;
	}
	
	public Ingredient getIngradient01Water(Dimension dimension){
		Ingredient ingredient = new Ingredient();
		ingredient.setAlcohol(BigDecimal.ZERO);
		ingredient.setCreatedBy("CreatedBy");
		ingredient.setCreatedDate(new Date());
		ingredient.setDescription("Water");
		ingredient.setDetached(false);
		ingredient.setDimension(dimension);
		ingredient.setName("Water");
		ingredient.setPlanningFunction(true);
		ingredient.setPublished(true);
		ingredient.setUpdatedBy("updatedBy");
		ingredient.setUpdatedDate(new Date());
		return ingredient;
	}
	
	public Ingredient getIngradient02Juice(Dimension dimension){
		Ingredient ingredient = new Ingredient();
		ingredient.setAlcohol(BigDecimal.ZERO);
		ingredient.setCreatedBy("CreatedBy");
		ingredient.setCreatedDate(new Date());
		ingredient.setDescription("juice of oranges");
		ingredient.setDetached(false);
		ingredient.setDimension(dimension);
		ingredient.setName("orange juice");
		ingredient.setPlanningFunction(true);
		ingredient.setPublished(true);
		ingredient.setUpdatedBy("updatedBy");
		ingredient.setUpdatedDate(new Date());
		return ingredient;
	}
	
	public Ingredient getIngradient03Ice(Dimension dimension){
		Ingredient ingredient = new Ingredient();
		ingredient.setAlcohol(BigDecimal.ZERO);
		ingredient.setCreatedBy("CreatedBy");
		ingredient.setCreatedDate(new Date());
		ingredient.setDescription("frozen water");
		ingredient.setDetached(false);
		ingredient.setDimension(dimension);
		ingredient.setName("crushed ice");
		ingredient.setPlanningFunction(true);
		ingredient.setPublished(true);
		ingredient.setUpdatedBy("updatedBy");
		ingredient.setUpdatedDate(new Date());
		return ingredient;
	}
	
	public Ingredient getIngradient04Wodca(Dimension dimension){
		Ingredient ingredient = new Ingredient();
		ingredient.setAlcohol(new BigDecimal(40));
		ingredient.setCreatedBy("CreatedBy");
		ingredient.setCreatedDate(new Date());
		ingredient.setDescription("Wodca");
		ingredient.setDetached(false);
		ingredient.setDimension(dimension);
		ingredient.setName("Wodca");
		ingredient.setPlanningFunction(true);
		ingredient.setPublished(true);
		ingredient.setUpdatedBy("updatedBy");
		ingredient.setUpdatedDate(new Date());
		return ingredient;
	}
	
	public Action getAction01Pour() {
		Action pour = new Action();
		pour.setName("pour");
		pour.setDetached(false);
		pour.setPublished(true);
		pour.setDescription("");
		return pour;
	}
	public Action getAction01Feel() {
		Action feel = new Action();
		feel.setName("glass feel");
		feel.setDetached(false);
		feel.setPublished(true);
		feel.setDescription("");
		return feel;
	}
	
	public Recipe getRecipe01Dummy(Ingredient juice, Ingredient ice, Unit cl, Action action1, Action action2){
		Recipe recipe = new Recipe();
		recipe.setCreatedBy("createdBy");
		recipe.setCreatedDate(new Date());
		recipe.setDescription("DUMMY");
		recipe.setDetached(false);
		recipe.setInstruction("no instructuions");
		recipe.setName("dummy");
		recipe.setPublished(true);
		recipe.setUpdatedBy("updatedBy");
		recipe.setUpdatedDate(new Date());
		recipe.getRows().add(new RecipeRow(juice, cl, new BigDecimal(0.30)));
		recipe.getRows().add(new RecipeRow(action1));
		recipe.getRows().add(new RecipeRow(ice, cl, new BigDecimal(0.10)));
		recipe.getRows().add(new RecipeRow(action2));
		return recipe;
	}
}
