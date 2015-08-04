package ElementarySorts;

/*
 * number of compares and exchanges made by selection sort doesnt 
 * depend on the order of the input! so its always n^2 compares

 */
public class Selection {

	
	/*
	 * A total order is a binary relation ≤ that satisfies:
	 *  -Antisymmetry: if v ≤ w and w ≤ v, then v = w.
		-Transitivity: if v ≤ w and w ≤ x, then v ≤ x.
		-Totality: either v ≤ w or w ≤ v or both.
	 * 
	 * Implement compareTo() so that v.compareTo(w)
			Is a total order.
			Returns a negative integer, zero, or positive integer
			if v is less than, equal to, or greater than w, respectively.
			Throws an exception if incompatible types (or either is null).
	 * 
	 * 
	 * Implementing Comparable allows:
			-calling Collections.sort and Collections.binarySearch
			-calling Arrays.sort and Arrays.binarySearch
			-using objects as keys in a TreeMap
			-using objects as elements in a TreeSet
	 * 
	 * Java provides the Comparable interface, which contains only one method, 
	 * called compareTo. This method compares two objects, in order to impose 
	 * an order between them. 
	 * 
	 * Specifically, it returns a 
	 * 	-0 if equal
	 *  - -1 if less
	 *  - +1 if greater
	 *  - throws an exception if incompatible types.
	 */
	
	
	/*
	 * selection sort
	 *  -In iteration i, find index min of smallest remaining entry.(if youre in index i, index min is minimum of index(i+1) to the last index)
		-Swap a[i] and a[min].
		
		time complexity of O(n^2)- i.e. worst case (insensitive to input)- n^2 even if whole array is sorted!
	 */
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		for(int i = 0; i < N; i++) //iteration i
		{
			int min = i;
			for(int j = i+1; j < N; j++) //min of smallest remaining entry
			{
				if(less(a[j], a[min])){
					min = j;}
				exch(a, i, min); //swap a[i] and a[min]
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
		
	}
	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Comparable[] a)
	{
		for (int i = 1; i < a.length; i++)
		if (less(a[i], a[i-1])) return false;
		return true;
	}
	
	//to make a comparable just implement it on class definition
	//simplified version of java.util.date
	//only compares dates to other dates
	public class Date implements Comparable<Date>{
		
		private final int month, day, year;
		private final String name;
		
		public Date(int month, int day, int year, String name)
		{
			this.month = month;
			this.day = day;
			this.year = year;
			this.name= name;
		}
		
		public String getName(){
			return name;
		}
		
		public int compareTo(Date that) {
			if (this.year < that.year ) return -1;
			if (this.year > that.year ) return +1;
			if (this.month < that.month) return -1;
			if (this.month > that.month) return +1;
			if (this.day < that.day ) return -1;
			if (this.day > that.day ) return +1;
			return 0;
		}
		
	}
	
	
	
	public static void main(String[] args)
	{
		Selection selection1= new Selection();
		Date date3 = selection1.new Date(7,15,1995, "15july1995");
		Date date1 = selection1.new Date(8,15,1995, "15aug1995");
		Date date2 = selection1.new Date(9,15,1996, "15sep1996");
		Date date5 = selection1.new Date(10,15,1995, "15oct1995");
		Date date4 = selection1.new Date(10,15,1997, "15oct1997");
		
		Date[] a = {date1, date2, date3, date4, date5};
		
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}
		
		selection1.sort(a);
		System.out.println("\n\nafter selection sort:\n\n");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}
	}
}








