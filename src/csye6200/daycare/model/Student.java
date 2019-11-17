package csye6200.daycare.model;

import java.sql.Timestamp;

/*
 * author:jf
 */
public class Student extends Person {
	
	private int StudentId;
	private int Grade;
	private String ParentName;
	private String Address;
	private String ParentPhone;
	private String gender;
	private int readTest;
	private int sportTest;
	private int mathTest;
	
	public Student(String name, int age,String parentName, String parentPhone, String gender, String Address, int read,int sport, int math){
		super(name,age);
		this.ParentName=parentName;
		this.ParentPhone=parentPhone;
		this.Address = Address;
		this.readTest = read;
		this.sportTest = sport;
		this.mathTest = math;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getReadTest() {
		return readTest;
	}

	public void setReadTest(int readTest) {
		this.readTest = readTest;
	}

	public int getSportTest() {
		return sportTest;
	}

	public void setSportTest(int sportTest) {
		this.sportTest = sportTest;
	}

	public int getMathTest() {
		return mathTest;
	}

	public void setMathTest(int mathTest) {
		this.mathTest = mathTest;
	}

	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getParentPhone() {
		return ParentPhone;
	}
	public void setParentPhone(String parentPhone) {
		ParentPhone = parentPhone;
	}
	@Override
	public String toString() {
		return "Student [StudentId=" + StudentId + ", Name=" + getName() + ", Age=" + getAge() + ", Grade=" + Grade + ", ParentName=" + ParentName + ", Address="
				+ Address + ", ParentPhone=" + ParentPhone + ", Timestamp=" + getTimestamp() + "]";
	}
	public Student( int studentId,String name, int age, int grade, String parentName, String address,
			String parentPhone, Timestamp timestamp) {
		super(name, age, timestamp);
		StudentId = studentId;
		Grade = grade;
		ParentName = parentName;
		Address = address;
		ParentPhone = parentPhone;
	}
	
}
