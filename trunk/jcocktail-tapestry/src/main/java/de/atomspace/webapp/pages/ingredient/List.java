package de.atomspace.webapp.pages.ingredient;

import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.atomspace.webapp.component.ingredient.Ingredient;
import de.atomspace.webapp.component.ingredient.service.IngredientService;

public class List {

	@SessionAttribute
	private Integer ingredientListNumber;
	
	@Autowired
	@Inject
	private IngredientService ingredientService;
	
	private PageRequest pageRequest;
	
	private Page<Ingredient> page;
	
	private int pageNumber;
	
	private Ingredient ingredient;
	
	void onActivate(){
		setIngredientListNumber(pageNumber);		
		pageRequest = new PageRequest(pageNumber,10);
		setPage(ingredientService.findAll(pageRequest));
	}
	
	void onActivate(int id){
		pageNumber = id;
	}
	
	public Page<Ingredient> getPage() {
		return page;
	}
	
	public void setPage(Page<Ingredient> page) {
		this.page = page;
	}
	
	public int getPageNr(){
		return page.getNumber();
	}
	
	public int getPreviousPageNr(){
		return page.getNumber()-1;
	}
	
	public int getNextPageNr(){
		return page.getNumber()+1;
	}
	
	public int getLastPageNr(){
		return page.getTotalPages()-1;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getIngredientListNumber() {
		return ingredientListNumber;
	}

	public void setIngredientListNumber(Integer ingredientListNumber) {
		this.ingredientListNumber = ingredientListNumber;
	}
	
	
}
