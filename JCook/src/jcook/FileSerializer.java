package jcook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("Deserialized Object: " + recipes);
			
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
			System.out.println("Serialized data is saved in recipes.ser");
			
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
			if (recipe.getName().equals(name)) {
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
			recipes.add(recipe);
			return true;
		}
			
		return false;
	}

	@Override
	public boolean deleteRecipeByName(String name) {
		for (Recipe recipe : recipes) {
			if (recipe.getName().equals(name)) {
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
	public Recipe getRecipeByName(String name) throws CloneNotSupportedException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getName().equals(name)) {
				return recipe.clone();
			}
		}
		return null;
	}

	@Override
	public Recipe getRecipeById(int id) throws CloneNotSupportedException {
		
		for (Recipe recipe : recipes) {
			if (recipe.getId() == id) {
				return recipe.clone();
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
			if (recipe.getCategory().equals(category))
				recipeIds.add(recipe.getId());
		}
		return recipeIds;
	}

	@Override
	public List<Recipe> getAllRecipe() throws CloneNotSupportedException {
		ArrayList<Recipe> tempRecipes = new ArrayList<>();
		for (Recipe recipe : recipes) {
			tempRecipes.add(recipe.clone());
		}
		return tempRecipes;
	}

	@Override
	public List<Recipe> getRecipesByCategory(String category) throws CloneNotSupportedException {
		ArrayList<Recipe> tempRecipes = new ArrayList<>();
		for (Recipe recipe : recipes) {
			if (recipe.getCategory().equals(category))
				tempRecipes.add(recipe.clone());
		}
		return tempRecipes;
	}

}