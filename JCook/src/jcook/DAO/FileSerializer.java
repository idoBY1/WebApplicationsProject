package jcook.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import jcook.Recipe;
import jcook.Exceptions.NoRecipeException;

@Component
public class FileSerializer implements ISerializer {
	
	private List<Recipe> recipes;
	
	@PostConstruct
	public void getRecipes() {

		try {
		FileInputStream fileIn = new FileInputStream("recipes.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		recipes = (List<Recipe>) in.readObject();
		fileIn.close();
		in.close();
		} catch (IOException | ClassNotFoundException e) {
			recipes = new ArrayList<>();
		}
	}
	
	public void SerializeData(List<Recipe> recipes) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("recipes.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(recipes);
//			System.out.println("Serialized data is saved in recipes.ser");
		
		fileOut.close();
		out.close();
	}

	
	@Override
	public boolean recipeExistsByName(String name) {

		for (Recipe recipe : recipes) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean recipeExistsById(int id) {

		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveRecipe(Recipe recipe) throws IOException {
		
		if (recipes.isEmpty())
			recipe.setId(1);
		else
			recipe.setId(recipes.get(recipes.size() - 1).getId() + 1);
		
		recipes.add(recipe);

		
		SerializeData(recipes);
	}

	@Override
	public void deleteRecipeByName(String name) throws NoRecipeException, IOException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				recipes.remove(recipe);
				SerializeData(recipes);
				return;
			}
		}
		
		throw new NoRecipeException("There are no recipes with the name " + name);
	}

	@Override
	public void deleteRecipeById(int id) throws NoRecipeException, IOException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				recipes.remove(recipe);
				SerializeData(recipes);
				return;
			}
		}
		throw new NoRecipeException("There are no recipes with the id " + id);
	}

	@Override
	public void editRecipe(Recipe changedRecipe) throws NoRecipeException, IOException {
		if(recipes.contains(changedRecipe)) {
			recipes.set(recipes.indexOf(changedRecipe), changedRecipe);
			SerializeData(recipes);
			return;
		}
		
		throw new NoRecipeException("There is no recipe with the id " + changedRecipe.getId());
	}

	@Override
	public Recipe getRecipeByName(String name) throws NoRecipeException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		
		throw new NoRecipeException("There is no recipe with this name");
	}

	@Override
	public Recipe getRecipeById(int id) throws NoRecipeException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				return recipe;
			}
		}
		
		throw new NoRecipeException("There is no recipe with this name");
	}

	@Override
	public List<Integer> getRecipeIds() {
		List<Integer> recipeIds = new ArrayList<Integer>();
		for (Recipe recipe : recipes) {
			recipeIds.add(recipe.getId());
		}
		return recipeIds;
	}

	@Override
	public List<String> getRecipeNames() {
		List<String> recipeNames = new ArrayList<String>();
		for (Recipe recipe : recipes) {
			recipeNames.add(recipe.getName());
		}
		return recipeNames;
	}

	@Override
	public List<Integer> getRecipesIdByCategory(String category) {
		List<Integer> recipeIds = new ArrayList<Integer>();
		for (Recipe recipe : recipes) {
			if (recipe.getCategory().equalsIgnoreCase(category))
				recipeIds.add(recipe.getId());
		}
		return recipeIds;
	}

	@Override
	public List<Recipe> getAllRecipe() {
		ArrayList<Recipe> tempRecipes = new ArrayList<>();
		for (Recipe recipe : recipes) {
			tempRecipes.add(recipe);
		}
		return tempRecipes;
	}

	@Override
	public List<Recipe> getRecipesByCategory(String category) {
		ArrayList<Recipe> tempRecipes = new ArrayList<>();
		for (Recipe recipe : recipes) {
			if (recipe.getCategory().equalsIgnoreCase(category))
				tempRecipes.add(recipe);
		}
		return tempRecipes;
	}

}