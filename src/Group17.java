import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 
 * 
 */

public class Group17 {

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
	//Max Magnuson and Kirbie Dramdahl
	private static void sort(String[] toSort) {
		if(toSort.length > 4000000)
		{
			BucketWithArrays.sort(toSort);
		}
		else
		{
			BucketOffSet.sort(toSort);
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

	/**
	 * The comparator provides a comparison method for strings The strings will
	 * be sorted by the following: by length (in increasing order), within each
	 * length, by the sum of all ones (also in increasing order) within each
	 * group as determined above, alphabetically.
	 * 
	 * @author elenam
	 * 
	 */
	// the inner class has to be static because it is used in a static method
	private static class StringComparator implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {
			if ((getSumFirstFourDigits(str1) % 10) < (getSumFirstFourDigits(str2) % 10)) {
				return 1;
			} else if ((getSumFirstFourDigits(str1) % 10) > (getSumFirstFourDigits(str2) % 10)) {
				return -1;
			} else if (getAllDigits(str1) < getAllDigits(str2)) {
				return -1;
			} else if (getAllDigits(str1) > getAllDigits(str2)) {
				return 1;
			} else {
				return 0;
			}
		}

		private int getSumFirstFourDigits(String s) {
			int digit1 = new Integer(s.substring(2, 3));
			int digit2 = new Integer(s.substring(3, 4));
			int digit3 = new Integer(s.substring(4, 5));
			int digit4 = new Integer(s.substring(5, 6));
			return digit1 + digit2 + digit3 + digit4;
		}

		private int getAllDigits(String s) {
			return new Integer(s.substring(2));
		}

	}

}

