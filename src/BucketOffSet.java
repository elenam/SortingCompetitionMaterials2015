import java.util.ArrayList;

public class BucketOffSet {
	public static void sort(String[] arr)
	{
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 10; i++)
		{
			ArrayList<String> list = new ArrayList<String>();
			output.add(list);
		}

		for(int i = 0; i < arr.length; i++)
		{
			String current = arr[i];
			int sum = (current.charAt(2) + current.charAt(3) + current.charAt(4) + current.charAt(5) - 192) % 10;
			output.get(sum).add(current);
		}

		int arrIndex = 0;
		for(int i = 9; i >= 0; i--)
		{
			arrIndex = BucketL2(output.get(i), 2, arr, arrIndex);
		}
	}

	private static int BucketL2(ArrayList<String> input, int index, String[] arr, int arrIndex)
	{
		int length = input.size();
		if(length < 2 || index == 11)
		{
			for(int i = 0; i < length; i++)
			{
				arr[arrIndex++] = input.get(i);
			}
			return arrIndex;
		}
		else
		{
			ArrayList<ArrayList<String>> workSpace = new ArrayList<ArrayList<String>>();
			for(int i = 0; i < 10; i++)
			{
				ArrayList<String> list = new ArrayList<String>();
				workSpace.add(list);
			}


			for(int i = 0; i < length; i++)
			{
				String current = input.get(i);
				int sum = current.charAt(index) - 48;
				workSpace.get(sum).add(current);
			}

			index++;
			for(int i = 0; i < 10; i++)
			{
				arrIndex = BucketL2(workSpace.get(i), index, arr, arrIndex);
			}
			return arrIndex;
		}
	}
}
