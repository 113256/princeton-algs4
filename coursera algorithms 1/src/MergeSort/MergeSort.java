package MergeSort;
import ElementarySorts.Insertion;
import princetonLib.StdRandom;
public class MergeSort {

	//BIG-OH = O(nlog(n)), space complexity = O(n)
	/*
	 Running time estimates:
		-Laptop executes 10^8 compares/second.
		-Supercomputer executes 10^12 compares/second.
		
		n = 			1k 			1m 		1b
		laptop -		instant		1s		18m
		supercomputer	instant		instant	instant
	 */

	/****merge sort - divide and conquer ****/
	/*
	 basic plan 
		-Divide array into two halves.
		-Recursively sort each half.
		-Merge two halves. */
	
	final static int CUTOFF = 7;
	
	public static void InsertionSort(Comparable[] a, int lo, int hi)
	{
		int N = a.length;
		for (int i = lo; i <= hi; i++){//remember is <= this time
			for (int j = i; j > 0; j--){
				if (less(a[j], a[j-1])){
					exch(a, j, j-1);}
				else break;
				
			}
		}
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
	{
		//Mergesort has too much overhead for tiny subarrays.
		//Cutoff to insertion sort for ≈ 7 items.
		if (hi <= lo + CUTOFF - 1)
		{
			//System.out.println("using insertionsort");
			InsertionSort(a, lo, hi);
			return;
		}
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		
		//recursively sort each half
		System.out.println("sort 1st");
		sort(a, aux, lo, mid);
		System.out.println("sort 2nd");
		sort(a, aux, mid+1, hi);
		
		//stop if already sorted
		if (less(a[mid], a[mid+1])) return;//Is biggest item in first half ≤ smallest item in second half?
		
		//merge 2 halves
		System.out.println("merging");
		merge(a, aux, lo, mid, hi);
	}
	
	//actual sort
	public static void sort(Comparable[] a)
	{
		//create aux array where the original one will be copied to
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
	
	
	
	/*
	merging
		goal- Given two sorted subarrays a[lo] to a[mid] and a[mid+1] to a[hi],replace with sorted subarray a[lo] to a[hi].
		
		-using auxiliary array to hold combined
		-so copy the 2 subarrays to aux[]
		-now we have to copy back to original array but in sorted order
		
		-maintain 3 indices i,j,k
		-i (current entry in left half), j (current entry in right half) both in aux
		-k (current entry in sorted result) a[]
		-take smaller of 2 entries pointed to by i and j and compare, then take the smallest one and replace a[k] with that. 
			whichever one (i/j) was taken, we increment its pointer, also increment k each time
		-*if 2 entries are equal just take one in left array 
		-repeat
		-when eg. elements in left array are all gone, just put the remaining ones from right array (in left to right order)

	 */
	
	
	//hi = highest index
	private static void merge(Comparable[] a, Comparable[] aux, int lo,
			int mid, int hi) {
		//assertions to make debugging easier
		assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid + 1, hi); // precondition: a[mid+1..hi] sorted
		/***copy everything to auxiliary array*****************/
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		/***merge! *****************/
		//i pointer for left half, j for right half (both in aux array)
		//k pointer for aux array
		int i = lo, j = mid + 1;//i = start of left array, j = start of right array
		for (int k = lo; k <= hi; k++) {
			/*
			 when eg. elements in left array are all gone, just put the 
			 remaining ones from right array (in left to right order)
			 */
			if (i > mid) a[k] = aux[j++];
			//if j pointer is exhausted (no more elements in right array)
			else if (j > hi) a[k] = aux[i++];
			/*
			 * take smaller of 2 entries pointed to by i and j 
			 * and compare, then take the smallest one and replace
			 *  a[k] with that. whichever one (i/j) was taken, 
			 *  we increment its pointer, also increment k each time
			 */
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		/**************************/
		//assertion at the end to test the post condition that entire array is sorted
		assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
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
	
	private static boolean isSorted(Comparable[] a, int lo, int hi)
	{	
		System.out.println("test");
		//for (int i = 1; i < a.length; i++)
		for (int i = lo; i <= hi; i++)
		if (less(a[i], a[i-1])) {System.out.println("notsorted"); return false;}
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
		
		
		//NOT INT BECAUSE INTEGER IS A COMPARABLE
		Integer[] b = new Integer[50];
		for(int i = 0; i<50;i++)
		{
			b[i] = StdRandom.uniform(200);
		}
		
		/*for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}*/
		for(int i = 0; i < b.length; i++){
			System.out.print(b[i]+ " ,");
		}
		System.out.println();
		sort(b);
		/*System.out.println("\n\nafter mergesort:\n\n");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].getName());
		}*/
		for(int i = 0; i < b.length; i++){
			System.out.print(b[i]+ " ,");
		}
	}
}








