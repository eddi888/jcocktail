package de.atomspace.webapp.component.recipe;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recipe {
	@Id	private ObjectId id;
	@Persistent private boolean published;
	@Persistent private boolean detached;
	
	@Indexed(direction = IndexDirection.ASCENDING, name = "name", unique = true, dropDups=true)
	private String name;
	
	@Persistent private String description;
	
	@Persistent private String instruction;
	
	@Persistent private ArrayList<RecipeRow> rows = new ArrayList<RecipeRow>();

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

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public ArrayList<RecipeRow> getRows() {
		return rows;
	}

	public void setRows(ArrayList<RecipeRow> rows) {
		this.rows = rows;
	}
	
	
}
