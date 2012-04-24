package de.atomspace.webapp.pages.ingredient;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.service.IngredientService;

public class View {
	
	@SessionAttribute
	private Integer ingredientListNumber;
	
	@Autowired
	@Inject
	private IngredientService ingredientService;
	
	private Ingredient ingredient;
	
	private DimensionRow row;

	public List<DimensionRow> getDimensionRows(){
		return ingredient.getDimension().getDimensionRows();
	}
	
	void onActivate(String ingredientName){
		setIngredient(ingredientService.findOneByName(ingredientName));
		if(getIngredient()==null){
			//TODO FORWARD TO EDIT
		}
	}
	
	public NumberFormat getDecimalFormat() {
		return new DecimalFormat(",##0.00" );
	}

	public Integer getIngredientListNumber() {
		return ingredientListNumber;
	}

	public void setIngredientListNumber(Integer ingredientListNumber) {
		this.ingredientListNumber = ingredientListNumber;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	public DimensionRow getRow() {
		return row;
	}


	public void setRow(DimensionRow row) {
		this.row = row;
	}
		
}
