import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private GameEngine engine;
	private List<Card> hand;
	
	private int id;
	
	private String name;
	private String alias;
	private int score;
	
	public Player(GameEngine engine, int id) {
		this.engine = engine;
		this.hand = new ArrayList<>();
		
		this.id = id;
		
		this.name = null;
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
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}
}
