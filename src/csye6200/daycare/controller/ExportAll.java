package csye6200.daycare.controller;

import csye6200.daycare.lib.MySQLConnection;

public class ExportAll {
	private MySQLConnection mConn;
	private String filepath;
	private String fromname;
	private boolean success;
	public MySQLConnection getmConn() {
		return mConn;
	}
	public void setmConn(MySQLConnection mConn) {
		this.mConn = mConn;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public ExportAll(String filepath, String fromname) {
		super();
		this.filepath = filepath;
		this.fromname = fromname;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		String  sql =("SELECT \r\n" + 
                "   *\r\n" + 
                "FROM \r\n" + 
                " "+fromname+"\r\n" + 
                "into outfile '"+filepath+"' " +
                "fields terminated by ',' " +
                "lines terminated by '\\r\\n';");

		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			this.success=false;
		}
	}
	
	public boolean export() {
		String sql = ("SELECT \r\n" + 
                "   *\r\n" + 
                "FROM \r\n" + 
                " "+fromname+"\r\n" + 
                "into outfile '"+filepath+"' " +
                "fields terminated by ',' " +
                "lines terminated by '\\r\\n';");
		System.out.println(sql);
		
		mConn = new MySQLConnection();
		if(mConn.getConnection()==null) {
			return false;
		}
		return success;
	}

}
