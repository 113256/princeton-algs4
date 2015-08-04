package ElementarySorts;


public class Insertion {

	
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
	 * insertion sort
	 *  -In iteration i, swap a[i] with each larger entry to its left.(Repeatedly so if you swapped and its still not larger than all of the left elements
	 *  then swap again)
		
		time complexity 
		O(n^2)
		
		best case- if array is already in ascending order it only makes n-1 compares and 0 exchanges
		worst case - 0.5N^2 exchanges if array is in descending order
	 */
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++){
			for (int j = i; j > 0; j--){
				if (less(a[j], a[j-1])){
					exch(a, j, j-1);}
				else break;
				
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
		Insertion selection1= new Insertion();
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
		System.out.println("\n\nafter insertion sort:\n\n");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}System.out.println();
	}
}








