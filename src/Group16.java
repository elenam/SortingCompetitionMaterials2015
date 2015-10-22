import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group16 {

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
		ModData[] data = new ModData[toSort.length];
		for (int i = 0; i < toSort.length; i++) {
			data[i] = new ModData(toSort[i]);
		}
		Arrays.sort(data, new ModDataComparator());
		for (int i = 0; i < toSort.length; i++) {
			toSort[i] = data[i].fullString;
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
	
	public static class ModData {
		public String fullString;
		public int modValue;
		public int integerValue;

		public ModData(String input) {
			fullString = input;
			modValue = (input.charAt(2) + input.charAt(3) + input.charAt(4) + input.charAt(5) + 8) % 10;
			integerValue = (input.charAt(2) - 48) * 100000000 + (input.charAt(3) - 48) * 10000000 + (input.charAt(4) - 48) * 1000000 +
					(input.charAt(5) - 48) * 100000 + (input.charAt(6) - 48) * 10000 + (input.charAt(7) - 48) * 1000 + (input.charAt(8) - 48) * 100 +
					(input.charAt(9) - 48) * 10 + (input.charAt(10) - 48);
		}
	}
	
	public static class ModDataComparator implements Comparator<ModData> {

		@Override
		public int compare(ModData value1, ModData value2) {
			// comparator for modValue and integerValue
			int prefixDifference = value2.modValue - value1.modValue;
			if (prefixDifference != 0) {
				// negative iff value1 should precede value2
				return prefixDifference;
			}
			// negative iff value1 should precede value2
			return value1.integerValue - value2.integerValue;
		}
	}
}