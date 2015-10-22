import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author Otto, Zach
 * 
 */

public class Group11 {

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
	public static void sort(String[] arr){
		ArrayList<String> sortedList = new ArrayList<String>();
		
		ArrayList<String> mod1 = new ArrayList<String>();
		ArrayList<String> mod2 = new ArrayList<String>();
		ArrayList<String> mod3 = new ArrayList<String>();
		ArrayList<String> mod4 = new ArrayList<String>();
		ArrayList<String> mod5 = new ArrayList<String>();
		ArrayList<String> mod6 = new ArrayList<String>();
		ArrayList<String> mod7 = new ArrayList<String>();
		ArrayList<String> mod8 = new ArrayList<String>();
		ArrayList<String> mod9 = new ArrayList<String>();
		ArrayList<String> mod0 = new ArrayList<String>();
		
		
		for (int i = 0; i<arr.length; i++){
			int holder = sumOfFirstFourDigitsModTen(arr[i]);
	
				if(holder == 1){
					mod1.add((arr[i]));
					}
				if(holder == 2){
					mod2.add((arr[i]));
					}
				if(holder == 3){
					mod3.add((arr[i]));
					}
				if(holder == 4){
					mod4.add((arr[i]));
					}
				if(holder == 5){
					mod5.add((arr[i]));
					}
				if(holder == 6){
					mod6.add((arr[i]));
					}
				if(holder == 7){
					mod7.add((arr[i]));
					}
				if(holder == 8){
					mod8.add((arr[i]));
					}
				if(holder == 9){
					mod9.add((arr[i]));
					}
				if(holder== 0){
					mod0.add((arr[i]));
					}
			}
			Collections.sort(mod1);
			Collections.sort(mod2);
			Collections.sort(mod3);
			Collections.sort(mod4);
			Collections.sort(mod5);
			Collections.sort(mod6);
			Collections.sort(mod7);
			Collections.sort(mod8);
			Collections.sort(mod9);
			Collections.sort(mod0);
			
			sortedList.addAll(mod9);
			sortedList.addAll(mod8);
			sortedList.addAll(mod7);
			sortedList.addAll(mod6);
			sortedList.addAll(mod5);
			sortedList.addAll(mod4);
			sortedList.addAll(mod3);
			sortedList.addAll(mod2);
			sortedList.addAll(mod1);
			sortedList.addAll(mod0);
			
			for(int i = 0; i < sortedList.size(); i++){
				arr[i] = sortedList.get(i);
			}
			
			
		}
	
	public static int sumOfFirstFourDigitsModTen(String str){
		int sum = 0;
		for (int i = 2; i < 6; i++){
			sum += Character.getNumericValue(str.charAt(i));
		}
		sum = sum%10;
		return sum;
	}

}