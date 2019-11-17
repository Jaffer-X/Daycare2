package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Student;

public class StudentRegisterController extends AbstractRegisterController {
	private Student mStudent;
	MySQLConnection mConn;
	public StudentRegisterController(String name, int age,String parentName, String parentPhone, String gender, String Address, int read,int sport, int math){
		mStudent= new Student(name,age,parentName,parentPhone,gender,Address,read,sport,math);
	}
	public boolean register() {
		String sql = ("insert into `Basic_Student` Set Name='"+mStudent.getName()+"',"+"Age='"+mStudent.getAge()+"',"
				+"parentName='"+mStudent.getParentName()+"',parentPhone='"+mStudent.getParentPhone()+"',Address='"+mStudent.getAddress()
				+"',ReadTest='"+mStudent.getReadTest()+"',SportTest='"+mStudent.getSportTest()+"',MathTest='"+mStudent.getMathTest()+"';");
		System.out.println(sql);
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		return mConn.sendSQLInsert(sql);	
	}
}
