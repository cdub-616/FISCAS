package Fiscas;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Fiscas {
	
	public static void main(String[] args) {
		
		//fields
		boolean printTable = false;
		ArrayList<String> lines = new ArrayList<>();
		ArrayList<String> outFileAr = new ArrayList<>();
		ArrayList<String> instructions = new ArrayList<>();
		
		//check for command line with at least two arguments
		/*if (args.length < 2 || args.length > 3 ) { //test for # arguments
			printUsage();
		}
		String[] testIn = args[0].split("\\.");
		String[] testOut = args[1].split("\\.");
		if (testIn.length != 2 || testOut.length != 2) //test for file ext
			printUsage();
		if (args.length == 3 && args[2].equals("-l"))  //check for -l
			printTable = true;*/
		
		//to test
		//String[] test = {"input.s", "output.hex", "-l"};
		String[] test = {"input2.s", "output2.hex", "-l"};
		if (test.length < 2 || test.length > 3) {
			printUsage();
		}
		String[] testIn = test[0].split("\\.");
		String[] testOut = test[1].split("\\.");
		if (testIn.length != 2 || testOut.length != 2)
			printUsage();
		if (test.length == 3 && test[2] == "-l")
			printTable = true;
		
		//read input file and build ArrayList
		FromInput inFile = new FromInput(test[0]);
		lines = inFile.getLines();
		
		//pass 1:  build symbol table
		BuildTable bTable = new BuildTable(lines);
		HashMap<String, Integer> table = bTable.getTable();
		
		//pass 2:  create output ArrayList using symbol table
		Pass2 output = new Pass2(lines, table);
		outFileAr = output.getOutput();
		
		//create output file
		OutFile goingOut = new OutFile(outFileAr, test[1]);
		//OutFile goingOut = new OutFile(outFileAr, args[1]);
		
		//print table
		instructions = output.getLines();
		if (printTable == true)
			printTable(table, instructions, outFileAr);
	}

	private static void printUsage() {
		System.out.println("USAGE:  java Fiscas.javaq <source file> " +
			"<object file> [-l]");
			System.out.println("\t\t-l : print listing to standard error");
			System.exit(-1);
	}
	private static void printTable(HashMap<String, Integer> map, 
			ArrayList<String> list, ArrayList<String> hexList) {
		System.out.println("*** LABEL LIST ***");
		for(String labels: map.keySet()) {
			System.out.print(labels + "\t");
			System.out.format("%02d\n", map.get(labels));
		}
		System.out.println("*** MACHINE PROGRAM ***");
		int lineCount = 0;
		for (int i = 0; i < list.size(); i++) {
			System.out.format("%02d:", lineCount);
			System.out.println(hexList.get(i + 1) + "\t" + list.get(i));
			lineCount++;
		}		
	}
}