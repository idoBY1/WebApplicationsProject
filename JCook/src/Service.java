import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	@Autowired
	private ISerializer serializer;	
	
	public boolean saveRecipe(Recipe recipe) throws InvalidRecipeException {
		String errorMsg = "";
		if (!isValidName(recipe.getName())) errorMsg += "Invalid Name!\n";
		if (!isValidCategory(recipe.getCategory())) errorMsg += "Invalid Category!\n";
		if (!isValidDescription(recipe.getDescription())) errorMsg += "Invalid Description!\n";
		if (!isValidIngredients(recipe.getIngredients())) errorMsg += "Invalid Ingredients!\n";
		if (!isValidInstructions(recipe.getInstructions())) errorMsg += "Invalid Instructions!\n";
		
		if (!errorMsg.equals(""))
			throw new InvalidRecipeException(errorMsg);
		
		// Might be changed later
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
	
	// TODO: WHat are we going to do about this?
	public void editRecipe(Recipe newInfo) throws InvalidRecipeException {
		return;
	}
	
	
	// VALIDATIONS //
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
