package jcook.DAO;
import java.io.IOException;
import java.util.List;

import jcook.Recipe;
import jcook.Exceptions.NoRecipeException;

public interface ISerializer {
	// Checks if a recipe exists in the DB by its name
	public boolean recipeExistsByName(String name);
	
	// Checks if a recipe exists in the DB by its id
	public boolean recipeExistsById(int id);
	
	// Save a recipe to the DB. Do nothing if name already exists.
	// Return true if added successfully and false if not added
	public void saveRecipe(Recipe recipe) throws IOException;
	
	// Delete a recipe from the DB. Return true if deleted successfully 
	// and false if not (if name doesn't exist, do nothing and return false)
	public void deleteRecipeByName(String name) throws NoRecipeException, IOException;
	
	// Delete a recipe from the DB. Return true if deleted successfully 
	// and false if not (if id doesn't exist, do nothing and return false)
	public void deleteRecipeById(int id) throws NoRecipeException, IOException;
	
	// Receives a recipe and changes the recipe in the DB with the same id
	// to be equal to the received recipe. Return true if changed successfully 
	// and false if not (if id doesn't exist in the DB, do nothing and return false)
	public void editRecipe(Recipe changedRecipe) throws NoRecipeException, IOException;
	
	// Retrieve a recipe from the DB. Return null if not found
	public Recipe getRecipeByName(String name) throws NoRecipeException;
	
	// Retrieve a recipe from the DB. Return null if not found
	public Recipe getRecipeById(int id) throws NoRecipeException;
	
	// Return in a list all of the ids of the recipes currently stored 
	// in the DB (Return an empty list if the DB doesn't contain any recipe)
	public List<Integer> getRecipeIds();
	
	// Return in a list all of the names of the recipes currently stored 
	// in the DB (Return an empty list if the DB doesn't contain any recipe)
	public List<String> getRecipeNames();
	
	// Retrieve the names of all of the recipes in the DB of a specific category
	public List<Integer> getRecipesIdByCategory(String category);
	
	// Retrieve all of the recipes in the DB
	public List<Recipe> getAllRecipe();
	
	// Retrieve all of the recipes in the DB of a specific category
	public List<Recipe> getRecipesByCategory(String category);

}
