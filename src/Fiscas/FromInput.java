package Fiscas;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FromInput {
	
	//fields
	private ArrayList<String> lines = new ArrayList<>();
	
	//constructors
	public FromInput(String filename) {
			
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] trimLine =  line.trim().split(";");
				if (trimLine.length > 0) 
					if (trimLine[0] != "") 
						lines.add(trimLine[0].toLowerCase());
			}
			sc.close();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}	
	}
	
	//methods
	public ArrayList<String> getLines() {
		return lines;
	}
}