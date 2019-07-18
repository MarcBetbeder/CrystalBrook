import java.util.HashMap;
import java.util.Map;

public enum Value {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);
	
	private int value;
	private static Map<Integer, Value> map = new HashMap<Integer, Value>();
	
	private Value(int value) {
		this.value = value;
	}
	
	static {
		for (Value value : Value.values()) {
			map.put(value.value, value);
		}
	}
	
	public static Value valueOf(int value) {
		return (Value) map.get(value);
	}
	
	public int getValue() {
		return value;
	}
}