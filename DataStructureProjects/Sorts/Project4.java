package Sorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


/**
 * Project 4
 * 
 * @author Justin Kim
 * @id 1385008
 *
 * @Project4 - 1. Implementing Java library Collections.sort() method
 * 			   2. Implementing my own bucket sort algorithm
 */

public class Project4 {

	/**
	 * Read students from file into a list
	 * 
	 * @param filename
	 * @return the list of students
	 */
	static ArrayList<Student> readFromFile(String filename) {
		ArrayList<Student> students = new ArrayList<Student>();
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(",");
				students.add(new Student(Integer.parseInt(fields[1]), fields[0], fields[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return students;
	}

	/**
	 * Write the students to a file
	 * 
	 * @param students
	 * @param filename
	 */
	static void writeToFile(ArrayList<Student> students, String filename) {
		try (FileWriter out = new FileWriter(filename)) {
			for (Student s : students) {
				out.write(s.toString());
				out.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sorts the students list by school. Same school students are sorted by ID.
	 * 
	 * Performs two stable sorts with different criteria. One sort and then another sort.
	 * 
	 * @param students
	 * 
	 */
	
	static void sortBySchoolById1(ArrayList<Student> students) {
			// Sort by school
				Collections.sort(students, new Comparator<Student>() {
					/**
					 * Compare two students by school.
					 * 
					 * @param1 Student 1
					 * @param2 Student 2
					 * 
					 * @return Return a positive int if arg1>arg2, 0 if arg1==arg2, or a negative int if arg1<arg2
					 */
					@Override
					public int compare(Student s1, Student s2) {
						String school1 = s1.getSchool();
						String school2 = s2.getSchool();
						
						return school1.compareTo(school2);
					}
				});
				
				// sort by ID
				Collections.sort(students, new Comparator<Student>() {
					/**
					 * Checks if two students' schools are the same, then compares IDs.
					 * 
					 * @param1 Student 1
					 * @param2 Student 2
					 * 
					 * @return Return a positive int if arg1>arg2, 0 if arg1==arg2, or a negative int if arg1<arg2
					 */
					@Override
					public int compare(Student s1, Student s2) {
						String school1 = s1.getSchool();
						String school2 = s2.getSchool();
						int swapSchools = school1.compareTo(school2);
						int s1ID = s1.getId();
						int s2ID = s2.getId();
						
						if ( (swapSchools == 0) ) { //if schools are same
							return (Integer.compare(s1ID, s2ID));  
						} else {
							return 0;
						} 
					}
				});		
	}

	/**
	 * Sorts the students list by school. Same school students are sorted by ID.
	 * 
	 * Performs only one sort that uses a comparator that takes both criteria
	 * into account.
	 * 
	 * @param students
	 */
	static void sortBySchoolById2(ArrayList<Student> students) {
		Collections.sort(students, new Comparator<Student>() {
			/**
			 * Compares two Students' schools. If they attend same school, then also compares IDs and sorts
			 * 
			 * @param1 Student 1
			 * @param2 Student 2
			 *
			 * @return  Return a positive int if arg1>arg2, 0 if arg1==arg2, or a negative int if arg1<arg2
			 */
			@Override
			public int compare(Student s1, Student s2) {
				String school1 = s1.getSchool();
				String school2 = s2.getSchool();
				int swapSchools = school1.compareTo(school2);
				int s1ID = s1.getId();
				int s2ID = s2.getId();
				
				if (swapSchools == 0) {  //if schools are same
					return Integer.compare(s1ID, s2ID);
				} else {
					return swapSchools;
				} 
			}
		});
		
	}

	/**
	 *      Use this function in your bucket sort
	 *
	 * Return the bucket index for each school (schools in alphabetical order)
	 * 
	 * "UCB", "UCD", "UCI", "UCLA", "UCM", "UCSD", "UCSF"
	 *   0		1	   2	  3	      4      5        6
	 * 
	 * @param school
	 * @return the bucket index
	 */
	private static int schoolToIndex(String school) {
		switch (school) {
		case "UCB":
			return 0;
		case "UCD":
			return 1;
		case "UCI":
			return 2;
		case "UCLA":
			return 3;
		case "UCM":
			return 4;
		case "UCSD":
			return 5;
		case "UCSF":
			return 6;
		default:
			System.err.println("Unknown school " + school);
			return -1;
		}
	}

	/**
	 * Sorts the students list by school. Same school students are sorted by ID.
	 * 
	 * Places all the students of the same school into an individual bucket and
	 * sorts each bucket separately.
	 * 
	 * @param students
	 * 
	 */
	
	static void sortBySchoolById3(ArrayList<Student> students) {
		final int NUM_SCHOOLS = 7;
		// an array of lists. Each element is a reference to a list.
		ArrayList<Student>[] buckets = (ArrayList<Student>[]) new ArrayList[NUM_SCHOOLS];
		int schoolInd;
		
		//loop to populate buckets list with Students ArrayLists
		for(int i =0;i<buckets.length;i++)
			buckets[i]=new ArrayList<Student>();
		
		//loop to populate each Student ArrayLists
		for( int i =0;i<students.size();i++)
		{
			schoolInd=schoolToIndex(students.get(i).getSchool()); //gets student's school
			buckets[schoolInd].add(students.get(i)); //assigns to corresponding bucket
		}

		//sorts each bucket
		for(int i=0;i<NUM_SCHOOLS;i++)
		{
			Collections.sort(buckets[i], new Comparator<Student>() {
				/**
				 * Compares student IDs
				 * 
				 * @param1 Student 1
				 * @param2 Student 2
				 *
				 * @return Return a positive int if arg1>arg2, 0 if arg1==arg2, or a negative int if arg1<arg2
				 */
				@Override
				public int compare(Student s1, Student s2) {
		
					int s1ID = s1.getId();
					int s2ID = s2.getId();
					
						return Integer.compare(s1ID, s2ID);
					
				}
			});
		}
		
		//reassign old list
		int sCounter=0; //counter for original list
		for(int i=0;i<NUM_SCHOOLS;i++)
		{
			for(int j=0;j<buckets[i].size();j++)
			students.set(sCounter++, buckets[i].get(j));
		}
		
	}

	public static void main(String[] args) {
		ArrayList<Student> students1 = readFromFile("students.txt");
		// make 2 other copies so we don't have to read the file again
		ArrayList<Student> students2 = new ArrayList<Student>(students1);
		ArrayList<Student> students3 = new ArrayList<Student>(students1);

		// let's time the three sorts for fun. Not really that accurate.
		long start, end;
		
		start = System.currentTimeMillis();
		sortBySchoolById1(students1);
		end = System.currentTimeMillis();
		System.out.println("Two stable sorts took " + (end - start)
				+ " milliseconds.");

		start = System.currentTimeMillis();
		sortBySchoolById2(students2);
		end = System.currentTimeMillis();
		System.out.println("One stable sort with a two criteria comparator took "
						+ (end - start) + " milliseconds.");

		start = System.currentTimeMillis();
		sortBySchoolById3(students3);
		end = System.currentTimeMillis();
		System.out.println("Bucket sort took " + (end - start)
				+ " milliseconds.");

		if (!(students1.equals(students2) && students2.equals(students3))) {
			System.out.println("Fix me: One or more sorts are incorrect.");
		}
		
		writeToFile(students3, "studentsSorted.txt");
	}
}
