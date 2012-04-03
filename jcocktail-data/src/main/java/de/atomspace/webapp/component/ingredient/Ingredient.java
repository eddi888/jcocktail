package de.atomspace.webapp.component.ingredient;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import de.atomspace.webapp.component.dimension.Dimension;

@Document
public class Ingredient {
	@Id	private ObjectId id;
	private boolean published;
	private boolean detached;
	
	@Indexed(direction = IndexDirection.ASCENDING, name = "name", unique = true, dropDups=true)
	private String name;
	
	private String description;
	
	private Dimension dimension;
	
	private BigDecimal alcohol;
	
	private boolean planningFunction;
	
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
	public BigDecimal getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(BigDecimal alcohol) {
		this.alcohol = alcohol;
	}
	public boolean isPlanningFunction() {
		return planningFunction;
	}
	public void setPlanningFunction(boolean planningFunction) {
		this.planningFunction = planningFunction;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	
	
	
}
