package csye6200.daycare.lib;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MySQLConnection {
    private static final String url = "jdbc:mysql://119.3.209.144:3306/DayCare";
    private static final String user = "csye6200";
    private static final String password = "!Csye6200";

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet resultSet = null;
    
    private ArrayList titleResult = new ArrayList();
    private ArrayList dataResult = new ArrayList();
    
    public MySQLConnection() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("error in init MySQL connection");
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
    	return this.conn;
    }
    
    public ArrayList getTitle() {
    	return this.titleResult;
    }
    
    private List convertList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            //Map rowData = new TreeMap();
            ArrayList rowList = new ArrayList();
            for (int i = 1; i <= columnCount; i++) {
            	rowList.add(rs.getObject(i));
            	titleResult.add(md.getColumnName(i));
                //rowData.put(md.getColumnName(i), rs.getObject(i));
                //System.out.println(md.getColumnName(i)+":"+rs.getObject(i)+".");
            }
            //list.add(rowData);
            dataResult.add(rowList);
        }
        return dataResult;
    }
    
    public List getData(String sql) {
    	titleResult.clear();
    	dataResult.clear();
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            return convertList(resultSet);
        } catch (Exception e) {
            System.out.println("error in execute sql");
            e.printStackTrace();
        }
		return null;
    }
    
    public boolean sendSQL(String sql) {
    	int result=0;
        try {
            pst = conn.prepareStatement(sql);
            result = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in execute sql");
            e.printStackTrace();
        }
		return result != 0?true:false;
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (Exception e) {
            System.out.println("error in close Mysql connection");
            e.printStackTrace();
        }
    }
}
