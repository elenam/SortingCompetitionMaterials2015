import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * 
 * @author Resa, Yuting
 * 
 */

public class Group1 {
	
	public static Random rand = new Random();

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
// ****************************************************************************************
	
	public static void sort(String[] array){
		numHolder[] temp = new numHolder[array.length];
		for(int i=0; i<array.length; i++){
			temp[i] = new numHolder(array[i]);
		}
		numHolder[] last = new numHolder[array.length];
		int[] sectionIndex = new int[10];
		countingSort(temp, last,sectionIndex);
		for (int l = 0; l<array.length; l++){
			array[l] = last[l].original;
		}
	}
	
	
	public static void countingSort(numHolder[] originalArr, numHolder[] newArr, int[] sectionIndex){
		int[] counting = new int[10];
		for (int i = 0; i < 10 ; i++) {
			counting[i] =0;
		}
		int[] countercopy = new int[10];
		for(int j =0 ; j < originalArr.length; j++) {
			counting[originalArr[j].modNum] = counting[originalArr[j].modNum] +1;
			countercopy[originalArr[j].modNum] = countercopy[originalArr[j].modNum] +1;
		}
	
		for (int k =9 ; k > 0 ;k--){
			counting[k-1] = counting[k-1] +counting[k];
		}
		for(int x = 0 ; x < originalArr.length; x++) {
			newArr[counting[originalArr[x].modNum]-1] = originalArr[x];
			counting[originalArr[x].modNum] = counting[originalArr[x].modNum] -1;		
		}

		sectionIndex[9] = 0 + countercopy[9];		
		for(int m = 8; m >= 0 ; m--) {
			sectionIndex[m] = countercopy[m] + sectionIndex[m+1];
		}		
		quickSort(newArr,sectionIndex);
	}
	
	public static void quickSort(numHolder[] newArr,int[] sectionIndex){
		if (sectionIndex[9] != 0 & sectionIndex[9] != 1){
			Medianquicksort(newArr,0,sectionIndex[9]-1);
		}
		for(int i = 8; i >= 0 ;i --) {
			int start = sectionIndex[i+1];
			int end = sectionIndex[i];
			if ((end != start) && (end - start > 1)) {
				Medianquicksort(newArr,start,end-1);
			}
		}

	}
	
	public static void Medianquicksort(numHolder[] array, int p, int r) {	
				if(p < r){	
					int len = r -p +1;
					if(len < 7) {
						for (int m = p; m<=r; m++){
							numHolder t = array[m];
							int j = m;
							for(; j>p && array[j-1].compareTo(t) > 0;j--) {
								array[j] = array[j-1];
							}
							array[j] = t;
						}
						return;
						
					}
					
					int q = Medianpartition(array, p, r);
					Medianquicksort(array,p,q-1);
					Medianquicksort(array,q+1,r);
				}

			}
	
	private static int FindMedianofThree(numHolder[] array, int a, int b, int c) {
        return array[a].compareTo(array[b])<0  ? (array[b].compareTo(array[c])<0 ? b : array[a].compareTo(array[c])<0 ? c : a)
                : array[b].compareTo(array[c]) > 0 ? b : array[a].compareTo(array[c]) > 0 ? c : a;
    }

	public static int Medianpartition(numHolder[] array, int p, int r){
		int len = r -p +1;
		int middle = p + (len>>1);
		if (len > 7) {
            int l = p;
            int n = p + len - 1;
            if (len > 40) { 
                int s = len / 8;
                l = FindMedianofThree(array, l, l + s, l + 2 * s);
                middle = FindMedianofThree(array, middle - s, middle, middle + s); 
                n = FindMedianofThree(array, n - 2 * s, n - s, n); 
            }
            middle = FindMedianofThree(array, l, middle, n); 
        }
        numHolder mi = array[middle];
        array[middle] = array[p];
        array[p] = mi;

		return partition(array,p,r);
	}

	public static int partition(numHolder[] array, int p, int r){
		numHolder x = array[p];
		int i = p;
		int j = r+1;
		while(true) {
			do{
				i++;
			}while(i<=r&&array[i].compareTo(x)<0);
				do{
					j--;
			}while(array[j].compareTo(x)>0) ;
				if(j<i) break;
				numHolder k = array[i];
				array[i] = array[j];
				array[j]=k;
		}
		
		numHolder l = array[j];
		array[j] = array[p];
		array[p] = l;
		return j;
	}
	
// **********************************************************************************
	
//	private static void sort(String[] toSort) {
//		Arrays.sort(toSort, new StringComparator());
//	}

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
	
	public static class numHolder {
		
		public String original;
		public int modNum;
		public int num;
		
		public numHolder(){
		}
		
		public numHolder(String origString){
			original = origString;
			int count = 0;
			for (int k =2; k < 6 ; k++) {
				String letter = Character.toString(origString.charAt(k));
				count = count + Integer.parseInt(letter);
			}
			modNum = count % 10;
			num = Integer.parseInt(origString.substring(2, 11));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		}
		
		public int getModNum() {
			return modNum;
		}
		
		public int getNum() {
			return num;
		}
		
		public int compareTo(numHolder input){
			return  num - input.num;
		}
	}

}