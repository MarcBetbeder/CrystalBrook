import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private GameEngine engine;
	private List<Card> hand;
	
	public Player(GameEngine engine) {
		this.engine = engine;
		this.hand = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
}
