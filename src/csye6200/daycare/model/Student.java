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
