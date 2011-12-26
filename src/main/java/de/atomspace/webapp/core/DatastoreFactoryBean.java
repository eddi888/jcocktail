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

package de.atomspace.webapp.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;

import de.atomspace.webapp.component.content.Content;
import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.webuser.Webuser;

public class DatastoreFactoryBean implements FactoryBean<Datastore>{
	 private Mongo mongo;
    private String name;

    @Override
    public Datastore getObject() throws Exception {
    	Morphia morphia = new Morphia();
    	Assert.notNull(mongo);
        Assert.notNull(name);
    	mongo.getDB(name);
    	
    	Datastore datastore = morphia.createDatastore(mongo, name);
    	morphia.map(Content.class);
    	morphia.map(Webuser.class);
    	morphia.map(Recipe.class);
    	morphia.map(Ingredient.class);
    	morphia.map(Unit.class);
    	
    	datastore.ensureIndexes();
    	
    	return datastore;
    }

   @Override
   public Class<?> getObjectType() {
       return DB.class;
   }

   @Override
   public boolean isSingleton() {
       return true;
   }

   @Required
   public void setMongo(Mongo mongo) {
       this.mongo = mongo;
   }

   @Required   
   public void setName(String name) {
       this.name = name;
   }
}