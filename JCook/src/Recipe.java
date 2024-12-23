import java.util.Date;
import java.util.List;

public class Recipe {
	private int id;
	private String name;
	private String description;
	private List<String> ingredients;
	private List<String> instructions;
	private Date dateAdded;
	
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		//
	}
	
	public List<String> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	
	
	
	
}
