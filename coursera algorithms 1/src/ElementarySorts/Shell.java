package ElementarySorts;


public class Shell {

	
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
	 * shell sort
	 *  -Move entries more than one position at a time by h-sorting the array
	 *  -h-sort by Insertion sort, with stride length h. (i.e. insertion sort but reaching back h elements at a time)
	 *  -e.g. in a 10 element array , 7-sort compares index 0 and 6, then 1 and 7 and swap if it has to
	 *  -easiest method is h=3N+1 increment sequence and we compute increments that are <n/3 e.g. if size = 364, 3n+1 = 1,4,13,40,121 
	 *  	so we start at 121-sort, then minus 1 and /3 to get 40 (or just /3 since its an int it will round down) so 40-sort then /3 13-sort ...
	 *  
	 *    
		
		time complexity
		worst case no.compares with 3x+1 = n^3/2 however NOBODY KNOWS AN ACCURATE MODEL YET 
		
		it makes ~Nlog(3)N compares if the arrays already sorted
		
	 */
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3)//compute increments that are <n/3 e.g. if size = 364, compute increments 1,4,13,40,121 
			//so if size = 364, h=121 so start with a 121-sort then /3 i.e. 40-sort etc...
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
		while (h >= 1) { // h-sort the array until the 1-sort (last)
			/****insertion sort but reach back by h-elements!****/
			for (int i = h; i < N; i++) {
			//when h=121: compare index 121(j) with index 0(j-h) then index 122 with 1, 123 with 2 so on... 300 with 179 then 179 with 58 (since 179>121)
				//its analogous to insertion sort- swap a[i] with each larger entry to its left.(Repeatedly) but jump back h-indexes instead of just 1 index
				//e.g. when you swap 300 with 179 and 179 is still larger than 58 (since we're jumping back by 121) then swap 179 and 58
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)//decrease j by h each time
					exch(a, j, j - h);   
			}
			/****end of insertion sort****/
			//move to next increment
			h = h / 3; //its (h-1)/3 but we just /3 since its an int it will round itself
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
		Shell selection1= new Shell();
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
		}
	}
}








