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

package de.atomspace.webapp.component.unit;

import java.math.BigDecimal;
import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.annotations.Version;
import com.google.code.morphia.utils.IndexDirection;

public class Unit {
	/*
	 * Menge (Stück)Stk
Volumen (Liter)l
Länge (Meter)m
Fläche (m²)m²
Masse (Kilogramm)
Zeit (Sekunden)
Temperatur (Kelvin)
Lichtstärke (Candela)
Stoffmenge (Mol)
Stromstärke (Ampere)

	 */
	@Transient public final static int DIMENSION_MENGE=1;
	@Transient public final static int DIMENSION_VOLUMEN=1;
	@Transient public final static int DIMENSION_MASSE=2;
	@Transient public final static int DIMENSION_FLAECHE=2;
	@Transient public final static int DIMENSION_LAENGE=2;
	@Transient public final static int DIMENSION_ZEIT=2;
	@Transient public final static int DIMENSION_TEMPERATUR=2;
	@Transient public final static int DIMENSION_LICHTSTAERKE=2;
	@Transient public final static int DIMENSION_STOFFMENGE=2;
	@Transient public final static int DIMENSION_STROMSTAERKE=2;
	

	
	@Id	private ObjectId id;
	@Property private boolean published;
	@Property private boolean detached;
	@Version Long version;
	
	@Indexed(value = IndexDirection.ASC, name = "nameIdx", unique = true, dropDups=true)
	private String name;
	
	@Property private String description;
	@Property private int dimension;
	
	@Reference private Unit basisUnit;
	
	@Property private Double basisFactor;
	@Transient private BigDecimal basisFactorTransistent;
	
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
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public Unit getBasisUnit() {
		return basisUnit;
	}
	public void setBasisUnit(Unit basisUnit) {
		this.basisUnit = basisUnit;
	}
	public BigDecimal getBasisFactor() {
		return new BigDecimal(basisFactor);
	}
	public void setBasisFactor(BigDecimal basisFactor) {
		this.basisFactor = basisFactor.doubleValue();
	}
	
	
	
}
