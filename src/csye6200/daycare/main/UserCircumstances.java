package csye6200.daycare.main;
/*
 * author:jf
 * egar sin
 * 
 */
public class UserCircumstances {
	public enum GUI{LOGIN_WINDOW,MAIN_WINDOW,TEACHER_WINDOW,STUDENT_WINDOW,ADVANCE_WINDOW};
	public enum ASSIGN_STRATEGY{BASIC,AI_SVM,AI_DT};
	private String username;
	private String password;
	private GUI current_gui;
	private String last_sql = "";
	private boolean dataBaseOP_asyn = true;
	private ASSIGN_STRATEGY current_strategy = ASSIGN_STRATEGY.BASIC; 

	private static final UserCircumstances instance = new UserCircumstances("admin", "admin", GUI.LOGIN_WINDOW);
	private UserCircumstances(String username, String password, GUI gui) {
		super();
		this.username = username;
		this.password = password;
		this.current_gui = gui;
		
	}
	public ASSIGN_STRATEGY getCurrent_strategy() {
		return current_strategy;
	}
	public void setCurrent_strategy(ASSIGN_STRATEGY current_strategy) {
		this.current_strategy = current_strategy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public GUI getCurrent_gui() {
		return current_gui;
	}
	public void setCurrent_gui(GUI current_gui) {
		this.current_gui = current_gui;
	}
	public String getLast_sql() {
		return last_sql;
	}
	public void setLast_sql(String last_sql) {
		this.last_sql = last_sql;
	}
	public boolean isDataBaseOP_asyn() {
		return dataBaseOP_asyn;
	}
	public void setDataBaseOP_asyn(boolean dataBaseOP_asyn) {
		this.dataBaseOP_asyn = dataBaseOP_asyn;
	}
	public static UserCircumstances getInstance() {
		return instance;
	}
	
}
