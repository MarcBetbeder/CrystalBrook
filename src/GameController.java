import java.util.Scanner;

public class GameController {
	
	GameEngine engine;
	
	public void startUp() {
		
		Scanner sc = new Scanner(System.in);
		String line = null;
		boolean valid = false;
		
		// Welcome Group
		System.out.println("Hello and welcome!");
		System.out.println("I am a CystalBrook electronic interface.\n");
		System.out.println("At this stage, I can keep track of the current state of the game, the score, and make sure no one plays any illegal moves!\n");
		System.out.println("Hopefully soon, I will be able to join you and play along!");
		System.out.println("But for now, are you ready to play?");
		
		// Prompt for system config.
		System.out.println("Please confirm that you have set up the system config file. y/n?");
		
		// Recieve answer.
		while (valid == false) {
			line = sc.nextLine();
			if (line.equals("y")) {
				System.out.println("Great! Proceeding to set up the game now. :)");
				
				valid = true;
				
				PropertiesReader properties = new PropertiesReader();
				try {
					this.engine = properties.initialiseGameState();
				} catch (Exception e) {
					System.err.println("Exception:" + e);
					System.err.println("The Application will now close.");
					CrystalBrook.applicationExit();
				} finally {
					sc.close();
				}
			} else if (line.equals("n")) {
				System.out.println("The Application will now close.");
				System.out.println("Please set up the system config file before restarting.");
				
				CrystalBrook.applicationExit();
			} else {
				printInvalidResponse();
			}
		}
	}
	
	public static void printInvalidResponse() {
		System.err.println("Sorry, that input is invalid! Please try again!");
	}

}