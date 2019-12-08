package csye6200.daycare.controller;

import csye6200.daycare.lib.mHttpClient;
import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.main.UserCircumstances;
import csye6200.daycare.model.Student;

public class StudentRegisterController extends AbstractRegisterController implements Runnable{
	private Student mStudent;
	private boolean success = false;
	public StudentRegisterController(String name, int age,String parentName, String parentPhone, String gender, String Address, int read,int sport, int math){
		mStudent= new Student(name,age,parentName,parentPhone,gender,Address,read,sport,math);
	}
	//sync
	public boolean register() {
		String mjson = ("{\"name\":\""+mStudent.getName()+"\","
			      + "\"age\":"+mStudent.getAge()+","
			      + "\"parentName\":\""+mStudent.getParentName()+"\","
			      + "\"address\":\""+mStudent.getAddress()+"\","
			      + "\"parentPhone\":\""+mStudent.getParentPhone()+"\","
			      + "\"gender\":\""+mStudent.getGender()+"\"," 
			      + "\"read\":"+mStudent.getReadTest()+","
			      + "\"sport\":"+mStudent.getSportTest()+","
			      + "\"math\":"+mStudent.getMathTest()+","
			      + "\"algorithm\":\""+UserCircumstances.getInstance().getCurrent_strategy().ordinal()+"\"}");
		System.out.println(mjson);
		
		String res;
		mHttpClient hc = new mHttpClient();
		res=hc.sendPost("http://119.3.209.144:6200", mjson);
		System.out.println(res);
		if(!res.contains("-1")) {
			this.success=true;
			return true;
		}
		return false;
	}
	//async
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String mjson = ("{\"name\":\""+mStudent.getName()+"\","
			      + "\"age\":"+mStudent.getAge()+","
			      + "\"parentName\":\""+mStudent.getParentName()+"\","
			      + "\"address\":\""+mStudent.getAddress()+"\","
			      + "\"parentPhone\":\""+mStudent.getParentPhone()+"\","
			      + "\"gender\":\""+mStudent.getGender()+"\"," 
			      + "\"read\":"+mStudent.getReadTest()+","
			      + "\"sport\":"+mStudent.getSportTest()+","
			      + "\"math\":"+mStudent.getMathTest()+","
			      + "\"algorithm\":\""+UserCircumstances.getInstance().getCurrent_strategy().ordinal()+"\"}");
		System.out.println(mjson);
	
		String res;
		mHttpClient hc = new mHttpClient();
		res=hc.sendPost("http://119.3.209.144:6200", mjson);
		System.out.println(res);
		if(!res.contains("-1")) {
			this.success=true;
		}	
	}
	public Student getmStudent() {
		return mStudent;
	}
	public void setmStudent(Student mStudent) {
		this.mStudent = mStudent;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
