package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.model.Student;

public class StudentRecordSearchController extends RecordSearchController implements Runnable{
	private Student mStudent;
	private MySQLConnection mConn;
	private boolean success = false;
	public StudentRecordSearchController(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
