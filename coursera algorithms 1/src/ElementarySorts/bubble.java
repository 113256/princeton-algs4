package ElementarySorts;

import java.util.Arrays;

public class bubble {
//	In the bubble sort, as elements are sorted they gradually "bubble" (or rise) to their proper location in the array,
	/*
	 * 
It continues this process until all the items in the array are placed in their sorted order. The three simple steps for Bubble sort are:-

1 Compare two adjacent items in the array.
2 If the one in the left is greater, swap them.
3 Move the cursor one position right.


	time complexity O(n^2)
	 */
	
	//picture of bubble sort- http://www.java2novice.com/java-sorting-algorithms/bubble-sort/
	public static void BubbleSort( int [] A )
	{
		int n = A.length;
		// Outer loop - need n-1 iteration to sort n elements
		for(int i = n; i >=0 ;i--){
			
			////Inner loop to perform comparision and swapping between adjacent numbers
            //After each iteration one index from last is sorted
			for(int j = 1; j<n;j++)//array(n) and array(n-1)
			{
				if(A[j-1]>A[j])
				{
					int temp = A[j-1];
					A[j-1]=A[j];
					A[j] = temp;
				}
				
			}System.out.println(Arrays.toString(A));
			
		}
	}
	
	
	
	//while loop instead of an outer for loop, and the while loop would execute again only if elements had to be swapped on the last pass, like this:
	void BubbleSort2(int A[]) 
	{   int size = A.length;
	    boolean swapped;
	    do {
	        swapped = false;
	        for (int j=0;j<size-1;j++)
	            if (A[j]>A[j+1]) {
	                int temp = A[j];
	                A[j] = A[j+1];
	                A[j+1] = temp;
	                swapped = true;
	            }
	      //break away from while loop IF you dont have to swap anymore elements in the for loop
	      //i.e. if A[j] is never > A[j+1] swapped will be false hence break
	    } while (swapped);
	}
	
	
	
	
	public static void main(String[] args)
	{
		int intArray[] = new int[]{5,90,35,45,50,3};
		BubbleSort(intArray);
		   System.out.println("Array After Bubble Sort");
           for(int i=0; i < intArray.length; i++){
                   System.out.print(intArray[i] + " ");
           }
		
	}
	     
}
