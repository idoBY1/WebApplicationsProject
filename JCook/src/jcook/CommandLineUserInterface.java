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
	}
	
	public void run() {
		Scanner inputScanner = new Scanner(System.in);
		String[] currInput;
		
		running = true;
		
		while (running) {
			currInput = inputScanner.nextLine().toLowerCase().split(" ");
			
			commandMap.get(currInput[0]).accept(currInput);
		}
	}
	
	private void quitCommand(String[] input) {
		running = false;
		
		System.out.println("Exiting the program...");
	}
}
