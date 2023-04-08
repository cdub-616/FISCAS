package Fiscas;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildTable {
	//fields
	private HashMap<String, Integer> table = new HashMap<>();
	private ArrayList<String> labels = new ArrayList<>();
	
	//constructors
	public BuildTable(ArrayList<String> lines) {
		
		String[] labelLine = {""};
		int lineNumber = 0;
		final int EXCEEDED_STORAGE = 64;
		
		for (String str: lines) {
			if (str.contains(":")) {
				labelLine = str.trim().split(":");
				String newLabel = labelLine[0];
				if (!labels.isEmpty()) {
					if (labels.contains(newLabel)) {
						System.out.println("Label <" + newLabel + "> on " +
							"line "	+ lineNumber + " is already defined");
						System.exit(-1);
					}
				}
				labels.add(newLabel);
				if (lineNumber < EXCEEDED_STORAGE)
					table.put(newLabel, lineNumber);
				else {
					System.out.println("Exceeded system memory");
					System.exit(-1);
				}
			}
			if (labelLine.length > 1)
				lineNumber++;
		}
	}
	
	//methods
	public HashMap<String, Integer> getTable() {
		return table;
	}
	public ArrayList<String> getLabels(){
		return labels;
	}
}