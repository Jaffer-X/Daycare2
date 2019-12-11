



package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Teacher;

public class TeacherRegisterController extends AbstractRegisterController implements Runnable{
	private Teacher mTeacher;
	private MySQLConnection mConn;
	private boolean success = false;
	
	
	public Teacher getmTeacher() {
		return mTeacher;
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

	public TeacherRegisterController(String name, int age, int wage){
		mTeacher= new Teacher(name,age,wage);
	}
	
	
	public boolean register() {
		String sql = ("insert into `Basic_Teacher` Set Name='"+mTeacher.getName()+"',"+"Age='"+mTeacher.getAge()+"',"
				+"Wage='"+mTeacher.getWage()+"';");
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
		String sql = ("insert into `Basic_Teacher` Set Name='"+mTeacher.getName()+"',"+"Age='"+mTeacher.getAge()+"',"
				+"Wage='"+mTeacher.getWage()+"';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			this.success=false;
		}
		this.success = mConn.sendSQLInsert(sql);	
	}

}
