import java.util.List;

public interface ISerializer {
	public boolean recipeExistsByName(String name);
	public boolean recipeExistsById(int id);
	public void saveRecipe(Recipe recipe);
	public Recipe getRecipeByName(String name);
	public Recipe getRecipeById(int id);
}
