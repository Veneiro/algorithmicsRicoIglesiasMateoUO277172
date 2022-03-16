package algstudent.s4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public abstract class FileUtil {

	public static void loadFileNodes(String fileName, List<Node> productsList) {

		String line;
		String[] productData = null;
		String[] neighboursData = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();
				productData = line.split(":");
				neighboursData = productData[1].split(",");
				productsList.add(new Node(productData[0], neighboursData,
						new Colour("No Colour")));
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}
	
	public static void loadFileColours(String fileName, List<Colour> productsList) {

		String line;

		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();
				productsList.add(new Colour(line));
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	public static void saveToFile(String fileName, List<Node> orderList) {
		try {
			BufferedWriter file = new BufferedWriter(
					new FileWriter("files/" + fileName + ".dat"));
			String line = orderList.toString();
			file.write(line);
			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	public static String setFileName() {
		String code = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int length = 8;
		for (int i = 0; i < length; i++) {
			int numero = (int) (Math.random() * (base.length()));
			code += base.charAt(numero);
		}
		return code;
	}
}
