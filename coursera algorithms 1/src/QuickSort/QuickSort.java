package QuickSort;
import MergeSort.MergeSort;
import princetonLib.StdRandom;
public class QuickSort {
	/******   http://algs4.cs.princeton.edu/23quicksort/ **********/
	
	
	final static int CUTOFF = 10;
	/*
Proposition1- Quicksort uses ~2 N ln N compares (and one-sixth that many exchanges) on the average to sort an array of length N with distinct keys.

Proposition2= . Quicksort uses ~(N^2)/2 compares in the worst case, but random shuffling protects against this case. (so thats why we shuffle)
	 */
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
		
	}

	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	/*
	 Repeat until i and j pointers cross.
		1-Scan i from left to right so long as (a[i] < a[lo]), if condition false then stop and move to step 2
		2-Scan j from right to left so long as (a[j] > a[lo]). if condition false then stop and move to step 3
		3-Exchange a[i] with a[j] 
	When pointers cross.
		-Exchange a[lo] with a[j]. so now a[j] is in place
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;//a[lo] is first element
		while (true) {//++i is i after incrementing
			/***step 1- keep incrementing i until a[i]>a[lo]****/
			while (less(a[++i], a[lo]))//find item on right to swap
				if (i == hi) break;
			/***step 2- keep decrementing j until a[j]<a[lo]****/
			while (less(a[lo], a[--j]))//find item on left to swap
				if (j == lo) break;
			/**check if pointers cross**/
			if (i >= j) break;//check if pointers cross, if they did then break out of while loop
			/***step 3****/
			exch(a, i, j); //IF POINTERS DONT CROSS THEN SIMPLY SWAP a[i] and a[j] and repeat the while loop
		}
		exch(a, lo, j); //swap with partitioning item (a[lo])
		return j; //return index (j) of item now known to be in place
	}
	
	
	private static void sort(Comparable[] a, int lo, int hi)
	{
		/*
		 * Insertion sort small subarrays.
			-Even quicksort has too much overhead for tiny subarrays.
			-Cutoff to insertion sort for ≈ 10 items.
			-Note: could delay insertion sort until one pass at end.
		 */
		if (hi <= lo + CUTOFF - 1)
		{
			InsertionSort(a, lo, hi);
			return;
		}
		int j = partition(a, lo, hi);
		//System.out.println("index ="+j+ ", element = "+ a[j]);
		//recursively sort elements left of j and right of j in the same way(partitioning / or insertion sort if size < 10)
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	/*
	 Basic plan. -  partitioning an array into two parts, then sorting the parts independently.
	-Shuffle the array.
		-Partition so that, for some j
		– entry a[j] is in place
		– no larger entry to the left of j
		– no smaller entry to the right of j
		-I.E. divide it so that for some value j, the entry a[j] is in place in the array
	-Sort each piece recursively.i.e. sort all elements left of a[j] and right of a[j] the same way as just now (partitioning)
	 */
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

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
