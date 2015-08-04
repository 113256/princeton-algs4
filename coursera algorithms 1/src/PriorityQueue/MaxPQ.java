//priority queue using binary heap
/*
 * cost summary
 * insert- log N
 * del Max- log N
 * Max- 1 
 */
package PriorityQueue;


import princetonLib.StdOut;
//Requirement. Generic items are Comparable.
public class MaxPQ<Key extends Comparable<Key>>
{
	/*
	 * Priority queue. Remove the largest (or smallest -MinPQ) item. -implementation using binary heap
	 * 
	 * Binary heap. Array representation of a heap-ordered complete binary tree.
	 * 	-Largest key is a[1], which is root of binary tree.
	 * 	-Can use array indices to move through tree.
			-Parent of node at k is at k/2.
			-Children of node at k are at 2k and 2k+1.
			
	 * A binary heap is a complete binary tree which satisfies the heap ordering property.
		The ordering can be one of two types: (we are implementing max-heap)
			-the min-heap property: the value of each node is greater than or equal to the value of its parent, with the minimum-value element at the root.
			-the max-heap property: the value of each node is less than or equal to the value of its parent, with the maximum-value element at the root.
	 */
	private Key[] pq;//heap
	private int N;
	
	//create an empty priority queue
	public MaxPQ(int capacity)
	{
		pq = (Key[]) new Comparable[capacity+1]; //for null at 0th index#
	}
	
	public boolean isEmpty()
	{
		return N == 0; 
	}
	
	//Cost. At most 1 + lg N compares. (lgN for swim)
	//insert a key into the priority queue
	public void insert(Key x)
	{
		pq[++N] = x;
		swim(N);//swim it up until its smaller than parent
	}
	
	
	
	//return and remove the largest key
	//priority queue feature
	//Cost. At most 2 lg N compares.
	/*
	 * swaps root(max) and final index(the rightmost child at the bottom level) then delete the max(which was swapped to last index)
	 * then sink the root(since its not the largest anymore)
	 */
	public Key delMax() 
	{
		if(isEmpty()) throw new java.util.NoSuchElementException("can't delete from empty heap");
		Key max = pq[1];
		exch(1, N--);
		sink(1);	
		pq[N+1] = null;
		return max; 
	}
	//starts from 1 because, children = 2k and 2k+1 if you start from 0, 2k  and 2k+1 is still 0!
	public Key max()
	{
		return pq[1];
	}
	
	/*
	 * when child's key is larger than parent key we have to eliminate this violation
	 * 	-exchange key in child with key in parent
	 * 	-repeat until heap order is restored
	 */
	private void swim(int k)
	{
		while (k > 1 && less(k/2, k))//swim up until k/2(parent) >= k
		{
			exch(k, k/2);
			k = k/2;//k = parent of k
		}
	}
	/*
	 * when parents key is smaller than one of its children's
	 * 	-exchange key in parent with key in child
	 * 	-repeat until heap order is restored
	 */
	private void sink(int k)
	{ 
		while (2*k <= N)
		{
			int j = 2*k;
			//children of k are 2k and 2k+1
			//find out whether index 2k or 2k+1 is bigger
			if (j < N && less(j, j+1)) j++; //if index 2k is < than 2k+1 make j = 2k+1 instead of 2k (by doing j++)
			if (!less(k, j)) break;//if k is not less than its children we're done
			else {
				exch(k, j);//if k is less than one of its children, swap with it (if less than both, swap with larger)
			}
			k = j;
		}
	}
	
	
	
	private boolean less(int i, int j)
	{ 
		return pq[i].compareTo(pq[j]) < 0; 
	
	}
	
	private void exch(int i, int j)
	{	//i--;j--;
		Key t = pq[i]; 
		pq[i] = pq[j];
		pq[j] = t; 
	}
	
	
    public void print() {
        int N = pq.length;
        //StdOut.println(N);
        for (int i = 0; i < N ; i++) {
            StdOut.print(pq[i]+ " ");
        }
        StdOut.println();
    }
	
	public static void main(String[] args)
	{
		MaxPQ<Integer> a = new MaxPQ<Integer>(6);
		a.insert(1);
		a.insert(2);
		a.insert(3);
		a.insert(4);
		a.insert(5);
		a.insert(6);
		
		
		
		a.print();
		//System.out.println(apq[1]);
		//System.out.println(a.max());
		
		//a.sort();
		
		a.print();
		
		
		
		
		
	}
}