package de.atomspace.webapp.component.recipe;

import java.math.BigDecimal;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import de.atomspace.webapp.component.action.Action;
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
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
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
	
}
