package analysisOfAlgorithms;

public class threeSum {
	public static void main(String[] args)
	{
		//int[] sorted = {30,-40,-20,-10,40,0,10,5};
		int[] sorted =   {-40,-20,-10,0,5,10,30,40};
		findThreeSum(sorted);
	}
	
	public static int findThreeSum(int[] a)
	{
		//for each pair of numbers
		for(int i = 0; i<a.length-1; i++){
			for(int j = 1; j <a.length; j++)
			{
				int k = -(a[i]+a[j]);
				//if statement to avoid double counting
				if(a[i] < a[j] && a[j] < k) {
					System.out.print(k+ " | ");
				if(binarySearch(a, k) != -1){
					System.out.println("three sum is " + a[i]+ " ," + a[j]+ ", "+ k);
				} else System.out.println("not found");
				}
			}
		}
		return 0;
	}
	
	public static int binarySearch(int[] a, int key)
	{
		int lo = 0, hi = a.length-1; //low and highest index
		while(lo <= hi)
		{
			int mid = lo + (hi-lo) / 2;
			if (key < a[mid])
			{
				hi = mid - 1;
			} else if (key > a[mid])
			{
				lo = mid +1;
			} else return mid;
		}
		
		return -1;
	}
	
}	
