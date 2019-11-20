package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Teacher;

public class TeacherModifyController extends AbstractModifyController implements Runnable{
	
	private Teacher uTeacher;
	private MySQLConnection mConn;
	private boolean success = false;
	private int id;
	
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
	
	public TeacherModifyController(int id,String name, int age, int wage) {
		uTeacher = new Teacher(name, age, wage);
		this.id = id;
	}
	
	public boolean update() {
		String sql = ("update `Basic_Teacher` Set Name='"+uTeacher.getName()+"',"+"Age='"+uTeacher.getAge()+"',"
				+"Wage='"+uTeacher.getWage()+"' where TeacherId='"+id+"';");
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
