public class BucketWithArrays {

	public static void sort(String[] arr)
	{
		String[][] output = new String[10][arr.length];
		int[] indexes = new int[10];

		for(int i = 0; i < arr.length; i++)
		{
			String current = arr[i];
			int sum = (current.charAt(2) + current.charAt(3) + current.charAt(4) + current.charAt(5) - 192) % 10;
			output[sum][indexes[sum]++] = current;
		}

		int arrIndex = 0;
		for(int i = 9; i >= 0; i--)
		{
			arrIndex = BucketL2(output, i, indexes[i], 2, arr, arrIndex);
		}
	}

	private static int BucketL2(String[][] input, int inputIndex, int bound, int strIndex, String[] arr, int arrIndex)
	{
		if(bound < 2 || strIndex == 11)
		{
			for(int i = 0; i < bound; i++)
			{
				arr[arrIndex++] = input[inputIndex][i];
			}
			return arrIndex;
		}
		else
		{
			String[][] workSpace = new String[10][bound];
			int[] indexes = new int[10];

			for(int i = 0; i < bound; i++)
			{
				String current = input[inputIndex][i];
				int sum = current.charAt(strIndex) - 48;
				workSpace[sum][indexes[sum]++] = current;
			}

			strIndex++;
			for(int i = 0; i < 10; i++)
			{
				arrIndex = BucketL2(workSpace, i, indexes[i], strIndex, arr, arrIndex);
			}
			return arrIndex;
		}
	}
}
