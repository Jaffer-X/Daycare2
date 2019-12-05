package csye6200.daycare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.TeachingRecord;

public class TeacherRecordSearchController extends AbstractSearchController implements Runnable{
	private TeachingRecord mTeaching;
	private MySQLConnection mConn;
	private boolean success = false;
	private List title;
	private List content;
	private String keyword;
	private String category;
	public TeachingRecord getmTeaching() {
		return mTeaching;
	}
	public void setmTeaching(TeachingRecord mTeaching) {
		this.mTeaching = mTeaching;
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
	
	public TeacherRecordSearchController(String category, String keyword) {
		this.keyword = keyword;
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
		String sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
				+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
				+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_TeachingRecord."+category+"='"+keyword+"';");
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
		String sql = ("select Basic_Student.Name,Basic_Teacher.Name,Basic_TeachingRecord.* from `Basic_Student`,`Basic_TeachingRecord`,`Basic_Teacher` "
				+ "where Basic_Student.StudentId=Basic_TeachingRecord.StudentId and "
				+ "Basic_Teacher.TeacherID= Basic_TeachingRecord.TeacherID and Basic_TeachingRecord."+category+"='"+keyword+"';");
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
