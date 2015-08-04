package PriorityQueue;

import princetonLib.StdOut;
 //sorting using a binary heap
public class Heap {

    // This class should not be instantiated.
    private Heap() { }

    /**N log N guarantee
     * heapsort arranges the array in a max-heap then sorts it ascendingly
     * 
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int N = pq.length;
        //Build heap using bottom-up method.
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);
        //Remove the maximum, one at a time
        //doesnt actually remove it, just decrease N in the argument so that they are excluded from the exchange and sink operations 
        while (N > 1) {
        	//exchange the root(max) and last index, then "remove (N--)" max(which is now in last index) then sink root to maintain heap orders 
            exch(pq, 1, N--);
            sink(pq, 1, N);//put N as argument here because N decreases and we want to exclude removed elements so they stay in place
        }
    }

   /***********************************************************************
    * Helper functions to restore the heap invariant.
    **********************************************************************/

    private static void sink(Comparable[] pq, int k, int N) {
    	//cant sink bottom row nodes and 
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /***********************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.nb
    * -since arrays for heaps start at 1 because if k=0, 2k and 2k+1 is still 0
    **********************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
        

   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
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
     * Reads in a sequence of strings from standard input; heapsorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        Integer[] a = {7,6,5,4,3,2,1};
        Heap.sort(a);
        show(a);
    }
}
