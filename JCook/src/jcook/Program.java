package jcook;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		// Load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

		// Create UI bean
		CommandLineUserInterface ui = context.getBean("commandLineUserInterface", 
				CommandLineUserInterface.class);
		
		// Start UI
		ui.run();
		
		// Close the context
		context.close();
	}

}
