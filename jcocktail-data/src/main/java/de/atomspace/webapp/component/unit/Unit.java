package de.atomspace.webapp.component.unit;

import javax.annotation.Nonnull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;



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
	private boolean published;
	
	@Indexed
	private boolean detached;
	
	private long createDate;
	
	private long updateDate;
	
	@DBRef
	private String webUser;
	
	
	@Indexed(direction = IndexDirection.ASCENDING, name = "name", unique = true, dropDups=true)
	
	private String name;
	
	private String description;
	
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
