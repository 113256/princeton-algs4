package ComparatorAndStability;

import java.util.Arrays;
import java.util.Comparator;





public class Date implements Comparable<Date>{
	/*
	 implement comparator (not comparable) INTERFACE
	- Define a (nested) class that implements the Comparator interface.
	-Implement the compare() method.
	 */
	
	//various comparators
	public static final Comparator<Date> BY_DAY = new ByDay();
	public static final Comparator<Date> BY_MONTH = new ByMonth();
	
	
	private final int month, day, year;
	private final String name;
	
	public Date(int month, int day, int year, String name)
	{
		this.month = month;
		this.day = day;
		this.year = year;
		this.name= name;
	}
	
	
	private static class ByDay implements Comparator<Date>
	{
		public int compare(Date v, Date w)
		{ 
			return (v).compareToDay(w); //ALL THE "COMPARE" METHODS have to return -1, 0, or 1
		}
	}
	
	private static class ByMonth implements Comparator<Date>
	{
		public int compare(Date v, Date w)
		{
			return v.compareToMonth(w); //ALL THE "COMPARE" METHODS have to return -1, 0, or 1
		}
	}
	
	public String getName(){
		return name;
	}
	public int compareToDay(Date that) {
		if (this.day < that.day ) return -1;
		if (this.day > that.day ) return +1;
		if (this.year < that.year ) return -1;
		if (this.year > that.year ) return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		
		
		return 0;
	}

	public int compareToMonth(Date that) {
		
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.year < that.year ) return -1;
		if (this.year > that.year ) return +1;
		if (this.day < that.day ) return -1;
		if (this.day > that.day ) return +1;
		return 0;
	}
	//default compare to that arrays.sort(a) (no 2nd argument) uses
	//compareTo method required for arrays.sort or else it wont know how to sort a custom class
	public int compareTo(Date that) {
		if (this.year < that.year ) return -1;
		if (this.year > that.year ) return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.day < that.day ) return -1;
		if (this.day > that.day ) return +1;
		return 0;
	}
	
	/*
	 * a stable sort preserves the relative order of items with equal keys
	 * e,g if you sort by name then by age, students wit the same age would still be sorted by name, algorithms that do this are called STABLE
	 * 
	 * STABLE SORTS- Equal items never move past each other.
	 * -insertion, mergesort
	 * -e.g. if you sort by name then insertion sort (by age), students with same age are still sorted by name
	 * 
	 * NOT STABLE - Long-distance exchange might move an item past some equal item.
	 * -selection, shell, quicksort
	 */
	public static void main(String[] args)
	{
		Date date3 = new Date(7,19,1995, "19july1995");
		Date date1 = new Date(8,18,1995, "18aug1995");
		Date date2 = new Date(9,17,1996, "17sep1996");
		Date date5 = new Date(10,16,1995, "16oct1995");
		Date date4 = new Date(12,15,1997, "15dec1997");
		
	
		
		Date[] a = {date1, date2, date3, date4, date5};
		
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}System.out.println();
		
		//comparator
		Arrays.sort(a, BY_MONTH);
		System.out.println("sort by month");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}System.out.println();
		
		Arrays.sort(a, BY_DAY);
		System.out.println("sort by day");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}System.out.println();
		
		//uses the compareTo
		Arrays.sort(a);
		System.out.println("sort normally");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}System.out.println();
		
	}
	
}