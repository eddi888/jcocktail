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

package de.atomspace.webapp.component.webuser;

import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;

public class WebuserService {
	private Datastore datastore;
	private WebuserDAO dao;
	
	public WebuserService(Datastore datastore) {
        this.datastore=datastore;
        this.dao=new WebuserDAO(this.datastore);
        
    }
	
	public Webuser findOneByUser(String string) {
		Query<Webuser> query = datastore.createQuery(Webuser.class);
		query = query.limit(1);
		query = query.field("detached").equal(false);
		query = query.field("user").equal(string);
		List<Webuser> list = dao.find(query).asList();
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Key<Webuser> save(Webuser entity){
		 return dao.save(entity);
	}

	public Webuser findOneByUserOrInitOne(String user) {
		Webuser webuser = this.findOneByUser(user);
		if(webuser==null){
			webuser = new Webuser();
			webuser.setDetached(false);
			webuser.setPublished(true);
			webuser.setName("NObodY");
			webuser.setUser(user);
			webuser.getRoles().add("ROLE_USER");
			dao.save(webuser);
		}
		return webuser;
	}
}
