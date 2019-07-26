import java.util.ArrayList;
import java.util.List;

public class GameEngine {

	GameController controller;

	private List<Player> players;
	
	private int maxHandSize;
	private int zeroValue;
	private int mode;

	private Deck deck = null;

	private boolean roundInProgress;
	private boolean gameInProgress;

	private Player currentPlayer = null;
	private int currentRound;
	private int cardsThisRound;
	private int maxRound;

	public GameEngine(GameController controller, int numPlayers, int maxHandSize, int zeroValue, int mode, int numAIs) {
		this.controller = controller;

		this.players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			this.players.add(new Player(this, i));
		}
		
		this.maxHandSize = maxHandSize;
		this.zeroValue = zeroValue;
		this.mode = mode;

		this.roundInProgress = false;
		this.gameInProgress = false;

		this.currentPlayer = findPlayerByID(0);
		this.currentRound = 1;
		this.maxRound = maxHandSize * 2;
	}

	public void startGame() {
		this.gameInProgress = true;
		this.startRound();
	}

	private void startRound() {

		controller.queryRoundStart();
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

	public List<Player> sortPlayersByScore() {
		List<Player> sortedPlayers = new ArrayList<>();
		List<Player> unsortedPlayers = new ArrayList<>();

		unsortedPlayers.addAll(this.players);

		int highScore;
		Player bestPlayer;
		boolean found;

		for (int i = 0; i < this.getNumPlayers(); i++) {
			highScore = 0;
			bestPlayer = null;

			for (Player player : unsortedPlayers) {
				found = false;

				if (player.getScore() > highScore) {
					found = true;
				} else if (player.getScore() == highScore) {
					if (bestPlayer == null) {
						found = true;
					} else if (player.getID() < bestPlayer.getID()) {
						found = true;
					}
				}
				if (found) {
					bestPlayer = player;
					highScore = player.getScore();
				}
			}

			sortedPlayers.add(bestPlayer);
			unsortedPlayers.remove(bestPlayer);
		}

		return sortedPlayers;
	}

}
