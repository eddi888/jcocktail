package de.atomspace.webapp.component.unit;

import java.math.BigDecimal;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Reference;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.annotations.Version;
import com.google.code.morphia.utils.IndexDirection;
*/

@Document
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
	@Persistent private boolean published;
	@Persistent private boolean detached;
	
	@Indexed(direction = IndexDirection.ASCENDING, name = "name", unique = true, dropDups=true)
	private String name;
	
	@Persistent private String description;
	//@Persistent private int dimension;
	
	//@DBRef private Unit basisUnit;
	
	//@Persistent private Double basisFactor;
	//@Transient private BigDecimal basisFactorTransistent;
	
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
}