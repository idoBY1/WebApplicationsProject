package jcook;

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
		recipes.add(recipe);
		
		return true;
	}

	@Override
	public boolean deleteRecipeByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecipeById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editRecipe(Recipe changedRecipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe getRecipeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe getRecipeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getRecipeIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRecipeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getRecipesIdByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> getAllRecipe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> getRecipesByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
