public class Pairs<KeyType, ValueType> {
	private KeyType key;
	private ValueType value;
	
	public Pairs(KeyType k, ValueType v) {
		this.key = k;
		this.value = v;
	}
	
	public KeyType getKey() {
		return this.key;
	}
	
	public ValueType getValue() {
		return this.value;
	}
	
	public String toString() {
		String message = "key: " + this.key + " Value: " + this.value;
		return message;
	}
}