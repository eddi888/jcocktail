package de.atomspace.webapp.component.dimension;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import de.atomspace.webapp.component.unit.Unit;

@Document
public class Dimension {
	
	@Id	private ObjectId id;
	private boolean published;
	private boolean detached;
	private DimensionType type;
	
	@Indexed(direction = IndexDirection.ASCENDING, name = "name", unique = true, dropDups=true)
	private String name;
	
	private String description;
	
	@DBRef
	private Unit unit;
	
	private List<DimensionRow> dimensionRows = new ArrayList<DimensionRow>();

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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<DimensionRow> getDimensionRows() {
		return dimensionRows;
	}

	public void setDimensionRows(List<DimensionRow> dimensionRows) {
		this.dimensionRows = dimensionRows;
	}

	public DimensionType getType() {
		return type;
	}

	public void setType(DimensionType type) {
		this.type = type;
	}

	
	
}
