package main;

import java.util.List;
import java.util.Scanner;

public class GameController {
	
	GameEngine engine;
	Scanner sc = new Scanner(System.in);
	String line = null;
	boolean validInput = false;
	
	public void startUp() {
		
		this.validInput = false;
		line = null;
		
		// Welcome Group
		System.out.println("Hello and welcome!");
		System.out.println("I am a CystalBrook electronic interface.\n");
		System.out.println("At this stage, I can keep track of the current state of the game, the score, and make sure no one plays any illegal moves!\n");
		System.out.println("Hopefully soon, I will be able to join you and play along!");
		System.out.println("\nBut for now, are you ready to play?");
		
		// Prompt for system config.
		System.out.println("Please confirm that you have set up the system config file. y/n?");
		
		// Recieve answer.
		while (!validInput) {
			line = sc.nextLine();
			if (line.equals("y")) {
				System.out.println("\nGreat! Proceeding to set up the game now. :)\n");
				
				validInput = true;
				
				PropertiesReader properties = new PropertiesReader();
				try {
					this.engine = properties.initialiseGameState(this);

					System.out.println("\nLet's set up the players of the game.");
                    System.out.println("Starting with the first leader, please input the names of each player in clockwise order.\n");
					for (int i = 0; i < this.engine.getNumPlayers(); i++) {
					    System.out.println("main.Player " + (i + 1) + ":");
					    line = sc.nextLine();
					    this.engine.setPlayerName(i, line);
                    }
                    System.out.println("\nWonderful! Let's begin the game!");
					this.printScoreCard(this.engine);
					this.engine.startGame();
				} catch (Exception e) {
					System.err.println("Exception:" + e);
					System.err.println("The Application will now close.");
					CrystalBrook.applicationExit();
				}
			} else if (line.equals("n")) {
				System.out.println("The Application will now close.");
				System.out.println("Please set up the system config file before restarting.");
				sc.close();
				CrystalBrook.applicationExit();
			} else {
				printInvalidResponse();
			}
		}
	}

	public void queryRoundStart() {
		System.out.println("When you are ready to start the next round, type 'g'.");

		validInput = false;
		line = null;

		while (!validInput) {
			line = sc.nextLine();

			if (line.equals("g")) {
				validInput = true;

				System.out.println("Starting new round...\n");

				return;
			} else {
				printInvalidResponse();
			}
		}
	}

	public void printRoundInfo(String leadersName, int cardsThisRound) {
		System.out.println("Welcome to the " + cardsThisRound + " round! " + leadersName + " is the leader this round.");
	}

	public int promptBid(String name, int tally, int max, boolean crystalbrook, int crystalBrookBid) {

		int bid = 0;

		System.out.println("The current bid tally is: " + tally + ". What is your bid, " + name +"?");

		if (crystalbrook) {
			this.printCrystalBrookBidInfo(crystalBrookBid);
		}

		validInput = false;
		line = null;

		while (!validInput) {
			line = sc.nextLine();

			try {
				bid = Integer.parseInt(line);
				
				if ((bid > max) || (crystalbrook && bid == crystalBrookBid)) {
					printInvalidResponse();
				} else {
					validInput = true;
				}

			} catch (NumberFormatException e) {
				printInvalidResponse();
			}
		}

		return bid;
	}

	public void printCrystalBrookBidInfo(int crystalBrook) {

		if (crystalBrook < 0) {
			System.out.println("You can bid whatever you'd like!");
		} else {
			System.out.println("You cannot bid " + crystalBrook + ".");
		}
	}

	public void printScoreCard(GameEngine engine) {

		System.out.println("\nScore Update!");

		List<Player> sortedPlayers = engine.sortPlayersByScore();

		for (Player p : sortedPlayers) {
			p.printScore();
		}
		System.out.println();
	}
	
	public static void printInvalidResponse() {
		System.err.println("Sorry, that input is invalid! Please try again!");
	}

}