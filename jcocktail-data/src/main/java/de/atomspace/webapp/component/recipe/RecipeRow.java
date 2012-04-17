package de.atomspace.webapp.component.recipe;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import de.atomspace.webapp.component.action.Action;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.unit.Unit;

@Document
public class RecipeRow {
	/*
	 * 
	 * Action X
	 * Ingredient 1
	 * Ingredient 2
	 * Ingredient 3
	 * Action y
	 */
	
	private RecipeRowType type;
	
	@DBRef 
	@Persistent
	private Ingredient ingredient;		
	
	@DBRef 
	private Action action;				
	
	@DBRef
	private Unit unit;
	
	private BigDecimal amount;
	
	public RecipeRow() {
		
	}
	
	public RecipeRow(Ingredient ingredient, Unit unit, BigDecimal amount) {
		this.type=RecipeRowType.INGREDIENT;
		this.ingredient=ingredient;
		this.unit=unit;
		this.amount=amount;
	}
	
	public RecipeRow(Action action) {
		this.type=RecipeRowType.ACTION;
		this.action=action;
		this.amount=BigDecimal.ZERO;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	/**
	 * unit definition only for display
	 * @return
	 */
	public Unit getUnit() {
		return unit;
	}
	
	/**
	 * unit definition only for display
	 * @param unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	/**
	 * Amount of the needed ingredient, Its the Dimension Base Unit
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Amount of the needed ingredient, Its the Dimension Base Unit
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public RecipeRowType getType() {
		return type;
	}

	public void setType(RecipeRowType type) {
		this.type = type;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getDisplayAmount(){
		//TODO REALY TESTING
		List<DimensionRow> dimensionRows = ingredient.getDimension().getDimensionRows();
		for (DimensionRow dimensionRow : dimensionRows) {
			if(dimensionRow.getUnit().getName().equals(unit.getName())){
				return dimensionRow.getFactor().multiply(amount);
			}
			
		}
		return BigDecimal.ONE;
	}
	
}
