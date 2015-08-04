package ElementarySorts;
import princetonLib.StdRandom;

public class Shuffle {
	
	
	
	private static void exch(Comparable[] a, int i, int j)
	{
		int swap = (Integer) a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	/*
	 In iteration i, pick integer r between index 0 and i uniformly at random.
	 Swap a[i] and a[r].
	 */
	public static void shuffle(Comparable[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = StdRandom.uniform(i + 1);
			exch(a, i, r);
		}
	}
	
	public static void print(Comparable[] a)
	{
		for (int i = 0; i<a.length; i++)
		{
			System.out.print(a[i] + ", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		Integer[] a = new Integer[50];
		for (int i = 0; i<a.length; i++)
		{
			a[i] = i;
		}
		
		print(a);
		shuffle(a);
	
		print(a);
	}
}
