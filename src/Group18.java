import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 Hopefully a slightly better sorting
 * 
 * @author elenam
 * 
 */

public class Group18 {

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
		
		sort(toSort);  // JVM warmup
		
		//System.gc();
		
		toSort = data.clone();
		
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();
		
		sort(toSort);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);

		writeOutResult(toSort, outFileName);
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

	// YOUR SORTING METHOD GOES HERE: (you may call other methods and use other classes). 
	private static void sort(String[] toSort) {
		ArrayList<DataObject> data = new ArrayList<DataObject>(toSort.length/20);
		HashMap<Integer,DataObject> seen = new HashMap<Integer,DataObject>();
		
		for (int i = 0; i < toSort.length; ++i) {
			int value = Integer.parseInt(toSort[i].substring(2));
			DataObject d = seen.get(value);
			if (d != null) {
				d.incrementCount();
			} else {
				d = new DataObject(toSort[i], value);
				seen.put(value, d);
				data.add(d);
			}
		}
		Collections.sort(data);
		
		int i = 0;
		for (DataObject d: data) {
			for (int j = 0; j < d.count; ++j) {
				String s= d.itsString;
				toSort[i++] = s;
			}
		}
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


	// the inner class has to be static because it is used in a static method
	private static class DataObject implements Comparable<DataObject> {
		private int firstFour;
		private int value;
		final public String itsString;
		private int count = 1;
		private static final int subtract = (4 * '0') % 10; 
		
		public DataObject(String s, int itsValue) {
			itsString = s;
			firstFour = (s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) - subtract) % 10;
			value = itsValue;
		}
		
		public void incrementCount() {
			++count;
		}

		@Override
		public int compareTo(DataObject other) {
			if (firstFour != other.firstFour) {
				// the opposite direction
				return (other.firstFour - firstFour);
			} 
				
			return value - other.value;

		}
		
		public String toString() {
			return "firstFour: " + firstFour + " intValue = " + value;
		}
		
	}
 
}
