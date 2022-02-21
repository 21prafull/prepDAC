package sunbeam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashTable {
	static class Entry {
		private int key; // roll
		private Student value; // Student object
		public Entry() {
		}
		public Entry(int key, Student value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private List<Entry> table[];
	
	public HashTable() {
		// allocate table
		table = new List[50];
		// initially all buckets (linked lists) are empty
		for (int i = 0; i < table.length; i++)
			table[i] = new ArrayList<>();
	}
	
	public void put(int key, Student value) {
		// find the slot/bucket for given key
		int index = key % table.length;
		// check if entry is found for given key in that bucket
		List<Entry> bucket = table[index];
		for(Entry e:bucket) {
			// if found, replace the value
			if(key == e.key) {
				e.value = value;
				return;
			}
		}
		// create a new entry (key + value) and add entry into that bucket
		Entry e = new Entry(key, value);
		bucket.add(e);
	}
	
	public Student get(int key) {
		// find the slot/bucket for given key -- hash function
		int index = key % table.length;
		// check if entry is found for given key in that bucket
		List<Entry> bucket = table[index];
		for(Entry e : bucket) {
			// if found, return the value
			if(key == e.key)
				return e.value;
		}
		// if not found, return null
		return null;
	}
}
