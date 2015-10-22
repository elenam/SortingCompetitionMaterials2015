import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Pretty much just runs the built-in sort.
 * 
 * FINAL VERSION
 * 
 * @author Emma Sax
 * @author Dan Stelljes
 */

public class Group6 {
	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}
		
		String inputFileName = args[0];
		String outFileName = args[1];
        
		String[] data = readInData(inputFileName);
        
		String[] toSort = (String[])data.clone();
		sort(toSort);
        
		toSort = (String[])data.clone();		
		Thread.sleep(10);
        
		long start = System.currentTimeMillis();
		sort(toSort);
		long end = System.currentTimeMillis();
        
		System.out.println(end - start);
		writeOutResult(toSort, outFileName);
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Sorts the data.
	 * 
	 * @param originalData
	 *   The data to be sorted.
	 */
	private static void sort(String[] originalData) {
		Group6.sortByLookup(originalData);
	}
	
	/**
	 * Sorts long representations of the data using a map to avoid string
	 * operations.
	 * 
	 * @param originalData
	 *    The data to be sorted.
	 */
	private static void sortByLookup(String[] originalData) {
		long[] dataAsLongs = new long[originalData.length];
		HashMap<Long, String> references = new HashMap<Long, String>(originalData.length);
		int counter = 0;
		long longRepresentation;
		long cacheKey = -1;
		
		// creates a long representation of the original string, then adds a
		// digit to the front according to the mod of the original string so we
		// can sort naturally
		for (String original : originalData) {			
			longRepresentation =
					  (original.charAt(10) - '0') * 1 +
					  (original.charAt(9)  - '0') * 10 +
					  (original.charAt(8)  - '0') * 100 +
					  (original.charAt(7)  - '0') * 1000 +
					  (original.charAt(6)  - '0') * 10000 +
					  (original.charAt(5)  - '0') * 100000 +
					  (original.charAt(4)  - '0') * 1000000 +
					  (original.charAt(3)  - '0') * 10000000 +
					  (original.charAt(2)  - '0') * 100000000 +
					  (9 - (((original.charAt(2) - '0') +
							 (original.charAt(3) - '0') +
							 (original.charAt(4) - '0') +
							 (original.charAt(5) - '0')) % 10))
					                              * 1000000000L;
			
			dataAsLongs[counter] = longRepresentation;
			references.put(longRepresentation, original);
			counter++;
		}
		
		Arrays.sort(dataAsLongs);
		
		// inserts sorted data into the original array, caching the previous
		// result to improve performance on duplicates
		for (counter = 0; counter < originalData.length; counter++) {
			if (cacheKey == dataAsLongs[counter]) {
				originalData[counter] = originalData[counter - 1];
			} else {
				cacheKey = dataAsLongs[counter];
				originalData[counter] = references.get(cacheKey);
			}
		}
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
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
