package sunbeam;

import java.util.Scanner;

public class HashTableMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashTable ht = new HashTable();
		ht.put(4002, new Student(4002, "A", 54));
		ht.put(3020, new Student(3020, "B", 54));
		ht.put(6048, new Student(6048, "C", 54));
		ht.put(2120, new Student(2120, "D", 54));
		ht.put(3670, new Student(3670, "E", 54));
		ht.put(5102, new Student(5102, "F", 54));
		
		ht.put(6048, new Student(6048, "F", 76));
		
		System.out.print("Enter roll to find: ");
		int key = sc.nextInt();
		Student value = ht.get(key);
		if(value != null)
			System.out.println("Found " + value);
		else
			System.out.println("Not Found.");
	}
}
