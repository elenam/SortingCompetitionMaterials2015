import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Group12 {
	static int n;
	//Thomas Hagen and Peter Hanson

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) {
			System.out
			.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];

		String[] data = readInData(inputFileName);

		String [] toSort = data.clone();

		sort(toSort);  // JVM warmup  /////////////////////////////////////////////////////////////////////////////////////////////////////

		toSort = data.clone();

		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();

		sort(toSort);

		long end = System.currentTimeMillis();

		System.out.println(end - start);

		writeOutResult(toSort, outFileName);




	}


	public static String[] sort(String[] arrayIn){
		//Arraylist of arraylists
		ArrayList<ArrayList<String>> arrayMove = new ArrayList<ArrayList<String>>(10);

		//Putting arraylists into arraylist
		for(int i=0;i<10;i++) {
			ArrayList<String> innerArray = new ArrayList<String>(arrayIn.length / 7);
			arrayMove.add(innerArray);
		}

		//Putting strings into appropriate inner arraylists
		for(int i=0; i<arrayIn.length; i++) {
			arrayMove.get(sumFirstFour(arrayIn[i])).add(arrayIn[i]);
		}

		int counter = 0;
		for(int i=9;i>=0;i--){
			Collections.sort(arrayMove.get(i));
			arrayMove.set(i, arrayMove.get(i));
			for(int j=0;j < arrayMove.get(i).size();j++) {
				arrayIn[counter] = arrayMove.get(i).get(j);
				counter++;
			}
		}
		return arrayIn;
	}

	public static int sumFirstFour(String str) {
		return (((int)str.charAt(2) + (int)str.charAt(3) + (int)str.charAt(4) + (int)str.charAt(5)) - 192) % 10;
	}



	private static String[] readInData(String inputFileName) {
		ArrayList<String> input = new ArrayList<String>();
		Scanner in;
		try {
			in = new Scanner(new File(inputFileName));
			while (in.hasNext()) {
				input.add(in.next());
			}
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// System.out.println(input);
		return input.toArray(new String[0]); // convert to array of strings
	}


	private static void writeOutResult(String[] sorted, String outputFilename) {
		try {
			PrintWriter out = new PrintWriter(outputFilename);
			for (String str : sorted) {
				out.println(str);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}