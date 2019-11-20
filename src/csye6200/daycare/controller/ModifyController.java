package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Teacher;

public class ModifyController extends AbstractModifyController implements Runnable{
	
	private Teacher uTeacher;
	private MySQLConnection mConn;
	private boolean success = false;
	private int id;
	private String changekeyword;
	private Object changedata;
	
	private int changetype;
	
	
	public int getChangetype() {
		return changetype;
	}
	public void setChangetype(int changetype) {
		this.changetype = changetype;
	}
	public String getChangekeyword() {
		return changekeyword;
	}
	public void setChangekeyword(String changekeyword) {
		this.changekeyword = changekeyword;
	}
	public Object getChangedata() {
		return changedata;
	}
	public void setChangedata(Object changedata) {
		this.changedata = changedata;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Teacher getmTeacher() {
		return uTeacher;
	}
	public void setmTeacher(Teacher mTeacher) {
		this.uTeacher = mTeacher;
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
	
//	public TeacherModifyController(int id,String name, int age, int wage) {
//		uTeacher = new Teacher(name, age, wage);
//		this.id = id;
//	}
	
	public ModifyController(String changekeyword,Object changedata, int id, int changetype) {
		this.changekeyword = changekeyword;
		this.changedata = changedata;
		this.id = id;
		this.changetype = changetype;
	}
	
	public boolean update() {
//		String sql = ("update `Basic_Teacher` Set Name='"+uTeacher.getName()+"',"+"Age='"+uTeacher.getAge()+"',"
//				+"Wage='"+uTeacher.getWage()+"' where TeacherId='"+id+"';");
		String sql = null;
		switch(changetype) {
		case 1:
			 sql = ("update `Basic_Teacher` set "+changekeyword+"= '"+changedata+"' where TeacherId= '"+id+"';");
			break;
	
		case 2:
			 sql = ("update `Basic_TeachingRecord` set "+changekeyword+"= '"+changedata+"' where RecordId= '"+id+"';");
			break;
		case 3:
			sql = ("update `Basic_Student` set "+changekeyword+"= '"+changedata+"' where StudentId= '"+id+"';");
			break;
		
			
		}	
		System.out.println(sql);
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		return mConn.sendSQLInsert(sql);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String sql = ("update into `Basic_Teacher` Set Name='"+uTeacher.getName()+"',"+"Age='"+uTeacher.getAge()+"',"
				+"Wage='"+uTeacher.getWage()+"' where TeacherId='"+id+"';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			this.success=false;
		}
		this.success = mConn.sendSQLInsert(sql);	
	}
}
