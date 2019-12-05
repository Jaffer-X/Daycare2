package csye6200.daycare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import csye6200.daycare.controller.StudentSearchController.SEARCH_STUDENT;
import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Student;

public class StudentRecordSearchController extends RecordSearchController implements Runnable{
	public enum SEARCH_RECORD{RECORD_TEACHING,RECORD_IMMUNIZATION};
	private Student mStudent;
	private MySQLConnection mConn;
	private boolean success = false;
	private List title;
	private List content;
	private String keyword;
	private String category;
	private boolean containAll = false;
	private boolean containUpcoming = false;
	private boolean containOverdue=false;
	private SEARCH_RECORD current_search = SEARCH_RECORD.RECORD_TEACHING;
	public Student getmStudent() {
		return mStudent;
	}
	public void setmStudent(Student mStudent) {
		this.mStudent = mStudent;
	}
	public MySQLConnection getmConn() {
		return mConn;
	}
	public void setmConn(MySQLConnection mConn) {
		this.mConn = mConn;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List getTitle() {
		return title;
	}
	public void setTitle(List title) {
		this.title = title;
	}
	public List getContent() {
		return content;
	}
	public void setContent(List content) {
		this.content = content;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isContainAll() {
		return containAll;
	}
	public void setContainAll(boolean containAll) {
		this.containAll = containAll;
	}
	public boolean isContainUpcoming() {
		return containUpcoming;
	}
	public void setContainUpcoming(boolean containUpcoming) {
		this.containUpcoming = containUpcoming;
	}
	public boolean isContainOverdue() {
		return containOverdue;
	}
	public void setContainOverdue(boolean containOverdue) {
		this.containOverdue = containOverdue;
	}
	public SEARCH_RECORD getCurrent_search() {
		return current_search;
	}
	public void setCurrent_search(SEARCH_RECORD current_search) {
		this.current_search = current_search;
	}
	public StudentRecordSearchController(String category, String keyword,SEARCH_RECORD s){
		this.keyword=keyword;
		this.category=category;
		this.current_search=s;
	}
	public Object[][] getDataString(){
		int row = content.size();
		int column = title.size();
		Object[][] tmp = new Object[row][column];
		Iterator<ArrayList> it=content.iterator();
		for(int i=0;i<row;i++) {
			Object[] ta= it.next().toArray();
			for(int j=0;j<column;j++) {
				tmp[i][j]=ta[j];
			}
		}
		return tmp;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String sql="";
		if(this.current_search==SEARCH_RECORD.RECORD_TEACHING)
			sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
					+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
					+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_Student."+category+"='"+keyword+"';");
		else if (this.current_search==SEARCH_RECORD.RECORD_IMMUNIZATION)
			System.out.println(isContainAll());
			if(isContainAll()) {
				sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
					+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
					+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_Student."+category+"='"+keyword+"';");
			}else {
				if(isContainUpcoming() && !isContainOverdue()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_ImmunizationRecord.Alert='upcoming' "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else if(!isContainUpcoming() && isContainOverdue()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_ImmunizationRecord.Alert='overdue' "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else if(isContainOverdue() && isContainUpcoming()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID "
							+ "and (Basic_ImmunizationRecord.Alert='overdue' or Basic_ImmunizationRecord.Alert='upcoming') "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else {
					sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
							+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
							+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_Student."+category+"='"+keyword+"';");
				}
			}
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			this.success=false;
		}
		content = mConn.sendSQLQuery(sql);
		title=mConn.getTitle();
		if(!content.isEmpty()) {
			this.success=true;
		}
	}
	public boolean query() {
		String sql="";
		if(this.current_search==SEARCH_RECORD.RECORD_TEACHING)
			sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
					+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
					+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_Student."+category+"='"+keyword+"';");
		else if (this.current_search==SEARCH_RECORD.RECORD_IMMUNIZATION)
			System.out.println(isContainAll());
			if(isContainAll()) {
				sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
					+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
					+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_Student."+category+"='"+keyword+"';");
			}else {
				if(isContainUpcoming() && !isContainOverdue()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_ImmunizationRecord.Alert='upcoming' "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else if(!isContainUpcoming() && isContainOverdue()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_ImmunizationRecord.Alert='overdue' "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else if(isContainOverdue() && isContainUpcoming()) {
					sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
							+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
							+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID "
							+ "and (Basic_ImmunizationRecord.Alert='overdue' or Basic_ImmunizationRecord.Alert='upcoming') "
							+ "and Basic_Student."+category+"='"+keyword+"';");
				}else {
					sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
							+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
							+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_Student."+category+"='"+keyword+"';");
				}
			}
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		content = mConn.sendSQLQuery(sql);
		if(content.isEmpty()) {
			return false;
		}else {
			title=mConn.getTitle();
			return true;
		}
	}
}
