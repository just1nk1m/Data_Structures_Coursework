package Sorts;

public class Student {
	
	private int id;
	private String name;
	private String school;
	
	public Student (int id, String name, String school) {
		this.id = id;
		this.name = name;
		this.school = school;
	}

	int getId() {
		return id;
	}
	
	String getSchool() {
		return school;
	}
	
	@Override
	public String toString() {
		return name + "," + id + "," + school;
	}
}
