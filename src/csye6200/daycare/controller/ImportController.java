package csye6200.daycare.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	    while((line=br.readLine())!=null) 
	    { 
	     String[]value = line.split(","); 
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
	    return true;
	}catch (IOException e) { 
	     e.printStackTrace(); 
	    } 
	return false; 
	}
}
	
	
