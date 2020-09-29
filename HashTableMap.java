// --== CS400 File Header Information ==--
// Name: Jason Kaminski	
// Email: jdkaminski@wisc.edu
// Team: EE
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;
import java.util.NoSuchElementException;
/*
 * this class creates a HashTableMap to be used for hashtables in project 1
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
	
	private int size;
	private int capacity;
	LinkedList<Pairs<KeyType, ValueType>>[] array;
	
	/*
	 * constructor for the hashtable map
	 * @param int capacity of the HashTableMap
	 */
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.array = new LinkedList[capacity];
		
	}
	/*
	 * constructor for the hashtable map, default capacity at 10
	 */
	public HashTableMap() { // with default capacity = 10
		this.capacity = 10;
		this.size = 0;
		this.array = new LinkedList[this.capacity];

	}
	/*
	 * private helper method to hash the key value input.
	 * @Param key value of the pair
	 * @return the position in the array for the key and its pairs to be entered
	 */
	private int hashIt(KeyType key) {
		int position = Math.abs(key.hashCode())%this.capacity;
		return position;
	}
	/*
	 * private helper method to check if over 80% of the HashTableMap will be used
	 * @return true if it will not be used, false if over 80% will be used
	 */
	private boolean checkRatio() {
		double currSize = this.size + 1;
		double currCap = this.capacity;
		double ratio = (currSize/currCap);
		if(ratio >= 0.8) {
			return false;
		}
				
		return true;
	}
	
	/*
	 * private helper method to rewrite the HashTableMap array when more than 80% has been used
	 * @return boolean true when the method has been completed
	 */
	private boolean reHash(){
		LinkedList<Pairs<KeyType, ValueType>> newArray[] = new LinkedList[capacity * 2];
		LinkedList<Pairs<KeyType, ValueType>> placeHolder[] = this.array;
		this.capacity = this.capacity * 2;
		this.array = newArray;
		this.size = 0;
		for(LinkedList<Pairs<KeyType, ValueType>> item: placeHolder) {
			if(item != null) {
				for(int i = 0; i< item.size(); i++) {
					put((KeyType) item.getFirst().getKey(), (ValueType)item.getFirst().getValue());
					item.remove(0);
					
				}
			}else {
				
			}
		}
		return true;
	}
	/*
	 * method to return the capacity of the HashTableMap, mainly for testing purposes
	 * @return int capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	/*
	 * This method puts Pairs into the LinkedList array, and into the HashTableMap
	 * @param KeyType key, the key in the key-value pair
	 * @param ValueType value, the value in the key-value pair
	 * @return boolean true if the put is successful, false if the key already exists in the HashTableMap
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		Pairs<KeyType, ValueType> newBook = new Pairs<KeyType, ValueType>(key, value);
		if(this.containsKey(key)) { // check to see if the key already exists in HashTableMap
			return false;
		}
		if(!checkRatio()) { // check if there needs to be rehashing done
			this.size=0;
			this.reHash();
		}
		int position = hashIt(key);
		if(this.array[position] == null) {
			LinkedList<Pairs<KeyType, ValueType>> here = new LinkedList<Pairs<KeyType, ValueType>>();
			this.array[position] = here;
			here.add(newBook);
			this.size ++;
			return true;
		}
		this.array[position].add(newBook);
		this.size ++;
		return true;
	}
	/*
	 * This method gets the ValueType that is stored in the HashTableMap associated with the passed
	 * argument, key.
	 * @param KeyType key, the key in the key-value pair
	 * @return true if the get method was completed
	 * @throws NoSuchElementException if the value did not exist
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		ValueType returnable = null;
		int position = hashIt(key);
		if(this.array[position] == null) {
			returnable = null;
		}else {
		for(Pairs<KeyType, ValueType> item: this.array[position]) {
			if(item.getKey().equals(key)) {
				returnable = (ValueType) item.getValue();
			}
		}
		}
		if(returnable == null) {
			throw new NoSuchElementException("The Key does not exist in the HashTableMap");
		}
		return returnable;
	}
	
	/*
	 * this method returns the amount of key-value pairs in the HashTableMap
	 * @return int amount of key-value pairs in HashTableMap
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	/*
	 * returns true if the HashTableMap contains the key, or false if not
	 * @param KeyType key, key in the key-value pair
	 * @return True if HashTableMap contains the key, false if it does not
	 */
	@Override
	public boolean containsKey(KeyType key) {
		int location = hashIt(key);
		if(this.array[location] != null) {
		for(int i = 0; i< this.array[location].size(); i++) {
			if(this.array[location].get(i).getKey().equals(key)) {
				return true;
			}
		}
		
		}
		return false;
	}
	/*
	 * removes key-value pair from the HashTableMap
	 * @param KeyType key, the key in the key-value pair
	 * @return ValueType value of the key-value pair being removed
	 */
	@Override
	public ValueType remove(KeyType key) {
		ValueType returnable = null;
		int location = hashIt(key);
		if(this.containsKey(key)) {
			for(int i = 0; i< this.array[location].size(); i++) {
				if(this.array[location].get(i).getKey().equals(key)) {
					returnable = this.array[location].get(i).getValue();
					this.array[location].remove(i);
					this.size --;
					return returnable;
				}
			}
		}
		return null;
	}
	/*
	 * this method clears the HashTableMap, and reconstructs a new HashTableMap
	 */
	@Override
	public void clear() {
		this.capacity = 10;
		this.size = 0;
		this.array = new LinkedList[this.capacity];
		
	}

	
	
	
}