import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private GameEngine engine;
	private List<Card> hand;
	
	private int id;
	
	public Player(GameEngine engine, int id) {
		this.engine = engine;
		this.hand = new ArrayList<>();
		this.id = id;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
}
