package csye6200.daycare.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * author:jf
 */
public class FileUtil {
	public void writeFile(String fileName, List<String> wData) {
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
	public List<String> readFile(String fileName){
		List<String> rData = new ArrayList<>();
		try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
			String data = null;
			while (null != (data = input.readLine())) {
				rData.add(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("reading Error!");
			e.printStackTrace();
		}

		return rData;

	}
}
