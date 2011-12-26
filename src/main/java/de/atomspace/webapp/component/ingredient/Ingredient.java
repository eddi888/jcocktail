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

package de.atomspace.webapp.component.ingredient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Version;
import com.google.code.morphia.utils.IndexDirection;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.UnitTransformRow;

public class Ingredient {
	@Id	private ObjectId id;
	@Property private boolean published;
	@Property private boolean detached;
	@Version private Long version;
	
	@Indexed(value = IndexDirection.ASC, name = "nameIdx", unique = true, dropDups=true)
	private String name;
	
	@Property private String description;
	
	@Reference private Unit unit;
	//private Image image;
	@Embedded private List<UnitTransformRow> rows = new ArrayList<UnitTransformRow>();
	
	@Property private int favorite; //private Client Object between 0-5 0=bad/5=good
	@Property private Double alcohol;
	
	@Property private boolean dispo;
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public boolean isDetached() {
		return detached;
	}
	public void setDetached(boolean detached) {
		this.detached = detached;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public int getFavorite() {
		return favorite;
	}
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
	public BigDecimal getAlcohol() {
		return new BigDecimal(this.alcohol);
	}
	public void setAlcohol(BigDecimal alcohol) {
		this.alcohol = alcohol.doubleValue();
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public boolean isDispo() {
		return dispo;
	}
	
	
	
}
