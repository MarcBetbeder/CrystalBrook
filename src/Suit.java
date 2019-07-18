import java.util.HashMap;
import java.util.Map;

public enum Suit {
	HEARTS(1), DIAMONDS(2), CLUBS(3), SPADES(4);
	
	private int value;
	private static Map<Integer, Suit> map = new HashMap<Integer, Suit>();
	
	private Suit(int value) {
		this.value = value;
	}
	
	static {
		for (Suit suit : Suit.values()) {
			map.put(suit.value, suit);
		}
	}
	
	public static Suit valueOf(int value) {
		return (Suit) map.get(value);
	}
	
	public int getValue() {
		return value;
	}
}