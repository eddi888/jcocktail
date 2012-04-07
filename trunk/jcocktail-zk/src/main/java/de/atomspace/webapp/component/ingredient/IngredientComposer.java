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
		listBox.setItemRenderer(new ListitemRenderer<Ingredient>() {

			//TODO PARAMETER 3
			@Override
			public void render(Listitem item, Ingredient ingredient, int x) throws Exception {
				//Ingredient ingredient = (Ingredient) object;
				
				Listcell cellName = new Listcell();
				new Label(ingredient.getName()).setParent(cellName);
				cellName.setParent(item);
				
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
