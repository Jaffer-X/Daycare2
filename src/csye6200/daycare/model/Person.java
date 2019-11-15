package csye6200.daycare.model;

import java.sql.Timestamp;

/*
 * author:jf
 */
public class Person {
	private String Name;
	private int Age;
	private Timestamp Timestamp;
	public Person(String name, int age, Timestamp datetime) {
		super();
		this.Name = name;
		this.Age = age;
		this.Timestamp = datetime;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		this.Age = age;
	}
	public Timestamp getTimestamp() {
		return Timestamp;
	}
	public void setTimestampe(Timestamp timestamp) {
		this.Timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Person [name=" + Name + ", age=" + Age + ", datetime=" + Timestamp + "]";
	}
	
}
