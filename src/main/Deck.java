package main;

import java.util.ArrayList;

public class Deck {
	
	private ArrayList<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();

		for (int s = 1; s < 5; s++) {
			
			for (int v = 2; v < 15; v++) {
				
				cards.add(new Card(Value.valueOf(v), Suit.valueOf(s)));
			}
		}
	}
	
}