package jcook;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recipe implements Comparable<Recipe>, Cloneable, Serializable {
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (!(obj instanceof Recipe))
			return false;
		
		Recipe other = (Recipe)obj;
		
		return id == other.id;
	}
	
	@Override
	public int compareTo(Recipe other) {
		// Compare the recipes by name
		return name.compareTo(other.name);
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", ingredients=" + ingredients + ", instructions=" + instructions + ", dateAdded=" + dateAdded
				+ ", dateLatestChange=" + dateLatestChange + "]";
	}
	
	@Override
	public Recipe clone() throws CloneNotSupportedException {
		 Recipe clonedRecipe = (Recipe) super.clone();
		 clonedRecipe.dateAdded = (Date) dateAdded.clone();
		 clonedRecipe.dateLatestChange = (Date) dateLatestChange.clone();
		 
		  if (ingredients != null) {
		        clonedRecipe.ingredients = new ArrayList<>(ingredients);
		  }
		  if (instructions != null) {
		        clonedRecipe.instructions = new ArrayList<>(instructions);
		  }
		 
		 
		return clonedRecipe;
	}
}
