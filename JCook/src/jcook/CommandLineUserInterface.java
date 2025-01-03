package jcook;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineUserInterface {
	private Map<String, Consumer<String[]>> commandMap = new HashMap<>();	
	private boolean running;
	private Scanner inputScanner;
	
	@Autowired
	private Service service;
	
	@PostConstruct
	public void initialize() {
		inputScanner = new Scanner(System.in);
		
		commandMap.put("quit", this::quitCommand);
		commandMap.put("help", this::helpCommand);
		commandMap.put("add", this::addRecipeCommand);
	}
	
	@PreDestroy
	public void destruct() {
		inputScanner.close();
	}
	
	public void run() {
		String[] currInput;
		Consumer<String[]> command;
		
		running = true;
		
		System.out.println("Welcome to the Digital Recipe Book. Enter 'quit' to "
				+ "exit and 'help' to find out about the available commands.");
		
		while (running) {
			System.out.println(); // Create additional space for readability
			
			currInput = inputScanner.nextLine().toLowerCase().split(" ");
			
			System.out.println(); // Create additional space for readability
			
			command = commandMap.get(currInput[0]);
			
			if (command == null) {
				System.out.println(String.format("The command '%s' is not defined", 
						currInput[0]));
			}
			else {
				command.accept(currInput);
			}
		}
	}
	
	private void quitCommand(String[] input) {
		if (input.length > 1 && input[1].equals("-h")) {
			System.out.println("Exits the program."
					+ "\n\n- '" + input[0] + "': Exit program");
			
			return;
		}
		
		running = false;
		
		System.out.println("Exiting the program...");
	}
	
	private void helpCommand(String[] input) {
		if (input.length > 1 && input[1].equals("-h")) {
			System.out.println("Provides information on the available commands."
					+ "\n\n- '" + input[0] + "': provides a list of the available commands"
					+ "\n\n- '" + input[0] + " {command}': provides additional information on the "
					+ "command.");
			
			return;
		}
		
		if (input.length > 1 && commandMap.containsKey(input[1])) {
			commandMap.get(input[1]).accept(new String[] {input[1], "-h"});
		}
		else {
			System.out.println("Available commands: ");
			
			for (String s : commandMap.keySet()) {
				System.out.print(s + "  ");
			}
			
			System.out.println("\n\nEnter 'help {command}' or '{command} -h' for "
					+ "additional info on a specific command.");
		}
	}
	
	private void addRecipeCommand(String[] input) {
		if (input.length > 1 && input[1].equals("-h")) {
			System.out.println("Adds a new recipe to the recipe book."
				+ "\n\n- '" + input[0] + "': create a new recipe by filling the fields one by one");
			
			return;
		}
				
		Recipe r = new Recipe();
		
		inputName(r, "Enter name for the recipe: ");
		inputCategory(r, "Enter a category for the recipe");
		inputDescription(r, "Enter description of the recipe:");
		inputIngredients(r, "Enter the ingredients needed for the recipe:");
		inputInstructions(r, "Enter the instructions how to make the recipe:");
		
		r.setDateAdded(new Date());
		r.setDateLatestChange(new Date());
		
		try {
			if (service.saveRecipe(r)) {
				System.out.println("Recipe saved successfully");
			}
			else {
				System.out.println("Failed to save recipe");
			}
		}
		catch (InvalidRecipeException e) {
			System.out.println("Failed to save recipe. Errors: ");
			System.out.println(e.getMessage());
		}
	}
	
	private void readRecipeCommand(String[] input) {
		if (input.length < 2) {
			System.out.println("Invalid arguments! Provide a recipe to read (missing recipe name)");
			return;
		}
		
		if (input[1].equals("-h")) {
			System.out.println("Show a recipe from the recipe book."
				+ "\n\n- '" + input[0] + " {recipeName}': show the recipe with the name provided"
				+ "\n\n- '" + input[0] + " -i {recipeId}': show the recipe with the ID provided"
				+ "\n\n- '" + input[0] + " -a': show all recipes sorted by name");
			
			return;
		}
		else if (input[1].equals("-i")) {
			if (input.length < 3) {
				System.out.println("Invalid arguments! Provide a recipe to read (missing recipe ID)");
				return;
			}
			
			
		}
		else {
			
		}
	}
	
	private void inputName(Recipe r, String msg) {
		System.out.println(msg);
		r.setName(inputScanner.nextLine());
	}
	
	private void inputCategory(Recipe r, String msg) {
		System.out.println(msg);
		r.setCategory(inputScanner.nextLine());
	}
	
	private void inputDescription(Recipe r, String msg) {
		System.out.println(msg + " (write 'fin' to end description)\n");
		
		String currWhole = "";
		String currPart = inputScanner.next();
		while (!currPart.equals("fin")) {
			currWhole += (currPart + " ");
			currPart = inputScanner.next();
		}
		
		r.setDescription(currWhole);
		inputScanner.nextLine(); // clear input
	}
	
	private void inputIngredients(Recipe r, String msg) {
		System.out.println(msg + " (write 'fin' to end ingredients list)\n");
		
		List<String> currWhole = new ArrayList<>();
		String currPart = inputScanner.nextLine();
		while (!currPart.equals("fin")) {
			currWhole.add(currPart);
			currPart = inputScanner.nextLine();
		}
		
		r.setIngredients(currWhole);
	}
	
	private void inputInstructions(Recipe r, String msg) {
		System.out.println(msg + " (write 'fin' to end instructions)\n");
		
		List<String> currWhole = new ArrayList<>();
		String currPart = inputScanner.nextLine();
		while (!currPart.equals("fin")) {
			currWhole.add(currPart);
			currPart = inputScanner.nextLine();
		}
		
		r.setInstructions(currWhole);
	}
	
	private void printRecipe(Recipe r) {
		System.out.println("---- " + r.getName() + " ----");
		System.out.println("Category: " + r.getCategory());
		System.out.println("Description: " + r.getDescription());
		System.out.println("\nIngredients: \n");
		
		for (String s : r.getIngredients()) {
			System.out.println("- " + s);
		}
		
		System.out.println("\nInstructions: \n");
		
		for (int i = 0; i < r.getInstructions().size(); i++) {
			System.out.println((i + 1) + ". " + r.getInstructions().get(i));
		}
		
		System.out.println();
	}
}
