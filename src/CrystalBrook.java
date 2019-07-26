import javafx.application.Application;
import javafx.stage.Stage;

public class CrystalBrook extends Application {

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		GameController controller = new GameController();
		controller.startUp();
	}
	
	public static void applicationExit() {
		// TODO Refactor into JavaFX implementation.

		System.out.println("Shutting down for now, see you next time!");
		
		System.exit(0);
	}
}
