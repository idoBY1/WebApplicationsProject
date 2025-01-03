package jcook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TempSerializer implements ISerializer {
	private List<Recipe> recipes;
	private int currId = 1;
	
	@Override
	public boolean recipeExistsByName(String name) {
		for (Recipe r : recipes)
		{
			if (r.getName().equals(name))
				return true;
		}
		
		return false;
	}

	@Override
	public boolean recipeExistsById(int id) {
		for (Recipe r : recipes)
		{
			if (r.getId() == id)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean saveRecipe(Recipe recipe) {
		if (recipeExistsByName(recipe.getName()))
			return false;
		
		recipe.setId(currId++);
		recipes.add(recipe);
		
		return true;
	}

	@Override
	public boolean deleteRecipeByName(String name) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getName().equals(name)) {
				recipes.remove(i);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteRecipeById(int id) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getId() == id) {
				recipes.remove(i);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean editRecipe(Recipe changedRecipe) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getId() == changedRecipe.getId()) {
				recipes.set(i, changedRecipe);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Recipe getRecipeByName(String name) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getName().equals(name)) {
				return recipes.get(i);
			}
		}
		
		return null;
	}

	@Override
	public Recipe getRecipeById(int id) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getId() == changedRecipe.getId()) {
				return recipes.get(i);
			}
		}
		
		return null;
	}

	@Override
	public List<Integer> getRecipeIds() {
		List<Integer> idList = new ArrayList<>(recipes.size());
		
		for (Recipe r : recipes) {
			idList.add(r.getId());
		}
		
		return idList;
	}

	@Override
	public List<String> getRecipeNames() {
		List<String> nameList = new ArrayList<>(recipes.size());
		
		for (Recipe r : recipes) {
			nameList.add(r.getName());
		}
		
		return nameList;
	}

	@Override
	public List<Integer> getRecipesIdByCategory(String category) {
		List<Integer> idList = new ArrayList<>(recipes.size());
		
		for (Recipe r : recipes) {
			if (r.getCategory().equals(category))
				idList.add(r.getId());
		}
		
		return idList;
	}

	@Override
	public List<Recipe> getAllRecipe() {
		return recipes;
	}

	@Override
	public List<Recipe> getRecipesByCategory(String category) {
		List<Recipe> rList = new ArrayList<>(recipes.size());
		
		for (Recipe r : recipes) {
			if (r.getCategory().equals(category))
				rList.add(r);
		}
		
		return rList;
	}

}
