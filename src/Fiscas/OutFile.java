package Fiscas;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class OutFile {
	//constructors
	public OutFile(ArrayList<String> outData, String out) {
		try {
			File outGoing = new File(out);
			if (!outGoing.exists())
				outGoing.createNewFile();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			FileWriter writer = new FileWriter(out);
			for (String outD: outData) {
				writer.write(outD);
				writer.write("\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}