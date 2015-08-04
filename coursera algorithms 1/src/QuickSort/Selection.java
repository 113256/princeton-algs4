package QuickSort;
import princetonLib.StdRandom;


public class Selection {
	/*
	 * Goal. Given an array of N items, find a kth smallest item. (starting from 0)
		Ex. Min (k = 0), max (k = N - 1), median (k = N / 2).
	Applications.
		-Order statistics.
		-Find the "top k."
	 */
	
	
	/*
	 quick-select
	 
	 Partition array so that:
		-Entry a[j] is in place.
		-No larger entry to the left of j.
		-No smaller entry to the right of j.
		
		Repeat in one subarray, depending on j; finished when j equals k.
		
		-takes linear time on average
		-Quick-select uses ~ ½ N 2 compares in the worst case, but (as with quicksort) the random shuffle provides a probabilistic guarantee (if the random shuffle shuffled it into order)
	 */
	
	public static Comparable select(Comparable[] a, int k)
	{
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length - 1;
		while (hi > lo)
		{
			int j = partition(a, lo, hi);//no elements left of j is > j, no elements right of j is < j; j is in place
			if (j < k) lo = j + 1;  //if k is right of j we just do the right subarray
			else if (j > k) hi = j - 1; //if k is left of j we just do the left subarray
			else return a[k];
		}
		return a[k];
	}
	
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
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
		
	}
	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
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
		System.out.println(select(b, 5)+ " is the 5th (start from 0) smallest element");
		
		//proof that select(b,5) is 5th smallest (starting from 0)
		QuickSort.sort(b);
		for(int i = 0; i < b.length; i++){
			System.out.print(b[i]+ " ,");
		}
		
		
	}
	
}
