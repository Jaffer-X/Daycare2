package csye6200.daycare.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Teacher;

public class TeacherSearchController extends AbstractSearchController implements Runnable {
	
	private Teacher mTeacher;
	private MySQLConnection mConn;
	private boolean success = false;
	private List title;
	private List content;
	private String keyword;
	private String category;

	private boolean containFeature = false;
	private boolean containClassroom = false;
	private boolean containGroup=false;
//	private boolean containClassroom = false;
//	private boolean containGroup=false;
//	private boolean containFeature = false;
	
	
	
	public Teacher getmTeacher() {
		return mTeacher;
	}


	public boolean isContainFeature() {
		return containFeature;
	}


	public void setContainFeature(boolean containFeature) {
		this.containFeature = containFeature;
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


	public void setmTeacher(Teacher mTeacher) {
		this.mTeacher = mTeacher;
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


	public TeacherSearchController(String category, String keyword) {
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
		String sql = null;
		if(keyword.equals("all") || keyword.equals("ALL")) {
			 sql = ("select * from `Basic_Teacher`;");
		}else {
			if(!this.containFeature&&!this.containGroup&&!this.containClassroom) {
		 sql = ("select * from `Basic_Teacher` where "+category+"='"+keyword+"';");
			}else if(this.containFeature&&this.containGroup&&this.containClassroom) {
			 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId,r.ClassroomId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
			 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(this.containClassroom && !this.containGroup && !this.containFeature) {
			 sql =("select t.Name,t.Age,t.Wage,r.ClassroomId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
				 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(this.containClassroom && this.containGroup && !this.containFeature) {
			 sql =("select t.Name,t.Age,t.Wage,r.ClassroomId,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
				 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(this.containClassroom && !this.containGroup && this.containFeature) {
			 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
				 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(!this.containClassroom && this.containGroup && !this.containFeature) {
			 sql =("select t.Name,t.Age,t.Wage,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
				 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(!this.containClassroom && this.containGroup && this.containFeature) {
			 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
				 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
		 }else if(!this.containClassroom && !this.containGroup && this.containFeature) {
			 sql = ("select * from `Basic_Teacher` where "+category+"='"+keyword+"';");
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
		String sql = null;
		if(keyword == "all" || keyword == "ALL") {
			 sql = ("select * from `Basic_Teacher`;");
		}else {
			if(!this.containFeature&&!this.containGroup&&!this.containClassroom) {
				 sql = ("select * from `Basic_Teacher` where "+category+"='"+keyword+"';");
					}else if(this.containFeature&&this.containGroup&&this.containClassroom) {
					 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId,r.ClassroomId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
					 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(this.containClassroom && !this.containGroup && !this.containFeature) {
					 sql =("select t.Name,t.Age,t.Wage,r.ClassroomId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
						 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(this.containClassroom && this.containGroup && !this.containFeature) {
					 sql =("select t.Name,t.Age,t.Wage,r.ClassroomId,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
						 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(this.containClassroom && !this.containGroup && this.containFeature) {
					 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
						 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(!this.containClassroom && this.containGroup && !this.containFeature) {
					 sql =("select t.Name,t.Age,t.Wage,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
						 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(!this.containClassroom && this.containGroup && this.containFeature) {
					 sql =("select t.Name,t.Age,t.Wage,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.GroupId from `Basic_Teacher` t, `Basic_TeachingRecord` r"
						 		+ " where t.TeacherId=r.TeacherId and t."+category+"='"+keyword+"';");
				 }else if(!this.containClassroom && !this.containGroup && this.containFeature) {
					 sql = ("select * from `Basic_Teacher` where "+category+"='"+keyword+"';");
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
