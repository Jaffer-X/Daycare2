package csye6200.daycare.model;

import java.sql.Timestamp;

/*
 * author:jf
 */
public class Vaccine {
	private int VaccineId;
	private String Name;
	private int Dose;
	private String Period;
	private String Category;
	private Timestamp Timestamp;
	public int getVaccineId() {
		return VaccineId;
	}
	public void setVaccineId(int vaccineId) {
		VaccineId = vaccineId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getDose() {
		return Dose;
	}
	public void setDose(int dose) {
		Dose = dose;
	}
	public String getPeriod() {
		return Period;
	}
	public void setPeriod(String period) {
		Period = period;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public Timestamp getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		Timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Vaccine [VaccineId=" + VaccineId + ", Name=" + Name + ", Dose=" + Dose + ", Period=" + Period
				+ ", Category=" + Category + ", Timestamp=" + Timestamp + "]";
	}
	public Vaccine(int vaccineId, String name, int dose, String period, String category, java.sql.Timestamp timestamp) {
		super();
		VaccineId = vaccineId;
		Name = name;
		Dose = dose;
		Period = period;
		Category = category;
		Timestamp = timestamp;
	}
	
	
}
