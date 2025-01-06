package jcook;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import jcook.DAO.ISerializer;
import jcook.Exceptions.InvalidRecipeException;

@Component
@PropertySource("classpath:params.properties")
public class Service {
	@Autowired
	private ISerializer serializer;

	@Value("${maxNameLen}")
	private int maxNameLength;

	@Value("${maxDescLen}")
	private int maxDescriptionLength;

	@Value("${maxIngredientsLen}")
	private int maxIngredients;

	@Value("${maxInstructionsLen}")
	private int maxInstructions;

	public boolean saveRecipe(Recipe recipe) throws InvalidRecipeException {
		if (!validate(recipe))
		 {
			return false; // also throws InvalidRecipeException
		}

		if (serializer.recipeExistsByName(recipe.getName())) {
			throw new InvalidRecipeException("Recipe already exists!\n");
		}

		serializer.saveRecipe(recipe);
		return true;
	}

	public Recipe getRecipe(String name) {
		return serializer.getRecipeByName(name);
	}

	public Recipe getRecipeClone(String name) throws CloneNotSupportedException {
		Recipe r = serializer.getRecipeByName(name);

		if (r == null) {
			return null;
		}

		return r.clone();
	}

	public Recipe getRecipe(int id) {
		return serializer.getRecipeById(id);
	}

	public List<Recipe> getAllRecipes() {
		return serializer.getAllRecipe();
	}

	public List<Recipe> getAllRecipesFromCategory(String category) {
		return serializer.getRecipesByCategory(category);
	}

	public void deleteRecipe(String name) throws InvalidRecipeException {
		if (serializer.recipeExistsByName(name)) {
			serializer.deleteRecipeByName(name);
		} else {
			throw new InvalidRecipeException("Recipe doesnt exist!");
		}
	}

	public boolean editRecipe(Recipe newInfo) throws InvalidRecipeException {
		int id = newInfo.getId();

		if (!validate(newInfo))
		 {
			return false; // also throws InvalidRecipeException
		}

		if (!serializer.recipeExistsById(id)) {
			throw new InvalidRecipeException("Recipe doesnt exist to change!");
		}

		Recipe oldInfo = serializer.getRecipeById(id);

		if (!newInfo.getName().equalsIgnoreCase(oldInfo.getName())) {
			if (serializer.recipeExistsByName(newInfo.getName())) {
				throw new InvalidRecipeException("New name already exists for a differenct recipe!");
			}
		}

		serializer.editRecipe(newInfo);

		return true;
	}


	// VALIDATIONS //

	public boolean validate(Recipe recipe) throws InvalidRecipeException {
		String errorMsg = "";

		if (!isValidName(recipe.getName())) {
			errorMsg += "Invalid Name!\n";
		}
		if (!isValidCategory(recipe.getCategory())) {
			errorMsg += "Invalid Category!\n";
		}
		if (!isValidDescription(recipe.getDescription())) {
			errorMsg += "Invalid Description!\n";
		}
		if (!isValidIngredients(recipe.getIngredients())) {
			errorMsg += "Invalid Ingredients!\n";
		}
		if (!isValidInstructions(recipe.getInstructions())) {
			errorMsg += "Invalid Instructions!\n";
		}

		if (!errorMsg.equals("")) {
			throw new InvalidRecipeException(errorMsg);
		}

		return true;
	}

	public boolean isValidName(String name) {
		return name != null && !name.trim().isEmpty() && name.length() <= maxNameLength;
	}

	public boolean isValidCategory(String category) {
		return category != null && !category.trim().replaceAll("[ \n\t]", "").isEmpty();
	}

	public boolean isValidDescription(String desc) {
		return desc != null && !desc.trim().replaceAll("[ \n\t]", "").isEmpty() && desc.length() <= maxDescriptionLength;
	}

	public boolean isValidIngredients(List<String> ingredients) {
		return ingredients != null && !ingredients.isEmpty() && ingredients.size() <= maxIngredients;
	}

	public boolean isValidInstructions(List<String> instructions) {
		return instructions != null && !instructions.isEmpty() && instructions.size() <= maxInstructions;
	}
}
