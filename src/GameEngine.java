import java.util.ArrayList;
import java.util.List;

public class GameEngine {

	private Deck deck;
	
	private List<Player> players;
	
	private int maxHandSize;
	private int zeroValue;
	
	public GameEngine(int numPlayers, int maxHandSize, int zeroValue) {
		this.deck = new Deck();
		
		this.players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			this.players.add(new Player(this, i));
		}
		
		this.maxHandSize = maxHandSize;
		this.zeroValue = zeroValue;
	}

}
