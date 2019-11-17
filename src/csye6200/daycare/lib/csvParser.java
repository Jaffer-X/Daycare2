package csye6200.daycare.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import csye6200.daycare.model.Student;
import csye6200.daycare.model.Teacher;

/*
 * author:jf
 */
public class csvParser extends FileUtil {
	public void writeCSV (File fileName, List<String> wData) {

		try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName))){
			for (String line : wData) {
				output.write(line);
				output.newLine();
			}
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("writing Error!");
			e.printStackTrace();
		}
	}

	public List<Student> readStudentCSV(File fileName){
		List<Student> students = new ArrayList<>();
		try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while (null != (line = input.readLine())) {
					String[] fields = line.split(",");
					int id = new Integer(fields[0]);
					String name = fields[1];
					int age = new Integer(fields[2]);
					int grade = new Integer(fields[3]);
					String pName = fields[4];
					String addr = fields[5];
					String phone = fields[6];
					Timestamp date = Timestamp.valueOf(fields[7]);
					students.add(new Student(id, name, age, grade, pName, addr, phone, date));
				}
			input.close();
		} catch (IOException e) {
			System.out.println("reading Error!");
			e.printStackTrace();
		}
		return students;
	}
	
	public List<Teacher> readTeacherCSV(File fileName){
		List<Teacher> teachers = new ArrayList<>();
		try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while (null != (line = input.readLine())) {
				String[] fields = line.split(",");
				int id = new Integer(fields[0]);
				String name = fields[1];
				int age = new Integer(fields[2]);
				int wage = new Integer(fields[3]);
				Timestamp date = Timestamp.valueOf(fields[4]);
				teachers.add(new Teacher(id, name, age, wage, date));
				}
			input.close();
		} catch (IOException e) {
			System.out.println("reading Error!");
			e.printStackTrace();
		}
		return teachers;
	}
	

	
}  
     
}


