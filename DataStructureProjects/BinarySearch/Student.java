package BinarySearch;

/**
 * 
 * @author Justin Kim
 * @ID 1385008
 *
 */

public class Student implements Comparable<Student> {

	private int id;
	private String name, school;

/**
* Default Student constructor
* 
* @param none;
* 
* @return none;
**/
	public Student() {

	}
/**
 * Student constructor initializes relevant variables
 * 
 * @param name
 * 			Student's name
 * @param id
 * 			Student's ID
 * @param school
 * 			Student's school
 * @return none.
 */
	public Student(String name, int id, String school) {
		this.name = name;
		this.id = id;
		this.school = school;
	}
/**
 * Overridden equals method returns boolean variable depending on id values
 * 
 * @param Object
 * 			Object to be checked
 * @return boolean
 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Student) { //checks to see if obj is a Student otherwise not equal
			Student other = (Student) obj;
			return this.id == other.id;
		}
		else
			return false;
	}
/**
 * Overridden compareTo method compares two Student ids
 * 
 * @param Student
 * 			The Student to be compared
 * @return int value depending on id values
 */
	@Override
	public int compareTo(Student o) {
//		if (id > o.id)
//			return 1;
//		else if (id == o.id)
//			return 0;
//		else
//			return -1;
		return Integer.compare(this.id, o.id); 

	}
/**
 * Overridden toString method returns String values of Student object
 * 
 * @param none
 * 
 * @return String representing Student object
 */
	@Override
	public String toString() {

		return name + ", " + id + ", " + school;

	}
}
