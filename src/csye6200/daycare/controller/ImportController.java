package csye6200.daycare.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.TableModel;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.lib.mTable;

/*
 * author:jf
 */
public class ImportController {
	private String formname;
	private String path;
	private String sql;
	private MySQLConnection mConn;
	private boolean success = false;
	private ArrayList<String> title;
	private Object[][] data;
	private int row=0;
	private int column=1;
	
	public Object[][] getData() {
		return data;
	}

	public ArrayList<String> getTitle() {
		return title;
	}

	public ImportController(String formname, String path) {
		super();
		this.path = path;
		this.formname = formname;
	}
	
	public boolean importcvs() {
	try {
	    BufferedReader br=new BufferedReader(new FileReader(path)); 
	    String line; 
	   line = br.readLine();
	   String[]titledata = line.split(","); 
	   this.title = new ArrayList<>();
	   for(String a: titledata) {
		   this.title.add(a);
	   }
	    ArrayList<ArrayList<String>> fromdata = new ArrayList<ArrayList<String>>();
	    
	    while((line=br.readLine())!=null) 
	    { 
	    ArrayList<String> content = new ArrayList<>();
	     String[]value = line.split(","); 
	     for(String a : value) {
	    	 content.add(a);
	     }
	     fromdata.add(content);
	     System.out.println(content.toString());
		
	     
	     if(formname == "Basic_Teacher") {
	      sql = ("insert into `"+formname+"` ( `Name`, `Age`, `Wage`,  `GenderFeature`, `ReadingFeature`, `SportFeature`, `MathFeature`) "
	     		+ "VALUES ('"+value[1]+"',"+value[2]+","+value[3]+","+value[5]+","+value[6]+","+value[7]+","+value[8]+");");
	     }else if(formname == "Basic_Student") {
	    	 sql = ("insert into `"+formname+"` ( `Name`, `Age`, `Grade`, `parentName`, `Address`, `parentPhone`, `Gender`,`ReadTest`, `SportTest`, `MathTest`, `Period`) "
	 	     		+ "VALUES ('"+value[1]+"',"+value[2]+","+value[3]+",'"+value[4]+"','"+value[5]+"',"+value[6]+",'"+value[7]+"',"+value[8]+","+value[9]+","+value[10]+","+value[12]+");");
	     }
	     System.out.println(sql); 
	     mConn = new MySQLConnection();
			if(mConn.getConnection()==null) {
				this.success=false;
			}
			this.success = mConn.sendSQLInsert(sql);	
		}
	    br.close();
	    Iterator<ArrayList<String>> it = fromdata.iterator();
	     this.data = new Object[fromdata.size()][this.title.size()];
		for(int i=0;i<fromdata.size();i++) {
			Object[] ta= it.next().toArray();
			System.out.println(ta.toString());
			for(int j=0;j<ta.length;j++) {
				this.data[i][j]=ta[j];
				System.out.println(ta[j]);
			}
		}
	    return true;
	}catch (IOException e) { 
	     e.printStackTrace(); 
	    } 
	return false; 
	}
}
	
	
