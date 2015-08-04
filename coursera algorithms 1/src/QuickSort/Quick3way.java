package QuickSort;
import princetonLib.StdOut;
import princetonLib.StdRandom;
import princetonLib.StdIn;

public class Quick3way {
	/*
	 * Arrays with large numbers of duplicate sort keys arise frequently in
	 * applications. In such applications, there is potential to reduce the time
	 * of the sort from linearithmic to linear. One straightforward idea is to
	 * partition the array into three parts, one each for items with keys
	 * smaller than, equal to, and larger than the partitioning item's key.
	 * 
	 * 
	 Dijkstra's solution is based on a single left-to-right pass through the array that maintains a pointer 
	 lt such that a[lo..lt-1] is less than v, a pointer gt such that a[gt+1..hi] is greater than v, and a 
	 pointer i such that a[lt..i-1] are equal to v, and a[i..gt] are not yet examined.
	 
	Quicksort 3-way partitioning overview
	Starting with i equal to lo we process a[i] using the 3-way compare given us by the Comparable interface to handle the three possible cases:
		a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
		a[i] greater than v: exchange a[i] with a[gt] and decrement gt
		a[i] equal to v: increment i
	 */
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo, hi);
    }



   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        return (v.compareTo(w) == 0);
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; 3-way
     * quicksorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}
