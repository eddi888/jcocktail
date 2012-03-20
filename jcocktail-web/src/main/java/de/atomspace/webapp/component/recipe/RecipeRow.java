/* ******************************************************************************
*    JCocktail - Webapplication for Cocktail Resource Planing and Management.   *
*    Copyright (C) 2011  Edgar Wentzlaff                                        *
*                                                                               *
*    This program is free software: you can redistribute it and/or modify       *
*    it under the terms of the GNU Affero General Public License as             *
*    published by the Free Software Foundation, either version 3 of the         *
*    License, or (at your option) any later version.                            *
*                                                                               *
*    This program is distributed in the hope that it will be useful,            *
*    but WITHOUT ANY WARRANTY; without even the implied warranty of             *
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
*    GNU Affero General Public License for more details.                        *
*                                                                               *
*    You should have received a copy of the GNU Affero General Public License   *
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.      *
*                                                                               *
****************************************************************************** */

package de.atomspace.webapp.component.recipe;

import java.math.BigDecimal;

import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;

import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.unit.Unit;

public class RecipeRow {
	/*
	 * 
	 * gekühltes Glass
	 * Aktion X
	 * Zutat 1
	 * Zutat 2
	 * Zutat 3
	 */
	@Property private String type;
	@Reference private Ingredient ingredient;
	@Reference private Unit unit;
	@Property private double amount;
	
	
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
		return new BigDecimal(amount);
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount.doubleValue();
	}

}
