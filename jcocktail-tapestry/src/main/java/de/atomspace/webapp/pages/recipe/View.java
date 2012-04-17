package de.atomspace.webapp.pages.recipe;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.RecipeRow;
import de.atomspace.webapp.component.recipe.RecipeRowType;
import de.atomspace.webapp.component.recipe.service.RecipeRepository;
import de.atomspace.webapp.component.recipe.service.RecipeService;

public class View {
	
	//@Inject
	//@Property
	//private Locale currentLocale;
	
	//@Persist(PersistenceConstants.SESSION)
	@SessionState(create=false)
	private Integer recipeListNumber;
	
	public Integer getRecipeListNumber() {
		return recipeListNumber;
	}
	
	@Autowired
	@Inject
	private RecipeService recipeService;
	
	//@Autowired
	//@Inject
	//private RecipeRepository recipeRepository;
	
	private Recipe recipe;
	
	private String recipeName;
	
	private RecipeRow row;
	
	public List<RecipeRow> getIngredients() {
		List<RecipeRow> ingredients = new ArrayList<RecipeRow>();
		ArrayList<RecipeRow> rows = recipe.getRows();
		for (RecipeRow recipeRow : rows) {
			if(recipeRow.getType()!=null && recipeRow.getType().equals(RecipeRowType.INGREDIENT)){
				System.out.println(">>>>>>>>>>>>>>>>>>>"+recipeRow.getUnit());
				ingredients.add(recipeRow);
			}
		}
		return ingredients;
	}
	
	public String getDisplayInstruction() {
		boolean ingredientBefore=false;
		StringBuffer instraction=new StringBuffer();
		ArrayList<RecipeRow> rows = recipe.getRows();
		for (RecipeRow recipeRow : rows) {
			RecipeRowType type = recipeRow.getType();
			if(type==null){
				//NOTING
			}else if(type.equals(RecipeRowType.ACTION)){
				if(ingredientBefore){
					instraction.append(". ");
					ingredientBefore=false;
				}
				instraction.append(recipeRow.getAction().getName()+" ");
			}else if(type.equals(RecipeRowType.INGREDIENT)){
				if(ingredientBefore){
					instraction.append(", ");
				}
				instraction.append(recipeRow.getIngredient().getName());
				ingredientBefore=true;
			}
		}
		instraction.append(".");
		return instraction.toString();
	}
	
	void onActivate(String recipeName){
		System.out.println("recipeListNumber:"+recipeListNumber);
		
		this.recipeName = recipeName;
		
		recipe=recipeService.findOneByName(recipeName);
		if(recipe==null){
			//TODO FORWARD
			
		}
		
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public RecipeRow getRow() {
		return row;
	}

	public void setRow(RecipeRow row) {
		this.row = row;
	}

	public NumberFormat getDecimalFormat() {
		return new DecimalFormat(",##0.00" );
	}


	/*public NumberFormat getNumberFormat() {
		//numberFormat = NumberFormat.getCurrencyInstance(currentLocale);
		//numberFormat.setMaximumFractionDigits(2);
		return numberFormat;
	}*/

	/*public void setNumberFormat(NumberFormat numberFormat) {
		this.numberFormat = numberFormat;
	}*/
	
	/*void setupRender() {
		numberFormat = NumberFormat.getInstance(currentLocale);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMaximumIntegerDigits(2);
		
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
	}*/
		
}
