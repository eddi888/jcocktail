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

package de.atomspace.webapp.component.ingredient;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.SimpleListModel;

public class IngredientComposer  extends GenericForwardComposer {
	
	private Ingredient ingredient;
	private IngredientService service;
	private Label infoLbl;
	private Menubar naviBar;
	private int id;
	private int limit;
	
	//private Button forwardBtn;
	//private Button backwardBtn;
	private Listbox listBox;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		id = (Integer) Executions.getCurrent().getAttribute("id");
		limit = (Integer) Executions.getCurrent().getAttribute("limit");
		
		service = (IngredientService) SpringUtil.getBean("ingredientService");
		
		initNaviBar();
		initListBox();
	}
	
	private void initNaviBar() {
		long count = service.count();
		final long maxId = count/limit;
		
		Menuitem backFirst = new Menuitem("|<");
		backFirst.setHref("/web/ingredients/0");
		backFirst.setParent(naviBar);
		
		Menuitem backOne = new Menuitem("<<");
		backOne.setHref("/web/ingredients/"+(id-1));
		backOne.setParent(naviBar);
		
		int j=0;
		for (int i=id;i<maxId;i++) {
			j++;
			Menuitem goToOne = new Menuitem(""+i);
			goToOne.setHref("/web/ingredients/"+i);
			goToOne.setParent(naviBar);
			if(j==10) break;
		}
		
		Menuitem nextOne = new Menuitem(">>");
		nextOne.setHref("/web/ingredients/"+(id+1));
		nextOne.setParent(naviBar);
		
		Menuitem nextLast = new Menuitem(">|");
		nextLast.setHref("/web/ingredients/"+maxId);
		nextLast.setParent(naviBar);
		
	}

	private void initListBox(){
		List<Ingredient> list = service.findAll(id,limit);
		ListModel model = new SimpleListModel(list);
		listBox.setModel(model );
		listBox.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem item, Object object) throws Exception {
				Ingredient ingredient = (Ingredient) object;
				
				Listcell cellName = new Listcell();
				new Label(ingredient.getName()).setParent(cellName);
				cellName.setParent(item);
				
				/*Listcell cellDesc = new Listcell();
				new Label(ingredient.getDescription()).setParent(cellDesc);
				cellDesc.setParent(item);*/
				
				Listcell cellAlcohol = new Listcell();
				new Label(ingredient.getAlcohol().toPlainString()+"%").setParent(cellAlcohol);
				cellAlcohol.setParent(item);
				
				Listcell cellView = new Listcell();
				new Label("VIEW...").setParent(cellView);
				cellView.setParent(item);
				
				Button button = new Button();
				button.setHref("");
				
			}
		});
	}
}
