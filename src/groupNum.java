import java.lang.reflect.Field;
import java.util.*;
public class groupNum {
	//reverse mod takes care of the backwards ordering while computing the mod.
	//since we only care about the mods of the first less than 40 numbers, it is
	//faster to just cover all the cases. 
	private final static int[] reverseMod = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 9, 8, 7, 6, 5, 4, 3,
			2, 1, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public static void sort(String[] data) {
		//these are our cached values. we only need to cache one
		//since the array is sorted so duplicates are next to eachother.
		String prevString = "0.000000000";
		long prevNum = 10000000000L;
		
		long[] tosort = new long[data.length];
			//populates tosort with the long representations of the values  of data. 
			for (int i = 0; i < data.length; i++) {
				tosort[i] = parseLong(data[i]);
			}
			//timsort is fast.
		Arrays.sort(tosort);
		
		if (data.length < 1500002) {
			//this works about 30 ms better on the oneM set.
			for (int i = 0; i < data.length; i++) {
				if (!(prevNum == tosort[i])) {
					//this has the downfall of generating a second new string
					//each time it runs because of the immutability of strings
					prevString = "0." + Long.toString(tosort[i]).substring(2); 
					data[i] = prevString;
					prevNum = tosort[i];
				} else {
					//use the cached value when duplicates
					data[i] = prevString;
				}
			}
		} else {
		//this one has very consistent good times(about 10~20 ms better than java)
		//on greater than twoM sets. They are similar on twoM, but java has a greater
		//variance. 
			
		//For the following reflections my main reference is as cited below:
		//http://pastebin.com/vbstfWX1
		//answered Nov 3 '14 at 19:43	szgal
		//http://stackoverflow.com/questions/6932772/effect-of-changing-string-using-reflection
		//this will be used to dive into the internal char array of our strings. 
		Field f = null;
		//we don't want to get NoSuchFieldException now, do we? 
		//let's just ignore it. 
		try {
			//makes our field be the immutable internal char array of a string
			f = String.class.getDeclaredField("value");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		//allows access to immutable char array in a string
		f.setAccessible(true);
		//value will be a pointer to the internal char[]
		char[] value;
			//populates the array we were to sort with the correct values
			for (int i = 0; i < data.length; i++) {
				//uses caching to make duplicate values fast
				if (prevNum == tosort[i]){
					//use the cached value if duplicates
				data[i] = prevString;
				}else{
				//java does Long.toString faster than I can. The problem became not
				//having the correct output format. 
				data[i]=Long.toString(tosort[i]);
				//silences exceptions
				try {
					//since all the char[] we are interested in printing have the same
					//two beginning char this reflection doesnt break anything. When I tried
					//to reflect this way on the whole array bad things happened. I am pretty
					//sure if the first two chars were not the same for every output this would
					//explode. 
					value = (char[])f.get(data[i]);
					value[0]='0';
					value[1]='.';
					//((char[])f.get(data[i]))[0]='0';
					//((char[])f.get(data[i]))[1]='.';

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				//caches the output string it's long value for the next iteration. 
				prevString = data[i];
				prevNum = tosort[i];
				//this commented out way was very fast, but it had the slight drawback of 
				//making the entire array into the same value. oh well!! 
				//very fast is <300 ms on fiveM. It actually did all the correct stuff. But
				//reflections. :(
				/*data[i] = new String(data[i]);
				editIntStr(data[i],countLongToString(tosort[i]));
				prevNum = tosort[i];
				prevString = data[i];*/
				}
			}
		}
	}
	//we adapted it from an example on this page:
	//http://nadeausoftware.com/articles/2009/08/java_tip_how_parse_integers_quickly
	//only works on strings of length 11 who's chars have meaning in base 10.
	//for clearer documentation the link above has a more general, but slower version of this.
	//I will sum up his key points:
	//This is faster than java's because it skips all the digit overflow and underflow
	//checking. This works better for negative numbers, so we keep the number negative 
	//until the end. 
	public static long parseLong(final String s) {
		long num = 0;
		//reverseMod takes care of the sorting backwards mod 10 by simply adding
		//a digit to the front so that any things that sorts longs can be easily
		//adapted to work on our data. We just need to remember to chop it off in the end.
		num = (reverseMod[((s.charAt(2) - '0') + (s.charAt(3) - '0') + (s.charAt(4) - '0') + (s.charAt(5) - '0'))] + 10)
				* -1;
		//here is his original loop for this part:
			//int i = 1;
	    	//while ( i < len )
	    	//    num = num*10 + '0' - s.charAt( i++ );
		//we hardcoded the loop to avoid the looping overhead.
		num = num * 10 + '0' - s.charAt(2);
		num = num * 10 + '0' - s.charAt(3);
		num = num * 10 + '0' - s.charAt(4);
		num = num * 10 + '0' - s.charAt(5);
		num = num * 10 + '0' - s.charAt(6);
		num = num * 10 + '0' - s.charAt(7);
		num = num * 10 + '0' - s.charAt(8);
		num = num * 10 + '0' - s.charAt(9);
		num = num * 10 + '0' - s.charAt(10);
		//the + 10000000000L is done so that all numbers we process have 
		//the exact same amount of digits. 
		return num * -1 + 10000000000L;
	}
}
