package jcook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class FileSerializer implements ISerializer {
	
	private List<Recipe> recipes;
	
	@PostConstruct
	public void getRecipes() {
		try {
			FileInputStream fileIn = new FileInputStream("recipes.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			recipes = (List<Recipe>) in.readObject();
//			System.out.println("Deserialized Object: " + recipes);
			
			fileIn.close();
			in.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			recipes = new ArrayList<>();
		}
	}
	
	@PreDestroy
	public void SerializeData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("recipes.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recipes);
//			System.out.println("Serialized data is saved in recipes.ser");
			
			fileOut.close();
			out.close();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
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
	public boolean saveRecipe(Recipe recipe) {
		if(!recipeExistsByName(recipe.getName())) {
			if (recipes.isEmpty())
				recipe.setId(1);
			else
				recipe.setId(recipes.get(recipes.size() - 1).getId() + 1);
			
			recipes.add(recipe);
			return true;
		}
			
		return false;
	}

	@Override
	public boolean deleteRecipeByName(String name) {
		for (Recipe recipe : recipes) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				recipes.remove(recipe);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteRecipeById(int id) {
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				recipes.remove(recipe);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean editRecipe(Recipe changedRecipe) {
		for (Recipe recipe : recipes) {
			if ( recipe.getId() == changedRecipe.getId()) {
				recipe.setName(changedRecipe.getName());
				recipe.setCategory(changedRecipe.getCategory());
				recipe.setDescription(changedRecipe.getDescription());
				recipe.setIngredients(changedRecipe.getIngredients());
				recipe.setInstructions(changedRecipe.getInstructions());
				recipe.setDateLatestChange(changedRecipe.getDateLatestChange());
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Recipe getRecipeByName(String name) {
		
		for (Recipe recipe : recipes) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public Recipe getRecipeById(int id) {
		
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				return recipe;
			}
		}
		return null;
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