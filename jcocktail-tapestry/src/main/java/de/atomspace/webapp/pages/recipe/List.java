package de.atomspace.webapp.pages.recipe;

import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.service.RecipeService;

public class List {

	@SessionAttribute
	private Integer recipeListNumber;
	
	@Autowired
	@Inject
	private RecipeService recipeService;
	
	private PageRequest pageRequest;
	
	private Page<Recipe> page;
	
	private int pageNumber;
	
	private Recipe recipe;
	
	void onActivate(){
		//System.out.println(recipeListNumber);
		//System.out.println("recipeListNumber:"+recipeListNumber);
		recipeListNumber=pageNumber;
		System.out.println("recipeListNumber:"+recipeListNumber);
		
		pageRequest = new PageRequest(pageNumber,10);
		setPage(recipeService.findAll(pageRequest));
	}
	
	void onActivate(int id){
		pageNumber = id;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public Page<Recipe> getPage() {
		return page;
	}
	
	public void setPage(Page<Recipe> page) {
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
	
	
}
