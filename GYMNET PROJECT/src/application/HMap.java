package application;

import java.util.LinkedList;

/**
 * This class is for the creation of a hashmap. for constant time searching
 * 
 * @author L Tami 219005173
 *
 */
public class HMap<K, V> {

	private Entry<K, V>[] table = null;
	private int numVertices = 15;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HMap() {

		table = (Entry<K, V>[]) new Object[numVertices];

		// TODO Auto-generated constructor stub
	}

	// this needs to store the number of vertexes
	/**
	 * this is to remove all the entries from the map
	 */
	@SuppressWarnings("unused")
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}

	}

	private int getHash(K key) {
		int hash = 0;
		if (key != null && key instanceof String) {
			hash = key.hashCode() % this.numVertices;
			return hash;
		} else {
			return -1;

		}

	}

	/**
	 * this checks if a certain key is present in the map
	 *
	 * @return
	 */
	public boolean containsKey(K key) {
		if (key != null) {
			int hash = getHash(key);
			boolean hasKey = false;
			// check if there is something in that place
			if (table[hash] == null) {
				hasKey = false;

			} else {
				Entry<K, V> entry = table[hash];
				do {

					if (entry.getValue() == key) {
						hasKey = true;

					}
					entry = entry.getNext();

				} while (entry.hasNext());

			}

			return hasKey;
		} else {
			return false;
		}

	}

	/**
	 * this is for inserting a key value pair into the map
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unused")
	public void put(K key, V value) {
		int hash = getHash(key);

		// create a new entry for the profile to be inserted in the table
		Entry<K, V> entry = new Entry<K, V>(key, value);
		// check if the table doesnt have any values at the hash position
		if (table[hash] == null) {

			table[hash] = entry;

		} else {
			// if the is a value at the fisrt position of the hash then try chaining the
			// entry
			Entry<K, V> e = table[hash];
			while (e.hasNext()) {
				// traverse through the chain till a null is reached
				e = e.getNext();

			}
			e = entry;
		}

	}

	/**
	 * This function is for removing an entry from the map
	 * 
	 * @param key
	 */
	@SuppressWarnings("unused")
	public void remove(K key, V value) {
		// checks if the key exists before
		if (containsKey(key)) {
			removeEntry(key, value);
		} else {
			System.err.println("The table does not have the key you are looking for ");
		}

	}

	/**
	 * this is to get the profile pointer / reference
	 * 
	 * @param key
	 * @return
	 */

	@SuppressWarnings("unused")
	public LinkedList<Entry<K, V>> get(K key) {
		if (containsKey(key)) {

			int hash = getHash(key);
			Entry<K, V> e = table[hash];
			LinkedList<Entry<K, V>> list = new LinkedList<Entry<K, V>>();
			while (e.hasNext()) {
				list.add(e);
				e = e.getNext();
			}
			return list;

		} else {
			return null;
		}

	}

	/**
	 * This is for removing a value with a certain key from the table
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unused")
	public void removeEntry(K key, V value) {
		int hash = getHash(key);
		Entry<K, V> nodeToRemove = null;
		Entry<K, V> e = this.table[hash];

		while (e.getNext().getValue() != value) {
			e = e.getNext();

		}
		Entry<K, V> nodeToChange = e;
		nodeToChange = e.getNext().getNext();

		nodeToRemove = e.getNext();
		nodeToChange.setNext(null);

	}

}
