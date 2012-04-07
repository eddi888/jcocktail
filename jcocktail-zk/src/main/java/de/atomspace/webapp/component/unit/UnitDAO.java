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

import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;

public class UnitDAO  extends BasicDAO<Unit, String>{
	
	public UnitDAO(Datastore ds) {
		super(ds);
	}

	public Unit findOneByName(String string) {
		Query<Unit> query = ds.createQuery(Unit.class);
		query = query.limit(1);
		query = query.field("detached").equal(false);
		query = query.field("name").equal(string);
		List<Unit> list = this.find(query).asList();
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}

}
