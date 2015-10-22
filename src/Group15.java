import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Jack Ziegler
 * 
 */

public class Group15 {

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
	private static void sort(String[] toSort) {
		

		// Create index array for "mapping". This way I never have to reconstruct Strings.
		int[] index = new int[toSort.length];
		

		for (int i = 0; i < index.length; i++) {
			index[i] = i;
		}
		
		
		
		int[] sumFourMod = new int[toSort.length];
		int[] countModValues = new int[10];
		
		int[] mod10Array = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,};
		
		for (int i = 0; i < sumFourMod.length; i++) {;
			int modValue = mod10Array[toSort[i].charAt(2) + toSort[i].charAt(3) + toSort[i].charAt(4) + toSort[i].charAt(5) - 192];
			countModValues[modValue] += 1;
			sumFourMod[i] = modValue;
		}
		
		
		
		 
		for (int i = 0; i < 9; i++) {
			int sumAbove = 0;
			for (int j = i + 1; j < 10; j++) {
				sumAbove += countModValues[j];
			}
			countModValues[i] = sumAbove;
		}
		countModValues[9] = 0;
		int[] cloneCountModValues = countModValues.clone();
		
		
		
		
		int[] oldIndex = index.clone();

		for (int i = 0; i < sumFourMod.length; i++) {
			int currentValue = sumFourMod[i];
			index[countModValues[currentValue]] = oldIndex[i];
			countModValues[currentValue]++;
		}
		
		

		int[] intOfStrings = new int[toSort.length];

		for (int i = 0; i < intOfStrings.length; i++) {
			intOfStrings[i] = getIntValue(toSort[index[i]]);
		}
		
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[9], cloneCountModValues[8] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[8], cloneCountModValues[7] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[7], cloneCountModValues[6] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[6], cloneCountModValues[5] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[5], cloneCountModValues[4] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[4], cloneCountModValues[3] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[3], cloneCountModValues[2] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[2], cloneCountModValues[1] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[1], cloneCountModValues[0] - 1);
		dPivotQuickSort(intOfStrings, index, cloneCountModValues[0], toSort.length - 1);

		
		// Final map:
		String[] oldToSort = toSort.clone();
		for (int i = 0; i < toSort.length; i++) {
			toSort[i] = oldToSort[index[i]];
		}

	} // Close sort()
	
	private static void dPivotQuickSort(int[] itemArray, int[] indexArray, int start, int end) {
		if (end > start) {
			if (itemArray[start] > itemArray[end]) {
				swap(itemArray, start, end);
				swap(indexArray, start, end);
			}
			int p = itemArray[start];
			int q = itemArray[end];

			int l = start + 1, g = end - 1, k = l;
			while (k <= g) {
				if (itemArray[k] < p) {
					swap(itemArray, k, l);
					swap(indexArray, k, l);
					++l;
				} else if (itemArray[k] >= q) {
					while (itemArray[g] > q && k < g) --g;
					swap(itemArray, k, g);
					swap(indexArray, k, g);
					--g;
					if (itemArray[k] < p) {
						swap(itemArray, k, l);
						swap(indexArray, k, l);
						++l;
					}
				}
				++k;
			}
			--l; ++g;
			
			swap(itemArray, start, l);
			swap(itemArray, end, g);
			swap(indexArray, start, l); 
			swap(indexArray, end, g);

			dPivotQuickSort(itemArray, indexArray, start, l - 1);
			dPivotQuickSort(itemArray, indexArray, l + 1, g - 1);
			dPivotQuickSort(itemArray, indexArray, g + 1, end);
		}
	}

	private static void swap(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static int getIntValue(String s) {
		int result = 0;
		
		result += s.charAt(2) - '0';
		result *= 10;
		result += s.charAt(3) - '0';
		result *= 10;
		result += s.charAt(4) - '0';
		result *= 10;
		result += s.charAt(5) - '0';
		result *= 10;
		result += s.charAt(6) - '0';
		result *= 10;
		result += s.charAt(7) - '0';
		result *= 10;
		result += s.charAt(8) - '0';
		result *= 10;
		result += s.charAt(9) - '0';
		result *= 10;
		result += s.charAt(10) - '0';
		
		return result;
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