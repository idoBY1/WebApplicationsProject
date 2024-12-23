import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.springframework.stereotype.Component;

@Component
public class FileSerializer implements ISerializer {

	@Override
	public void saveData(Recipe recipe) {
		try {
			FileOutputStream fileOut = new FileOutputStream("recipes.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recipe);
			System.out.println("Serialized data is saved in recipes.ser");
			} 
		catch (IOException ioe) {
			ioe.printStackTrace();
			// throw ioe;
			}
		
	}

	@Override
	public Recipe getData() {
		try {
			FileInputStream fileIn = new FileInputStream("recipes.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Recipe recipe = (Recipe) in.readObject();
			System.out.println("Deserialized Object: " + recipe);
			return recipe;
			} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
