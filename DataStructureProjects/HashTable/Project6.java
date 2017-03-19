package HashTable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * Project 6
 * 
 * @author  Justin Kim
 * @ID      1385008
 * 
 * @Project6  1. Write a recursive method to operate on Binary Trees
 *            2. Implementing a separate-chaining hash table to find valid combinations of letters (anagram solver)
 *
 */

public class Project6 {
	
	/*
	 * ******************************** Part 1 ******************************
	 */
	/*
	 * DO NOT MODIFY THE NODE CLASS
	 */
	Node root;
	static class Node {
		Node(int data) {
			this.data = data;
		}

		int data;
		Node left;
		Node right;
	}
	
	static void flip(Node node) {

		//base case if we reach an end of a tree
		if (node == null)
			return;
		//swapping
		else {
			Node temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
		
		//recursive step: flips all of left subtree, then flips the all of right subtree
		flip(node.right);
		flip(node.left);
	}
	
	/*
	 * ******************************** Part 2 ******************************
	 * 
	 */
	
	//Assuming dictionary is fixed we'll have an alpha = ~4.95
	private final int HASH_TABLE_SIZE = 20000;
	private LinkedList<String> [] table = new LinkedList[HASH_TABLE_SIZE];
	/**
	 * Constructs a word matcher based on the given dictionary.
	 * Assumes dictionary is unchanging
	 * 
	 * @param filename The dictionary file name
	 */

	public Project6(String filename) {
		
		int hashVal;
		String curStr; //current string 
		
		for(int i =0;i<table.length;i++) //populate our table with buckets
			table[i]=new LinkedList();
		
		try{
			Scanner input = new Scanner(new File(filename));	
			while(input.hasNext()){
				curStr=input.next();
				hashVal = hash(curStr);
				table[hashVal].add(curStr);
			}

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * Return a list of dictionary words that have the same letters as the given word.
	 * Differences in letter cases are ignored.
	 * 
	 * @param word The word to find matches for. May or may not be in the dictionary.
	 * 
	 * @return The list of matching words from the dictionary, all in lower case.
	 * 		   The word itself is not included in the returned list.
	 * 		   e.g.: 	NAME -> [amen, mane, mean]
	 */
	public List<String> getMatches(String word) {
		List<String> matches = new LinkedList<String>();
		
		word = new String(word).toLowerCase();
		char[] wordArr= word.toCharArray();
		Arrays.sort(wordArr);
		String sortWord= new String(wordArr);
		
		int hashVal = hash(word.toLowerCase()); //lowercase so everything is uniform
		
		char[] sArr;
		String sortedTemp;//sorted word
		
		for(String temp:table[hashVal]){ // iterates through bucket
			sArr= temp.toCharArray();
			Arrays.sort(sArr);
			sortedTemp= new String(sArr);
			
			// adds words if the sorted strings are equal and it is not the word itself
			if (sortWord.equals(sortedTemp) && !(word.equals(temp)) ) { 
				matches.add(temp);
			}
		}
			
		return matches; 

	}
	/**
	 * Provides a hash value based on a String's character values.
	 * 
	 * @param s String that will be used to produce a hash value
	 * 
	 * @return hash value
	 */
	public int hash(String s){
		char [] c = s.toCharArray(); //array to be sorted so that all hashvalues are uniform
		int hash=0;
		
		Arrays.sort(c); // need to sort so that all "act" and "cat" have the same hash value
		String sortedStr = new String(c); 
		
		//from https://www.tutorialspoint.com/java/java_string_hashcode.htm
		//s[0]*31^(n - 1) + s[1]*31^(n - 2) + ... + s[n - 1]
		for(int i =0;i<sortedStr.length();i++)
			hash+=sortedStr.charAt(i)*31^(sortedStr.length()-(i+1)); 
		hash=hash%HASH_TABLE_SIZE;
		if (hash < 0) {
			hash += HASH_TABLE_SIZE;
		}
		
		
		return hash;
	}
	
	public static void main(String[] args) {
		
		/*
		 *    You can test your methods here but I will not grade the main
		 *
		 */
//		Project6 test = new Project6("fwet");
//
//		test.root = new Node(5);
//		test.root.left = new Node(3);
//		test.root.right = new Node(3);

		long start = System.currentTimeMillis();
		
		Project6 matcher = new Project6("words.txt");
		
		// test code for functionality
		String[] words = { "act", "hug", "cafe", "node", "canoe", "dusty",
				"friend", "silent", "meteor", "markers", "aStewSir",
				"dirtyRoom", "stampStore", "moonStarers", "theClassroom",
				"accordNotInIt","name" };
		for (String word : words) {
			System.out.println(word + " -> " + matcher.getMatches(word));
		}

		// Stress the application to ensure it runs efficiently under load.
		// All runs below should complete practically in an instant.
		final int RUNS = 100000;
		for (int i = 0; i <= RUNS; i++) {
			matcher.getMatches("noMoreStars");
			if (i % 1000 == 0) {
				System.out.print(".");
			}
		}
		System.out.println();
		System.out.println("noMoreStars" + " -> "
				+ matcher.getMatches("noMoreStars"));
		long end = System.currentTimeMillis();
		System.out.println("Two stable sorts took " + (end - start)
				+ " milliseconds.");
	}
}
