package main;

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

	private Player currentPlayer;
	private Player currentLeader;
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
		this.currentLeader = currentPlayer;
		this.currentRound = 1;
		this.maxRound = maxHandSize * 2;
	}

	public void startGame() {
		this.gameInProgress = true;
		this.currentLeader = this.findPlayerByID(0);
		this.currentPlayer = this.currentLeader;
		this.currentRound = 1;
		this.startRound();
	}

	private void startRound() {

		this.controller.queryRoundStart();

		this.cardsThisRound = calculateCardsThisRound();
		this.controller.printRoundInfo(this.currentLeader.getName(), this.cardsThisRound);

		this.biddingPhase();
	}

	private void biddingPhase() {

		int bidTally = 0;
		int crystalBrookBid = 0;
		boolean crystalBrook = false;

		for (int i = 0; i < this.getNumPlayers(); i++) {
			if (i == this.getNumPlayers() - 1) {
				crystalBrookBid = this.cardsThisRound - bidTally;
				crystalBrook = true;
			}

			bidTally += this.makeBid(bidTally, crystalBrook, crystalBrookBid);
			this.currentPlayer = this.getNextPlayer(this.currentPlayer);
		}
	}

	private int makeBid(int tally, boolean crystalBrook, int crystalBrookBid) {

		int bid = controller.promptBid(this.currentPlayer.getName(), tally,
				this.cardsThisRound, crystalBrook, crystalBrookBid);
		this.currentPlayer.setBid(bid);

		return bid;
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

	private Player getNextPlayer(Player p) {

		Player target = null;

		if (p.getID() == this.getNumPlayers()) {
			target = this.findPlayerByID(0);
		} else {
			target = this.findPlayerByID(p.getID() + 1);
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

	private int calculateCardsThisRound() {

		int answer;

		if (this.currentRound <= this.maxHandSize) {
			answer = this.maxHandSize - this.currentRound + 1;
		} else {
			answer = this.currentRound - this.maxHandSize;
		}

		return answer;
	}

}
