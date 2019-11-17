package csye6200.daycare.main;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;

import csye6200.daycare.lib.MySQLConnection;
import csye6200.daycare.view.LoginWindow;

/*
 * author:fc(chong fan), jf(jiafeng xie)
 */
public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginWindow.getInstance().mshow();
//		MySQLConnection m = new MySQLConnection();
//		List ml = m.getData("select * from `Basic_Student`;");
//		Iterator<ArrayList> it=ml.iterator();
//		List mt = m.getTitle();
//		Iterator<ArrayList> it2=mt.iterator();
//		while(it.hasNext()) {
//			ArrayList ma = it.next();
//			Iterator iter = ma.iterator();
//			while (iter.hasNext()) {
//				System.out.println(it2.next()+":"+iter.next());
//			}
//		}
	}
}
