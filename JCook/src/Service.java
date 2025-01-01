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
		
		serializer.saveRecipe(recipe);
		return true;
	}
	
	public boolean isValidName(String name) {
		return name != null && !name.trim().isEmpty();
	}
	
	public boolean isValidCategory(String category) {
		return category != null && !category.trim().isEmpty();
	}
	
	public boolean isValidDescription(String desc) {
		return desc != null && !desc.trim().isEmpty();
	}
	
	public boolean isValidIngredients(List<String> ingredients) {
		return ingredients != null && !ingredients.isEmpty();
	}
	
	public boolean isValidInstructions(List<String> instructions) {
		return instructions != null && !instructions.isEmpty();
	}
	
	public Recipe getRecipe(String name) {
		return serializer.getRecipeByName(name);
	}
	
	public List<Recipe> getAllRecipes() {
		return null;
		//return serializer.getAllRecipes();
	}
	
	public List<Recipe> getAllRecipesFromCategory(String category) {
		return null;
		//return serializer.getRecipesByCategory(category);
	}
}
