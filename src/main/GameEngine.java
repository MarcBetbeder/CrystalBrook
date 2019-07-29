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

		this.scoringPhase();
	}

	private void scoringPhase() {

		this.controller.printScoringPhase();

		for (Player p : this.players) {
			this.scorePlayer(p);
		}

		this.controller.printScoreCard();

		this.finaliseRound();
	}

	private void finaliseRound() {
		if (currentRound == maxRound) {
			gameInProgress = false;
		}

		if (gameInProgress) {
			currentRound++;
			currentLeader = this.getNextPlayer(currentLeader);
			currentPlayer = currentLeader;

			startRound();
		} else {
			this.gameFinish();
		}
	}

	private void gameFinish() {

		String winner = this.findWinningPlayers();
		int score = this.findWinningScore();
		float average = (float) score / (float) maxRound;

		controller.printGameEnd(winner, score, average);

		CrystalBrook.applicationExit();
	}

	private int makeBid(int tally, boolean crystalBrook, int crystalBrookBid) {

		int bid = controller.promptBid(this.currentPlayer.getName(), tally,
				this.cardsThisRound, crystalBrook, crystalBrookBid);
		this.currentPlayer.setBid(bid);

		return bid;
	}

	private void scorePlayer(Player p) {

		int tricks = this.controller.promptTricksWon(p.getName(), this.cardsThisRound, p.getCurrentBid());

		int score = this.calculateScore(tricks, p.getCurrentBid());

		p.addScore(score);
	}

	private int calculateScore(int tricks, int bid) {

		int score = 0;

		if (tricks == bid) {
			if (bid == 0) {
				score = zeroValue;
			} else {
				score = 10 + tricks;
			}
		} else {
			score = tricks;
		}

		return score;
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

		if (p.getID() == this.getNumPlayers() - 1) {
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

	private String findWinningPlayers() {
		StringBuilder winners = new StringBuilder();

		int winningScore = this.findWinningScore();

		List<Player> winningPlayers = new ArrayList<>();

		for (Player p : this.players) {
			if (p.getScore() == winningScore) {
				winningPlayers.add(p);
			}
		}

		switch (winningPlayers.size()) {
			case 1:
				winners = new StringBuilder(winningPlayers.get(0).getName());
				break;
			case 2:
				winners = new StringBuilder(winningPlayers.get(0).getName() + " and " + winningPlayers.get(1).getName());
				break;
			case 3:
				for (int i = 0; i < winningPlayers.size(); i++) {
					if (i == winningPlayers.size() - 1) {
						winners.append("and ").append(winningPlayers.get(i).getName());
					} else {
						winners.append(winningPlayers.get(i).getName()).append(", ");
					}
				}
		}

		return winners.toString();
	}

	private int findWinningScore() {
		int score = 0;

		List<Player> sortedPlayers = sortPlayersByScore();
		Player winner = sortedPlayers.get(0);
		score = winner.getScore();

		return score;
	}

}
