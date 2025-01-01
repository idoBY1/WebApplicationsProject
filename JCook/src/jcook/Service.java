package jcook;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.PortableServer.ThreadPolicyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	@Autowired
	private ISerializer serializer;	
	
	public boolean saveRecipe(Recipe recipe) throws InvalidRecipeException {
		if (!validate(recipe)) return false; // also throws InvalidRecipeException
		
		if (serializer.recipeExistsByName(recipe.getName()))  
			throw new InvalidRecipeException("Recipe already exists!\n");
		
		serializer.saveRecipe(recipe);
		return true;
	}

	
	public Recipe getRecipe(String name) {
		return serializer.getRecipeByName(name);
	}
	
	// In my opinion no need for this since we have get all recipes but okay
	public List<String> getAllRecipeNames() {
		return serializer.getRecipeNames();
	}
	
	public List<Recipe> getAllRecipes() {
		return serializer.getAllRecipe();
	}
	
	public List<Recipe> getAllRecipesFromCategory(String category) {
		return serializer.getRecipesByCategory(category);
	}
	
	public void deleteRecipe(String name) throws InvalidRecipeException {
		if (serializer.recipeExistsByName(name)) 
			serializer.deleteRecipeByName(name);
		else
			throw new InvalidRecipeException("Recipe doesnt exist!");
	}
	
	public boolean editRecipe(Recipe newInfo) throws InvalidRecipeException {
		int id = newInfo.getId();
		
		if (!validate(newInfo)) return false; // also throws InvalidRecipeException
		
		Recipe oldInfo = serializer.getRecipeById(id);
		
		if (!serializer.recipeExistsById(id))
			throw new InvalidRecipeException("Recipe doesnt exist to change!");
		
		if (!newInfo.getName().equals(oldInfo.getName()))
			if (serializer.recipeExistsByName(newInfo.getName()))
				throw new InvalidRecipeException("New name already exists for a differenct recipe!");
		
		serializer.editRecipe(newInfo);
		
		return true;
	}
	
	
	// VALIDATIONS //
	
	public static boolean validate(Recipe recipe) throws InvalidRecipeException {
		String errorMsg = "";
		if (!isValidName(recipe.getName())) errorMsg += "Invalid Name!\n";
		if (!isValidCategory(recipe.getCategory())) errorMsg += "Invalid Category!\n";
		if (!isValidDescription(recipe.getDescription())) errorMsg += "Invalid Description!\n";
		if (!isValidIngredients(recipe.getIngredients())) errorMsg += "Invalid Ingredients!\n";
		if (!isValidInstructions(recipe.getInstructions())) errorMsg += "Invalid Instructions!\n";
		
		if (!errorMsg.equals(""))
			throw new InvalidRecipeException(errorMsg);
		
		return true;
	}
	
	public static boolean isValidName(String name) {
		return name != null && !name.trim().isEmpty();
	}
	
	public static boolean isValidCategory(String category) {
		return category != null && !category.trim().isEmpty();
	}
	
	public static boolean isValidDescription(String desc) {
		return desc != null && !desc.trim().isEmpty();
	}
	
	public static boolean isValidIngredients(List<String> ingredients) {
		return ingredients != null && !ingredients.isEmpty();
	}
	
	public static boolean isValidInstructions(List<String> instructions) {
		return instructions != null && !instructions.isEmpty();
	}
}
