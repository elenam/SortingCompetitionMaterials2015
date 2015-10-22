import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;

/*
 * Isaac K
 * Mitch F
 * 10/1/15
 */

public class Group3 {

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
	
	public static void sort(String[] data) {
		ArrayList<String>[] moded = bucketSort(data);
		int index = 0;
		for(int i = moded.length - 1; i >= 0; i--) {
			if(!moded[i].isEmpty()) {
				for(int j = 0; j < moded[i].size(); j++) {
					data[index] = moded[i].get(j);
					index++;
				}
				moded[i].clear();
			}
		}
	}
	
	public static int sumFour(String nums){
		int digit1 = Integer.parseInt(nums.substring(2,3));
		int digit2 = Integer.parseInt(nums.substring(3,4));
		int digit3 = Integer.parseInt(nums.substring(4,5));
		int digit4 = Integer.parseInt(nums.substring(5,6));
		return (digit1 + digit2 + digit3 + digit4) % 10;
	}
	
	//makes thing very slow
	public static Integer toInt(String nums) {
		Integer number = new Integer(nums.substring(2, nums.length()));
		return number;
	}
	
	public static void insertionSort(ArrayList<String> arr) {
		for(int i = 1; i < arr.size(); i++) {
			String key = arr.get(i);
			int j = i;
			while(j > 0 && arr.get(j - 1).compareTo(key) > 0) {
				arr.set(j, arr.get(j - 1));
				j -= 1;
			}
			arr.set(j, key);
		}
	}
	
	public static void quickSort(ArrayList<String> arr, int left, int right) {
	      int index = partition(arr, left, right);
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);
	      if (index < right)
	            quickSort(arr, index, right);
	}
	
	public static int partition(ArrayList<String> arr, int left, int right) {
	    int i = left, j = right;
	    //String pivot = arr.get((left + right) / 2);
	    Random rand = new Random();
		String pivot;
		if(right - left > 4){
			String value1 = arr.get(rand.nextInt(right - left) + left);
			String value2 = arr.get(rand.nextInt(right - left) + left);
			String value3 = arr.get(rand.nextInt(right - left) + left);
			String median = calcMedian(value1, value2, value3);
			pivot = median;
		} else {
			pivot = arr.get((left + right) / 2);
			
		}
	    while (i <= j) {
	    	while (arr.get(i).compareTo(pivot) < 0){
	    		i++;
	        }
	        while (arr.get(j).compareTo(pivot) > 0){
	        	j--;
	        }
	        if (i <= j) {
	        	swap(arr, i, j);
	        	i++;
	        	j--;
	        }
	    };
	     
	    return i;
	}
	
	public static String calcMedian(String value1, String value2, String value3){
		if(value1.compareTo(value2) >= 0){
			if(value1.compareTo(value3) <= 0){
				return value1;
			}else if(value2.compareTo(value3) >= 0){
				return value2;
			} else {
				return value3;
			}
		} else if(value2.compareTo(value3) <= 0){
			return value2;
		} else if(value3.compareTo(value1) >=0){
			return value3;
		} else {
			return value1;
		}
	}
	
	public static void swap(ArrayList<String> arr, int initialPosn, int finalPosn) {
		String temp = arr.get(initialPosn);
        arr.set(initialPosn, arr.get(finalPosn));
        arr.set(finalPosn, temp);
	}
	
	public static ArrayList<String>[] bucketSort(String[] arr) {
		ArrayList<String>[] buckets = new ArrayList[10];
		for(int i = 0; i < 10; i++) {
			buckets[i] = new ArrayList<String>();
		}

		for(int j = 0; j < arr.length; j++){
			buckets[sumFour(arr[j])].add(arr[j]);
		}
		sortBuckets(buckets);
		return buckets;
	}
	
	public static void sortBuckets(ArrayList<String>[] buckets){
		for(int i = 0; i < buckets.length; i++){
			quickSort(buckets[i], 0, buckets[i].size() - 1);
			//switch to insertion sort: might implement in the future
		}
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
	
	private static void writeOutResult(String[] sorted, String outputFilename) {
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
	
	public static void printArray (Comparable[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void printArrList (ArrayList<String> arr) {
		for(int i =0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

}