public class Card {
	
	private Value value;
	private Suit suit;
	
	public Card(Value value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public Value getValue() {
		return this.value;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
}