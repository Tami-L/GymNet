/**
 * 
 */
package application;

import java.io.Serializable;

/**
 * @author L Tami This class is for storing the profile as an entrie consiting
 *         of a name which is the key and a pointer to the profile
 *
 */
public class Entry<K, V> implements Comparable<Entry<K, V>>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1560639407620112842L;
	// the key is the profile name
	private K key;
	// this strores the reference to the profile as an object
	private V value;

	private Entry<K, V> next;

	/**
	 * 
	 */
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;

	}

	/**
	 * this returns the name of the profile
	 * 
	 * @return
	 */

	public K getKey() {
		return key;
	}

	/**
	 * this returns the next entry in the table
	 * 
	 * @return
	 */
	public Entry<K, V> getNext() {
		return next;
	}

	/**
	 * this returns the profile
	 * 
	 * @return
	 */
	public V getValue() {
		return value;
	}

	// returns true if the entry has the reference to the next one
	public boolean hasNext() {
		if (next == null) {
			return false;
		} else {
			return true;
		}

	}

	public void setNext(Entry<K, V> e) {
		this.next = e;

	}

	/**
	 * this function updates the password
	 * 
	 * @param password
	 */

	@Override
	public int compareTo(Entry<K, V> o) {
		if (this.key.hashCode() - o.key.hashCode() > 0) {
			return 1;
		} else if (this.key.hashCode() - o.key.hashCode() < 0) {
			return -1;
		}
		// TODO look at this again??
		return 0;
	}

}
