import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Reed
 * 
 */

public class Group14 {

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
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
		/*//Code to quickly determine correctness of algorithm
		try {
			File file1 = new File(outFileName);
			File file2 = new File("E:/Users/Reed/workspace/SortingCompetition/src/out3.txt");
			//boolean result = (sameContent(file1.toPath(),file2.toPath()));
			boolean result = Arrays.equals(Files.readAllBytes(file1.toPath()), Files.readAllBytes(file2.toPath()));
			System.out.println("Sorting correct: "+result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		quicksortX(toSort,0,toSort.length-1,0);
	}

	private static void quicksortX(String[] a, int lo, int hi, int d){
		if (hi - lo <= 0 || d>9) return;
		int i = lo-1, j = hi;
		int p = lo-1, q = hi;
		int v = getDigit(a[hi],d);
		String tmp;
		while (i < j) {
			while (getDigit(a[++i],d) < v) if (i == hi) break;
			while (v < getDigit(a[--j],d)) if (j == lo) break;
			if (i > j) break;
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
			if (getDigit(a[i],d) == v) {
				tmp = a[++p];
				a[p] = a[i];
				a[i] = tmp;
			}
			if (getDigit(a[j],d) == v) {
				tmp = a[j];
				a[j] = a[--q];
				a[q] = tmp;
			}
		}
		if (p == q) {
			quicksortX(a, lo, hi, d+1);
			return;
		}
		if (getDigit(a[i],d) < v) i++;
		for (int k = lo; k <= p; k++) {
			tmp = a[k];
			a[k] = a[j--];
			a[j+1] = tmp;
		}
		for (int k = hi; k >= q; k--) {
			tmp = a[k];
			a[k] = a[i++];
			a[i-1] = tmp;
		}
		quicksortX(a, lo, j, d);
		if ((i == hi) && (getDigit(a[i],d) == v)) i++;
		quicksortX(a, j+1, i-1, d+1);
		quicksortX(a, i, hi, d);
	}

	private static int getDigit(String s,int radix){
		if(radix==0) return 9 - ((s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5)-2) % 10);
		else return s.charAt(radix+1)-48;
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