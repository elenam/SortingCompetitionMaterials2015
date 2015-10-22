import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Big implements Comparator<HashMap<String, Object>> {
	
	private String key;
	
	public Big(String key){
		this.key = key;
	}

	@Override
	public int compare(HashMap<String, Object> first, HashMap<String, Object> second) {
		return ((Long) first.get(key)).compareTo((Long) second.get(key));
		
	}


	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0, Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T> Comparator<T> nullsFirst(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<HashMap<String, Object>> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<HashMap<String, Object>> thenComparing(
			Comparator<? super HashMap<String, Object>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<HashMap<String, Object>> thenComparing(
			Function<? super HashMap<String, Object>, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<HashMap<String, Object>> thenComparing(
			Function<? super HashMap<String, Object>, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<HashMap<String, Object>> thenComparingDouble(
			ToDoubleFunction<? super HashMap<String, Object>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<HashMap<String, Object>> thenComparingInt(
			ToIntFunction<? super HashMap<String, Object>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<HashMap<String, Object>> thenComparingLong(
			ToLongFunction<? super HashMap<String, Object>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
