package jcook;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineUserInterface {
	private Map<String, Consumer<String[]>> commandMap = new HashMap<>();	
	private boolean running;
	
	@Autowired
	private Service service;
	
	@PostConstruct
	public void initialize() {
		commandMap.put("quit", this::quitCommand);
		commandMap.put("help", this::helpCommand);
		commandMap.put("addrecipe", this::addRecipeCommand);
	}
	
	public void run() {
		Scanner inputScanner = new Scanner(System.in);
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
				+ "\n\n- '" + input[0] + "': create a new recipe by filling the fields one by one"
				+ "\n\n- '" + input[0] + " {name} {category} {description} {ingredients} "
				+ "{cooking instructions}': create a new recipe by filling all of the fields at once");
			
			return;
		}
	}
}
