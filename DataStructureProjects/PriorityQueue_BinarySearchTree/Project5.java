package PriorityQueue_BinarySearchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;




/**
 * Project 5
 * @author Justin Kim 1385008
 * 
 * @Project5   1. Implementing a max/min priority queue with a Binary Search Tree
 *             2. Using java library Maps to count words in a text file
 *
 */
public class Project5 {

// Part 1: min/max queue 
	
	static class Node {
		Node(int key) {
			this.key = key;
		}

		int key;
		Node left, right;
	}

  private Node root;
  private int size;  

	/**
	 * Add an element if it is not already present.
	 * Duplicate values are ignored.
	 * 
	 * 
	 * @param key
	 */
	public void add(int key) {

		if (root == null){
			size++;
			root = new Node(key);
		}
		else
			add(root, key);
	}
	
	Node flips(Node node){
		
		if (node == null)
			return null;
		else if ((node.left == null) && (node.right == null))
			return node;
		else {
			Node temp = node.left;
			node.left = flips(node.right);
			node.right = flips(temp);
		}
		
		return node;
	}
	
	
	/**
	 * Remove the min element
	 * @return the min element removed
	 */
	public int removeMin() {
		int min;

		if (root == null) { //if Queue is empty
			System.out.println("Queue is empty");
			return 0;
		}
		else if(root.left==null){
			min = root.key;
			size--;
			if(root.right!=null)
				root=root.right;
			else
				root=null;
			return min;
		}
		
		Node parent = null;
		Node cur=root;
		
		while(cur.left!=null){ //traverse tree to find minimum node and its parent
			parent=cur;
			cur=cur.left;
		}
		min=cur.key;
		
		
	   parent.left=cur.right; // parent.left will either be cur's right node or null
	   size--;

		return min;
	}

	
	/**
	 * Remove the max element
	 * @return the max element removed
	 */
	public int removeMax() {
		
		int max;

		if (root == null) { //if Queue is empty
			System.out.println("Queue is empty");
			return 0;
		}
		else if(root.right==null){
			max = root.key;
			size--;
			if(root.left!=null)
				root=root.left;
			else
				root=null;
			return max;
		}
		
		Node parent = null;
		Node cur=root;
		
		while(cur.right!=null){ //traverse tree to find minimum node and its parent
			parent=cur;
			cur=cur.right;
		}
		max=cur.key;
		
	   parent.right=cur.left; // parent.right will either be cur's left node or null
	   size--;

		return max;
	}


	/**
	 * Get the size of the queue
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 *Recursive add method. Traverses tree to find node's position and add's at position
	 *
	 *@param1 Node   child node
	 *@param2 key    key to be added 
	 *
	 *@return the child node back to parent node
	 */
	private Node add(Node node, int key) {
		if (node == null)
		{	size++;
			return new Node(key);
		}
		if (node.key > key)
			node.left = add(node.left, key);
		else if (node.key < key)
			node.right = add(node.right, key);
		return node;
	}
	
	/* Part 2: min/max queue analysis. State clearly any assumptions the runtime
	 * depends on and how the runtime is affected if the assumptions do not hold.
	 * Give the big O. Give a bad example.
	 *
	 *
	 *
	 *
	 * add: O(logn) Assuming the input is random the runtime, because we must go 
	 *      through the tree level-by-level until we reach the node's place at the 
	 *      bottom of the tree.
	 *    Worst Case: Big O: O(n) If we choose inputs that are always the most left or most right
	 *      of the tree, since we pretty much have to traverse the whole tree.
	 *     For example:          7
	 *                          /
	 *                         6
	 *                        /
	 *                       5
	 *                      /
	 *                    ... and so on. To add another least node we would have to traverse 
	 *                                    the whole tree.
	 *      
	 * removeMin/Max: O(logn) log(n) being the steps it takes to get to the
	 *                 min/max level of the tree.
	 *              Worst Case: Big O: O(n) if the tree is heavily unbalanced  so that
	 *              	the tree is either very left favored or right favored
	 *            For example:   7
	 *                          / \
	 *                         6   8
	 *                        /     \
	 *                       5       9 
	 *                      /         \ 
	 *                    ...         ...  and so on
	 *                       Each removeMin/Max will have to traverse all levels of either 
	 *                         end of the tree.
	 *
	 */
	
	// Part 3: Most common words  
	
	/**
	 * Prints all the words that occur more times than the given threshold value and their count.
	 * The words are printed in alphabetical order. 
	 * All the words read from the file are converted to lower case so that "That" and "that" 
	 * are considered the same word. Empty words are not counted.
	 * 
	 * @param filename The text file to process
	 * @param threshold e.g. 1000 -> only words that occur more than 1000 times
	 */
	
	static void mostFrequentWords(String filename, int threshold) {
		SortedMap<String, Integer> map = new TreeMap<String, Integer>();
		int value; //value for key
		String tempStr;
		double wordSize=0; //total words
		double commonSize=0; //total of words that appear 2 or more times
		
		try {
			Scanner input = new Scanner(new File(filename));

			while (input.hasNextLine()) {

				tempStr = input.nextLine().toLowerCase().trim();

				// for each String separated by white spaces and punctuation
				for (String key : tempStr.split(("[\\s\\p{Punct}]+"))) {
					wordSize++;
					if (map.containsKey(key)) {
						value = map.get(key) + 1;
						map.replace(key, value);
					} else
						map.put(key, 1);
				}

			}input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(Map.Entry<String , Integer> entry :map.entrySet()){ ///iterate through map
			if(entry.getValue()>=2)
				commonSize++;
			if(entry.getValue()>=1000)
				System.out.println(entry.getKey()+" "+entry.getValue());
		}
		
			System.out.println(commonSize+ " /  "+wordSize +"  =  "+commonSize/wordSize);

			System.out.println("Total words : " + wordSize);
			System.out.println("Total common words: " + commonSize);
			System.out.println("All common words as a portion of total: " + commonSize/wordSize);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * This code is here just as an example.
		 * 
		 * Test your code well.
		 * 
		 * You can leave that code in the main but I will not use that or grade
		 * you on it. I will test your code with my own main.
		 */


		
		System.out.println("Most frequent words:");
		mostFrequentWords("LesMiserables.txt", 1000);		


		// Would output something like below (all numbers shown are made up)

		// a 12345                 - this means the word "a" occured 12345 times in the text
		// all 2345                -                     "all"       2345 times, and so on ...
		// an 3456
		// ...
		// ...
		// with 4567
		// would 1234
		// you 2345
		
		// Total words : 234567
		// Total common words: 123456
		// All common words as a portion of total: 0.53

	}
}
