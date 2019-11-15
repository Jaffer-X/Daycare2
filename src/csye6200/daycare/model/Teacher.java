package csye6200.daycare.model;

import java.sql.Timestamp;

/*
 * author:jf
 */
public class Teacher extends Person {
	private int TeacherId;
	private int Wage;
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
	public int getWage() {
		return Wage;
	}
	public void setWage(int wage) {
		Wage = wage;
	}
	@Override
	public String toString() {
		return "Teacher [TeacherId=" + TeacherId + ", Name=" + getName() + ", Age="
				+ getAge() + ", Wage=" + Wage + ", Timestamp=" + getTimestamp() + "]";
	}
	public Teacher( int teacherId, String name, int age, int wage, Timestamp timestamp) {
		super(name, age, timestamp);
		TeacherId = teacherId;
		Wage = wage;
	}
	
	
	
}
