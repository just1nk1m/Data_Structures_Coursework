package BinarySearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Project 2
 * @author Justin Kim 1385008
 * 
 * @Project2  Sorting UC alumni based on Santa Monica College enrollment
 * 			   1. Sorted through inefficient corss-checking
 *             2. Sorting through Binary Search
 *
 */

public class Project2 {

	/**
	 * Read in a file containing student records and create an array of Student
	 * objects
	 * 
	 * @param file
	 *            The filename
	 * @param num
	 *            The number of students in the file
	 * @return An array of Student objects
	 */
	static Student[] readStudentsFromFile(String filename, int num) {
		try (Scanner in = new Scanner(new File(filename))) {
			Student[] students = new Student[num];
			int index = 0;  //prevents ArrayIndexOutOfBounds
			String line;
			String[] input;

			while (in.hasNextLine()) {
				line = in.nextLine();
				input = line.split(","); //Splits each String input by the character ','
				students[index++] = new Student(input[0], Integer.parseInt(input[1]), input[2]);
			}
			return students;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Write an array of Student objects to a file
	 * 
	 * @param students
	 *            The array of Student objects to write out
	 * @param filename
	 *            The output filename
	 */
	static void writeStudentsToFile(Student[] students, String filename) {
		try (FileWriter out = new FileWriter(filename)) {

			for (Student s : students)
				out.write(s.toString() + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * comparing it with every student in group2.
	 * 
	 * @param group1
	 *            A group of Students
	 * @param group2
	 *            A group of Students
	 * @param numCommon
	 *            number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents1(Student[] group1, Student[] group2, int numCommon) {
		Student[] common = new Student[numCommon];
		int commonIndex = 0;

		// Checks each element from group1 in group2
		for (int i = 0; i < group1.length; i++)
			for (int j = 0; j < group2.length; j++) {
				if (group1[i].equals(group2[j])) {
					common[commonIndex++] = group1[i];
				}
			}

		return common;
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * doing a binary search on group2.
	 * 
	 * @param group1
	 *            A group of Students
	 * @param group2
	 *            A group of Students
	 * @param numCommon
	 *            number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents2(Student[] group1, Student[] group2, int numCommon) {
		Student[] common = new Student[numCommon];

		int index; // Index where BinarySearch finds element
		int comIndex = 0; // Index for common array
		Arrays.sort(group2);
		for (int i = 0; i < group1.length; i++) {
			index = Arrays.binarySearch(group2, group1[i]);
			if (index >= 0) { // BinarySearch will return a negative if not
								// found
				common[comIndex++] = group1[i];
			}

		}

		return common;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/***** These files provided to help you with initial testing *****/
//		 Student[] uc = readStudentsFromFile("sample_uc_students.txt", 10);
//		 Student[] smc = readStudentsFromFile("sample_smc_grads.txt", 5);
//		 final int SMC_UC_GradsNumber = 2;

		/***** Use these files for the output you submit *****/
		Student[] uc = readStudentsFromFile("uc_students.txt", 350000);
		Student[] smc = readStudentsFromFile("smc_grads.txt", 75000);
		final int SMC_UC_GradsNumber = 25000;

		long start, end;

		/***** These files provided to help you with initial testing *****/
		 start = System.currentTimeMillis();
		 Student[] common1 = findCommonStudents1(uc, smc, SMC_UC_GradsNumber);
		 end = System.currentTimeMillis();
		 System.out.println("Cross checking took " + (end - start) / 1000.0
		 + " seconds.");
		 Arrays.sort(common1);
		 writeStudentsToFile(common1, "smc_grads_at_uc_1.txt");

		start = System.currentTimeMillis();
		Student[] common2 = findCommonStudents2(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Using binary search it took " + (end - start) / 1000.0 + " seconds.");
		Arrays.sort(common2);
		writeStudentsToFile(common2, "smc_grads_at_uc_2.txt");

	}
}