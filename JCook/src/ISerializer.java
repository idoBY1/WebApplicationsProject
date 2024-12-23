import java.util.List;

public interface ISerializer {
	public void saveRecipe(Recipe recipe);
	public List<Recipe> getData();
}
