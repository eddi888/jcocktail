package de.atomspace.webapp.pages.ingredient;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import de.atomspace.webapp.component.recipe.Recipe;
import de.atomspace.webapp.component.recipe.RecipeRow;
import de.atomspace.webapp.component.recipe.RecipeRowType;
import de.atomspace.webapp.component.recipe.service.RecipeRepository;
import de.atomspace.webapp.component.recipe.service.RecipeService;

public class Edit {
	
	@Autowired
	@Inject
	private RecipeService recipeService;
	
	//@Autowired
	//@Inject
	//private RecipeRepository recipeRepository;
	
	private Recipe recipe;
	
	private RecipeRow row;
	
	private String category;
	
	public List<RecipeRow> getIngredients() {
		List<RecipeRow> ingredients = new ArrayList<RecipeRow>();
		ArrayList<RecipeRow> rows = recipe.getRows();
		for (RecipeRow recipeRow : rows) {
			if(recipeRow.getType()!=null && recipeRow.getType().equals(RecipeRowType.INGREDIENT)){
				ingredients.add(recipeRow);
			}
		}
		return ingredients;
	}
	
	public String getDisplayInstruction() {
		boolean ingredientBefore=false;
		StringBuffer instraction=new StringBuffer();
		ArrayList<RecipeRow> rows = recipe.getRows();
		for (RecipeRow recipeRow : rows) {
			RecipeRowType type = recipeRow.getType();
			if(type==null){
				//NOTING
			}else if(type.equals(RecipeRowType.ACTION)){
				if(ingredientBefore){
					instraction.append(". ");
					ingredientBefore=false;
				}
				instraction.append(recipeRow.getAction().getName()+" ");
			}else if(type.equals(RecipeRowType.INGREDIENT)){
				if(ingredientBefore){
					instraction.append(", ");
				}
				instraction.append(recipeRow.getIngredient().getName());
				ingredientBefore=true;
			}
		}
		instraction.append(".");
		return instraction.toString();
	}
	
	void onActivate(String recipeName){
		SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		recipe=recipeService.findOneByName(recipeName);
		if(recipe==null){
			recipe = new Recipe();
			
		}
		
	}
	
	void onActivate(){
		recipe = new Recipe();
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public RecipeRow getRow() {
		return row;
	}

	public void setRow(RecipeRow row) {
		this.row = row;
	}

	public NumberFormat getDecimalFormat() {
		return new DecimalFormat(",##0.00" );
	}

	public String getCategory() {
		System.out.println("getCategory: "+category);
		return category;
	}

	public void setCategory(String category) {
		System.out.println("setCategory: "+category);
		this.category = category;
	}


	List<String> onProvideCompletionsFromCategory(String partial) {
		/*List<String> matches = new ArrayList<String>();
		partial = partial.toUpperCase();

		for (String countryName : countryNames.getSet()) {
			if (countryName.startsWith(partial)) {
				matches.add(countryName);
			}
		}
		return matches;*/
		List<String> list = new ArrayList<String>();
		
		if(partial.equals("aaa")){
			
		}else{
			list.add(partial+"1");
			list.add(partial+"2");
			list.add(partial+"3");
		}
		return list;
	}
	
	@Property
    private UploadedFile file;

    public void onSuccess()
    {
        //File copied = new File("/my/file/location/" + file.getFileName());
    	System.out.println(file.getFilePath());
    	System.out.println(file.getSize());
    	System.out.println(file.getContentType());
    	System.out.println(file.getFileName());
    	InputStream x = file.getStream();
    	
    	
    	
        //file.write(copied);
    }
    
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;
    Object onUploadException(FileUploadException ex)
    {
        message = "Upload exception: " + ex.getMessage();

        return this;
    }
	
	
}
