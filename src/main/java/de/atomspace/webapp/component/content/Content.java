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

package de.atomspace.webapp.component.content;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Version;
import com.google.code.morphia.utils.IndexDirection;

public class Content {
	@Id	private ObjectId id;
	@Property private boolean published;
	@Property private boolean detached;
	@Version private Long version;
	
	@Indexed(value = IndexDirection.ASC, name = "nameIdx", unique = true, dropDups=true)
	private String name;
	
	@Property private String description;
	@Property private String keywords;
	@Property private String robots;
	
	@Property private String title;
	@Property private String body;
	
	
	public Content(){
		
	}
	
	public Content(boolean published, boolean detached, String name,
			String description, String title, String body) {
		super();
		this.published = published;
		this.detached = detached;
		this.name = name;
		this.description = description;
		this.title = title;
		this.body = body;
	}
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setRobots(String robots) {
		this.robots = robots;
	}

	public String getRobots() {
		return robots;
	}
	
}
