import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Group8 {
	public static void main(String[] args) throws InterruptedException {	
		// Maggie and Ben
		String inputFileName = args[0];
		String outFileName = args[1];
		String[] data = readInData(inputFileName);
		String [] toSort = data.clone();
		sort(toSort);  // JVM warmup
		toSort = data.clone();
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs
		long before = System.currentTimeMillis();
		sort(toSort);
		long after = System.currentTimeMillis();
		System.out.println(after-before);

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

	public static void sort(String[] arr){		
		@SuppressWarnings("unchecked")
		List<String>[] buckets = (ArrayList<String>[])new ArrayList[10];
		int arrSize = arr.length;
		for(int k = 0; k < 10; k++){
			buckets[k] = new ArrayList<String>(arrSize/10);
		}
		String holder;
		for(int i = 0; i < arrSize; i++){		
			holder = arr[i];
			buckets[getSumFirstFourDigits(holder)].add(holder);
		}
		int pos = 0;
		ArrayList<String> holderArr;
		for(int i = 9; i > -1; i--){
			holderArr = (ArrayList<String>) buckets[i];
			Collections.sort(holderArr);
			for(int j = 0; j < holderArr.size(); j++){
				arr[pos++]=holderArr.get(j);
			}
		}
	}
	
	public static int getSumFirstFourDigits(String s) {
		return (new Integer (s.substring(2,3)) + new Integer(s.substring(3, 4)) + new Integer(s.substring(4, 5)) + new Integer(s.substring(5, 6))) %10;
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
