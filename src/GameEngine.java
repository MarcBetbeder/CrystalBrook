import java.util.ArrayList;
import java.util.List;

public class GameEngine {

	private List<Player> players;
	
	private int maxHandSize;
	private int zeroValue;

	private Deck deck = null;

	private boolean roundInProgress;
	private boolean gameInProgress;

	private Player currentPlayer = null;

	public GameEngine(int numPlayers, int maxHandSize, int zeroValue) {
		
		this.players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			this.players.add(new Player(this, i));
		}
		
		this.maxHandSize = maxHandSize;
		this.zeroValue = zeroValue;

		this.roundInProgress = false;
		this.gameInProgress = false;
	}

	public void startGame() {
		this.gameInProgress = true;
	}

}
