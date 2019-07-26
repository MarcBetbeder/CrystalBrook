import java.util.ArrayList;
import java.util.List;

public class GameEngine {

	private List<Player> players;
	
	private int maxHandSize;
	private int zeroValue;
	private int mode;

	private Deck deck = null;

	private boolean roundInProgress;
	private boolean gameInProgress;

	private Player currentPlayer = null;

	public GameEngine(int numPlayers, int maxHandSize, int zeroValue, int mode, int numAIs) {
		
		this.players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			this.players.add(new Player(this, i));
		}
		
		this.maxHandSize = maxHandSize;
		this.zeroValue = zeroValue;
		this.mode = mode;

		this.roundInProgress = false;
		this.gameInProgress = false;
	}

	public void startGame() {
		this.gameInProgress = true;
	}

	public int getNumPlayers() {
		return this.players.size();
	}

	public void setPlayerName(int id, String name) {
		Player p = findPlayerByID(id);
		if (p != null) {
			p.setName(name);
		}
	}

	private Player findPlayerByID(int id) {
		Player target = null;
		for (Player p : this.players) {
			if (p.getID() == id) {
				target = p;
			}
		}

		return target;
	}

	private Player findPlayerByName(String name) {
		Player target = null;
		for (Player p : this.players) {
			if (p.getName().equals(name)) {
				target = p;
			}
		}

		return target;
	}

}
