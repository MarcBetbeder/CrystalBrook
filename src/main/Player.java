package main;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private GameEngine engine;
	private List<Card> hand;
	
	private int id;
	
	private String name;
	private String alias;
	private int score;

	private int currentBid = 0;
	
	public Player(GameEngine engine, int id) {
		this.engine = engine;
		this.hand = new ArrayList<>();
		
		this.id = id;
		
		this.name = null;
		this.alias = null;
		this.score = 0;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getScore() {
		return this.score;
	}

	public void addScore(int points) {
		this.score += points;
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	public void setBid(int bid) {
		this.currentBid = bid;
	}

	public int getCurrentBid() {
		return this.currentBid;
	}

	public void printScore() {
		System.out.println(this.name + ": " + this.score);
	}
}
