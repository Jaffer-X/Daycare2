package csye6200.daycare.controller;

import java.util.List;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.main.UserCircumstances;
import csye6200.daycare.model.Student;

public class LoginController {
	private MySQLConnection mConn;
	private String username;
	private String password;
	public LoginController(String username, String password){
		this.username = username;
		this.password = password;
		UserCircumstances.getInstance().setUsername(username);
		UserCircumstances.getInstance().setPassword(password);
	}
	//sync
	public boolean login() {
		String sql = ("select password from Manage_Account where username='"+this.username+"';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		List m = (List) mConn.sendSQLQuery(sql).get(0);
		m.get(0);
		System.out.println(m.get(0));
		if(m.get(0).equals(this.password)) return true;
		else return false;
	}
}
