package jcook;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TempSerializer implements ISerializer {
	@Override
	public boolean recipeExistsByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recipeExistsById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
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
