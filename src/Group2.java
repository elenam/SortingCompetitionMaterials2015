import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Preston Miller, Myles Gavic
 *
 */
public class Group2 {

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




	private static void writeOutResult(String [] sorted, String outputFilename) {
		try {
			PrintWriter out = new PrintWriter(outputFilename);
			for (String str: sorted) {
				out.println(str);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void sort(String[] items){
		ArrayList<String>[] groups = (ArrayList<String>[])new ArrayList[10];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new ArrayList<String>(items.length/9);
		}
		//First pass:  takes the sum of the first 4 digits and mod's them by 10.
		for (int i = 0; i < items.length; i++) {
			String nums = items[i].substring(2, 6);
			int result = 0;
			for (int j = 0; j < nums.length(); j++) {
				result += Integer.parseInt(""+nums.charAt(j));
			}
			groups[result%10].add(items[i]);
		}

		for (int i = 0; i < groups.length; i++) {
			groups[i].sort(null);
		}

		int index = 0;
		for (int i = groups.length-1; -1 < i; i--) {
			for (String item : groups[i]) {
				items[index++] = item;
			}
		}

	}


}