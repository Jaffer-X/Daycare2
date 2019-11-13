package csye6200.daycare.model;

import java.sql.Timestamp;

/*
 * author:jf
 */
public class Classroom {
	private int ClassroomId;
	private int Capacity;
	private int Remain;
	private Timestamp Timestamp ;
	public int getClassroomId() {
		return ClassroomId;
	}
	public void setClassroomId(int classroomId) {
		ClassroomId = classroomId;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	public int getRemain() {
		return Remain;
	}
	public void setRemain(int remain) {
		Remain = remain;
	}
	public Timestamp getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		Timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Classroom [ClassroomId=" + ClassroomId + ", Capacity=" + Capacity + ", Remain=" + Remain
				+ ", Timestamp=" + Timestamp + "]";
	}
	public Classroom(int classroomId, int capacity, int remain, java.sql.Timestamp timestamp) {
		super();
		ClassroomId = classroomId;
		Capacity = capacity;
		Remain = remain;
		Timestamp = timestamp;
	}
	
	
}
