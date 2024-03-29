package csye6200.daycare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Student;

public class StudentSearchController extends AbstractSearchController implements Runnable {
	public enum SEARCH_STUDENT{STUDENT_BASIC,STUDENT_IMMUNIZATION};
	private Student mStudent;
	private MySQLConnection mConn;
	private boolean success = false;
	private List title;
	private List content;
	private String keyword;
	private String category;
	private boolean containTeacher = false;
	private boolean containClassroom = false;
	private boolean containGroup=false;
	private SEARCH_STUDENT current_search = SEARCH_STUDENT.STUDENT_BASIC;
	public StudentSearchController(String category, String keyword,SEARCH_STUDENT s){
		this.keyword=keyword;
		this.category=category;
		this.current_search=s;
	}
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
		if(this.current_search==SEARCH_STUDENT.STUDENT_BASIC) {
			if(this.keyword.equals("all")|| this.keyword.equals("ALL")) {
				 sql = ("select * from `Basic_Student`;");
			}else {
			if(!this.containClassroom&&!this.containGroup&&!this.containTeacher) {
				sql = ("select * from `Basic_Student` where "+category+"='"+keyword+"';");
			}else if(this.containTeacher&&this.containGroup&&this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.GroupId,r.ClassroomId from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& !this.containGroup&& !this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& this.containGroup&& !this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.GroupId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& this.containGroup&& !this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.GroupId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& this.containGroup&& this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.GroupId,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& !this.containGroup&& this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& !this.containGroup&& this.containClassroom) {
				sql = ("select s.StudentId,s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}
			}
		}
		else if (this.current_search==SEARCH_STUDENT.STUDENT_IMMUNIZATION)
			sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
					+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
					+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_Student."+category+"='"+keyword+"';");
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
		if(this.current_search==SEARCH_STUDENT.STUDENT_BASIC) {
			if(this.keyword.equals("all")|| this.keyword.equals("ALL")) {
				 sql = ("select * from `Basic_Student`;");
			}else {
			if(!this.containClassroom&&!this.containGroup&&!this.containTeacher) {
				sql = ("select * from `Basic_Student` where "+category+"='"+keyword+"';");
			}else if(this.containTeacher&&this.containGroup&&this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.GroupId,r.ClassroomId from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& !this.containGroup&& !this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& this.containGroup&& !this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.GroupId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& this.containGroup&& !this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.GroupId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& this.containGroup&& this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.GroupId,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(!this.containTeacher&& !this.containGroup&& this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}else if(this.containTeacher&& !this.containGroup&& this.containClassroom) {
				sql = ("select s.name,s.age,s.grade,s.readTest,s.sportTest,s.MathTest,r.teacherId,r.ClassroomId "
						+ "from `Basic_Student` s, `Basic_TeachingRecord` r where s.studentId=r.studentId and s."+category+"='"+keyword+"';");
			}
			}
		}
		else if (this.current_search==SEARCH_STUDENT.STUDENT_IMMUNIZATION)
			sql = ("select Basic_Student.Name,Basic_Vaccine.name,Basic_ImmunizationRecord.* from `Basic_Student`,`Basic_ImmunizationRecord`,`Basic_Vaccine` "
					+ "where Basic_Student.StudentID = Basic_ImmunizationRecord.StudentID and "
					+ "Basic_Vaccine.VaccineId=Basic_ImmunizationRecord.VaccineID and Basic_Student."+category+"='"+keyword+"';");
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
	
	public boolean isContainTeacher() {
		return containTeacher;
	}
	public void setContainTeacher(boolean containTeacher) {
		this.containTeacher = containTeacher;
	}
	public boolean isContainClassroom() {
		return containClassroom;
	}
	public void setContainClassroom(boolean containClassroom) {
		this.containClassroom = containClassroom;
	}
	public boolean isContainGroup() {
		return containGroup;
	}
	public void setContainGroup(boolean containGroup) {
		this.containGroup = containGroup;
	}
	public boolean queryall() {
		String sql = ("select * from `Basic_Student` ;");
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
