import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class DataGenerator2015 {
	/**
	 * Competition data is generated as follows: Data consists of
	 * 
	 * @param args
	 *            : arg[0] is the file name for the generated data arg[1] is the
	 *            number of elements to sort arg[2] is the range of lengths for
	 *            clusters for the Binomial distribution range arg[3] is the
	 *            seed for the random number generator All arguments are
	 *            optional. If there is only one argument, it is interpreted as
	 *            the file name; if there are only two, the second one is
	 *            interpreted as the number of elements. If some arguments are
	 *            not provided, defaults are used. If no file name is provided
	 *            or if the file name is given as "nofile", the output goes to
	 *            standard output.
	 * 
	 *            Author: Elena Machkasova
	 */

	public static Random rand;
	public static final int dataSize = 1000000000;

	public static void main(String[] args) {
		int defaultClusters = 99;
		int clusters = defaultClusters;
		int defaultN = 1000000;
		int n = defaultN;
		String filename = "nofile";

		if (args.length >= 1) {
			filename = args[0];
		}

		if (args.length >= 2) {
			n = Integer.parseInt(args[1]);
		}

		if (args.length >= 3) {
			clusters = Integer.parseInt(args[2]);
		}

		// initializing random number generator
		if (args.length >= 4) {
			rand = new Random(Integer.parseInt(args[3]));
		} else {
			rand = new Random();
		}

		String[] data = new String[n];

		// generateCluster(data, 0, 100, 40, 0.5);

		// if the division is uneven, we make the clusters slightly bigger. Will
		// get a very few elements overwritten, but that's ok
		int clusterSize = (int) Math.ceil(((double) n) / clusters);

		// generate larger clusters
		for (int i = 0; i < 2 * clusters / 3; ++i) {
			generateCluster(data, i * clusterSize, clusterSize, n / 10);
		}

		// generate smaller clusters
		for (int i = 0; i < clusters / 3 - 1; ++i) {
			generateCluster(data, (i + 2 * clusters / 3) * clusterSize,
					clusterSize, n / 1000);
		}

		// the last cluster cannot go over
		generateCluster(data, (clusters - 1) * clusterSize, n - (clusters - 1)
				* clusterSize, n / 1000);

		// shuffle well
		for (int i = 0; i < 2 * n; ++i) {
			int i1 = rand.nextInt(n);
			int i2 = rand.nextInt(n);
			String temp = data[i1];
			data[i1] = data[i2];
			data[i2] = temp;
		}

		System.out.println("Generating data: file = " + filename + " n = " + n
				+ " number of clusters = " + clusters);

		System.out.println(pad(8567));
		System.out.println(pad(1));

		// the output goes to the standard output (console)
		if (filename.equals("nofile")) {
			writeOutputStandardOut(data);
		} else { // output goes to a file
			try {
				writeOutputFile(new PrintWriter(filename), data);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Pad an integer with leading zeros to 9 places, if needed, and convert it
	 * into a decimal. Return a string. Example: k = 8567, to be returned:
	 * "0.000008567"
	 * 
	 * @param k
	 *            an integer to be padded
	 * @return String representation of the padded int
	 */
	private static String pad(int k) {
		String value = Integer.toString(k);
		int padLength = 9 - value.length();
		char[] zeros = new char[padLength + 2];
		zeros[0] = '0';
		zeros[1] = '.';
		for (int i = 2; i < padLength + 2; ++i) {
			zeros[i] = '0';
		}
		String padding = new String(zeros);
		return padding + value;
	}

	/**
	 * Generates a cluster of data of n elements according to Binomial
	 * distribution of a length generated within the given maxRange adds them
	 * starting at the given position in the given array as padded strings
	 * representing decimals
	 * 
	 * @param data
	 *            the array where the generated elements go
	 * @param start
	 *            the starting position for this cluster
	 * @param n
	 *            the number of elements to be generated
	 * @param maxRange
	 *            maximum range of a cluster
	 */
	private static void generateCluster(String[] data, int start, int n,
			int maxRange) {
		// two random points in the array
		int range = 20 + rand.nextInt(maxRange - 20); // guarantees that the
														// smallest cluster has
														// at least 20 elements

		// the array to be used as offsets for the given range
		int[] countRolls = new int[range + 1];

		double p = rand.nextDouble();

		// count how many times each value occurred. The counts follow binomial
		// distribution.
		for (int i = 0; i < n; ++i) {
			int r = generateBinomial(range, p);
			countRolls[r]++;
		}

		// the starting point of the cluster in the data range
		int clusterLowest = rand.nextInt(dataSize);

		// the entire cluster must be within the data range
		while (clusterLowest + range >= dataSize) {
			clusterLowest = rand.nextInt(dataSize);
		}

		// fill in the corresponding part of the array with values,
		// as padded strings
		int k = start;
		for (int i = 0; i <= range; ++i) { // for every value of the random
											// variable...
			// System.out.println(countRolls[i]);
			for (int j = 0; j < countRolls[i]; ++j) { // ...add that many copies
														// of the element
				data[k] = pad(clusterLowest + i);
				k++;
			}
		}
	}

	// http://stackoverflow.com/questions/1241555/algorithm-to-generate-poisson-and-binomial-random-numbers
	private static int generateBinomial(int n, double p) {
		int x = 0;
		for (int i = 0; i < n; i++) {
			if (Math.random() < p)
				x++;
		}
		return x;
	}

	private static void writeOutputStandardOut(String[] data) {
		for (int i = 0; i < data.length; ++i) {
			System.out.println(data[i]);
		}
	}

	private static void writeOutputFile(PrintWriter out, String[] data) {
		for (int i = 0; i < data.length; ++i) {
			out.println(data[i]);
		}
		out.close();
	}
}
