package Lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Project 3
 * @author Justin Kim 1385008
 * 
 * @Project3   1. Create an array-based list using the iSimpleList interface
 *             2. Creating a singly-linked list using the iSimpleList interface
 *
 */


public class Project3 {

	/*
	 * A- O(n) - Two linear loops: First one to store data in list n lines long
	 * 							   Second to create a new list and store n lines of data in reverse order 
	 * 
	 */

	/**
	 * Read lines from the file and put each line into the list in reverse order
	 * 
	 * @param filename
	 *            the filename
	 * @return A list containing the file contents in reverse order (first line
	 *         in file is last in list and last line in file is first in list)
	 */
	static ISimpleList getFileContentsInReverseA(String filename) {
		// you can only use ArrayBasedList to store any data
		long start = System.currentTimeMillis();

		File infile = new File(filename);
		String line; 
		ArrayBasedList list = new ArrayBasedList(); //original list
		ArrayBasedList newList = new ArrayBasedList(); //list in reverse order
		
		try {
			Scanner input = new Scanner(infile);
			while (input.hasNextLine()) {
				line = input.nextLine();
				list.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i - 1 < list.size(); i++)
			newList.add(list.get(list.size() - i));
		
		long end = System.currentTimeMillis();
		 System.out.println("O(2n) for arraybased lists took " + (end - start) / 1000.0);
		
		return newList;
	}

	/*
	 * B- O(n): Linearly stores data into linked list n lines in the file long
	 * 
	 * 
	 */

	/**
	 * Read lines from the file and put each line into the list in reverse order
	 * 
	 * @param filename
	 *            the filename
	 * @return A list containing the file contents in reverse order (first line
	 *         in file is last in list and last line in file is first in list)
	 */
	static ISimpleList getFileContentsInReverseB(String filename) {
		long start = System.currentTimeMillis();
		File infile = new File(filename);
		String line;
		SinglyLinkedList list = new SinglyLinkedList();
		try {
			Scanner input = new Scanner(infile);
			while (input.hasNextLine()) {
				line = input.nextLine();
				list.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		 System.out.println("O(2n) for arraybased lists took " + (end - start) / 1000.0);

		return list;
	}
	static ISimpleList getFileContentsInReverseC(String filename) {
		// you can only use ArrayBasedList to store any data
		long start = System.currentTimeMillis();

		File infile = new File(filename);
		String line; 
		ArrayBasedList list = new ArrayBasedList(); //original list
		
		try {
			Scanner input = new Scanner(infile);
			while (input.hasNextLine()) {
				line = input.nextLine();
				list.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		 System.out.println("O(2n) for arraybased lists took " + (end - start) / 1000.0);
		
		return list;
	}
	public static void main(String[] args) {

		SinglyLinkedList list = new SinglyLinkedList();
		list.add("one");

		list.add("three");

		list.add("two");

		System.out.println(list.remove("one"));
		System.out.println(list.get(2));
		list.add(1, "four");
		list.toString2();


		

	}

}
