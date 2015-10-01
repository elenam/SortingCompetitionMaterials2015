import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class DataGenerator2015 {
	/**
	 * Competition data is generated as follows: 
	 * Data consists of 
	 * @param args:
	 *  arg[0] is the file name for the generated data 
	 * 	arg[1] is the number of elements to sort
	 *  arg[2] is the range of lengths for clusters for the Binomial distribution range
	 *  All arguments are optional. If there is only one argument, it is interpreted as the file name;
	 *  if there are only two, the second one is interpreted as the number of elements. 
	 *  If some arguments are not provided, defaults are used. If no file name is provided or if the
	 *  file name is given as "nofile", the output goes to standard output.
	 *  
	 *  Author: Elena Machkasova
	 */
	
	public static void main(String[] args) {
		int defaultClusters = 100;
		int clusters = defaultClusters;
		int defaultN = 50000;
		int n = defaultN;	
		String filename = "nofile";
		
		if (args.length >= 1) {
			filename = args[0];
		}
		
		if (args.length >= 2) {
			n = Integer.parseInt(args[1]);
		}
		
		if (args.length >= 3){
			clusters= Integer.parseInt(args[2]);
		}
		
		System.out.println(Integer.MAX_VALUE);
		
		String [] data = new String[n];
		
		// generate clusters
		for (int i = 0; i < clusters; ++i) {
			
		}
		
		// shuffle well
				
		System.out.println("Generating data: file = " + filename + " n = " + n + " number of clusters = " + clusters);
		
		System.out.println(pad(8567));
		System.out.println(pad(1));

//		// the output goes to the standard output (console)
//		if (filename.equals("nofile")) {
//			generateAndWriteOutputStandardOut(n, lambda);
//		} else { //output goes to a file 
//			try {
//				generateAndWriteOutput(new PrintWriter(filename), n, lambda);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}			
//		}
			
	}
	
    /**
     * Pad an integer with leading zeros to 9 places, if needed, and convert it into a decimal. 
     * Return a string. Example: k = 8567, to be returned: "0.000008567"
     * @param k - an integer to be padded
     * @return String representation of the padded int
     */
	private static String pad(int k) {
		String value = Integer.toString(k);
		int padLength = 9 - value.length();
		char [] zeros = new char[padLength + 2];
		zeros[0] = '0';
		zeros[1] = '.';
		for (int i = 2; i < padLength + 2; ++i) {
			zeros[i] = '0';
		}
		String padding = new String(zeros);
		return padding + value;
	}
	
	
}
