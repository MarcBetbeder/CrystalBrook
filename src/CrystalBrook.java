public class CrystalBrook {

	public static void main(String[] args) {
		
		applicationStart();
	}
	
	private static void applicationStart() {
		GameController controller = new GameController();
		controller.startUp();
	}
	
	public static void applicationExit() {
		System.out.println("Shutting down for now, see you next time!");
		
		System.exit(0);
	}

}
