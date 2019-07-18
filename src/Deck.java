import java.util.ArrayList;

public class Deck {
	
	private ArrayList<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		int v = 2;
		int s = 1;
		while (s < 5) {
			
			while (v < 15) {
				
				cards.add(new Card(Value.valueOf(v), Suit.valueOf(s)));
				
				v++;
			}
			
			s++;
		}
	}
	
}