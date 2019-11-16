package csye6200.daycare.main;
/*
 * author:jf
 * egar sin
 * 
 */
public class UserCircumstances {
	public String username;
	public String password;
	public int gui;
	public int searchkind;

	private static final UserCircumstances instance = new UserCircumstances("s", "s", 1, 1);
	private UserCircumstances(String username, String password, int gui, int searchkind) {
		super();
		this.username = username;
		this.password = password;
		this.gui = gui;
		this.searchkind = searchkind;
	}
	public static UserCircumstances getInstance() {
		return instance;
	}
	
}
