import java.util.Date;
import java.util.List;

public class Recipe {
	private int id;
	private String name;
	private String category;
	private String description;
	private List<String> ingredients;
	private List<String> instructions;
	private Date dateAdded;
	private Date dateLatestChange;
	
	public int getId() {
		return id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory() {
		this.category = category;
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
		this.ingredients = ingredients;
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
	
	public void setDateAdded(Date date) {
		dateAdded = date;
	}

	public Date getDateLatestChange() {
		return dateLatestChange;
	}

	public void setDateLatestChange(Date dateLatestChange) {
		this.dateLatestChange = dateLatestChange;
	}
	
	
	
}
