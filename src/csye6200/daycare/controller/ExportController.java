package csye6200.daycare.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import csye6200.daycare.lib.mTable;

/*
 * author:jf
 */
public class ExportController {
	private mTable exportTable;
	private String path;
	public ExportController(mTable exportTable, String path) {
		super();
		this.exportTable = exportTable;
		this.path = path;
	}
	
	public  boolean exportToCSV() { 

		  try { 

			     TableModel model = exportTable.getModel(); 
			     FileWriter csv = new FileWriter(new File(path)); 

			     for (int i = 0; i < model.getColumnCount(); i++) { 
			      csv.write(model.getColumnName(i) + ","); 
			     } 

			     csv.write("\n"); 

			     for (int i = 0; i < model.getRowCount(); i++) { 
			      for (int j = 0; j < model.getColumnCount(); j++) { 
			    	  try {
			       csv.write(model.getValueAt(i, j).toString() + ","); 
			    	  } catch (Exception e) {
//			                String key = "null";
//			                csv.write(key);
			                //continue;
			    		  csv.write("");
			    		  csv.write(","); 
			            }
			      } 
			      csv.write("\n"); 
			     } 

			     csv.close(); 
			     return true; 
			    } catch (IOException e) { 
			     e.printStackTrace(); 
			    } 
			    return false; 
			} 
		
	
}

