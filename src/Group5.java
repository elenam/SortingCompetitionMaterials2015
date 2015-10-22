import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

// Mark, Matthew 

public class Group5 {

	public static void main(String[] args) throws InterruptedException{
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


		return input.toArray(new String[0]); // convert to array of strings
	}
	
	
	private static void sort(String[] toSort) {
		Arrays.sort(toSort, new StringComparator());
		countSortModified(toSort);
	}

	
	private static void countSortModified(String[] arr){
		int n = arr.length;
		int sumOfN;
		int[] modCount = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6};
		int[] itemKeys = new int[n];
		
		int[] count = new int[10];
		String[] output = new String[n];
		for(int i = 0; i < n; i++){
			sumOfN = getSumFirstFourDigits(arr[i]);
			count[modCount[sumOfN]] += 1;
			itemKeys[i] = modCount[sumOfN];
		}
		int total= 0;
		int oldCount;
		for(int x = 9; x >= 0; x--){
			oldCount = count[x];
			count[x] = total;
			total += oldCount;
		}
		
		for(int y = 0; y < n; y++){
			int key = itemKeys[y];
			output[count[key]] = arr[y];
			count[key] += 1;
		}
		
		for(int m = 0; m < n; m++){
			arr[m] = output[m];
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
	
	
	private static int getSumFirstFourDigits(String s) {
		return ((s.charAt(2)-48) + (s.charAt(3)-48) + (s.charAt(4)-48) + (s.charAt(5)-48));
		
	}
	
	
	private static class StringComparator implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {
			int compVal;
			for(int i = 2; i<11; i++){
				compVal = str1.charAt(i) - str2.charAt(i);
				if(compVal != 0) {
					return compVal;
				} 
			}
			return 0;
		}
	}
}








