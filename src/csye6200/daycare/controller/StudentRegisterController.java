package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Student;

public class StudentRegisterController extends AbstractRegisterController implements Runnable{
	private Student mStudent;
	private MySQLConnection mConn;
	private boolean success = false;
	public StudentRegisterController(String name, int age,String parentName, String parentPhone, String gender, String Address, int read,int sport, int math){
		mStudent= new Student(name,age,parentName,parentPhone,gender,Address,read,sport,math);
	}
	//sync
	public boolean register() {
		String sql = ("insert into `Basic_Student` Set Name='"+mStudent.getName()+"',"+"Age='"+mStudent.getAge()+"',"
				+"parentName='"+mStudent.getParentName()+"',parentPhone='"+mStudent.getParentPhone()+"',Address='"+mStudent.getAddress()+"',Gender='"+mStudent.getGender()
				+"',ReadTest='"+mStudent.getReadTest()+"',SportTest='"+mStudent.getSportTest()+"',MathTest='"+mStudent.getMathTest()+"';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		return mConn.sendSQLInsert(sql);	
	}
	//async
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String sql = ("insert into `Basic_Student` Set Name='"+mStudent.getName()+"',"+"Age='"+mStudent.getAge()+"',"
				+"parentName='"+mStudent.getParentName()+"',parentPhone='"+mStudent.getParentPhone()+"',Address='"+mStudent.getAddress()+"',Gender='"+mStudent.getGender()
				+"',ReadTest='"+mStudent.getReadTest()+"',SportTest='"+mStudent.getSportTest()+"',MathTest='"+mStudent.getMathTest()+"';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			this.success=false;
		}
		this.success = mConn.sendSQLInsert(sql);	
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
}
