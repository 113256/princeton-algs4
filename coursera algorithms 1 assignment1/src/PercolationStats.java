import java.lang.*;

public class PercolationStats {
	
	private double thresArray[];
	private int T;
	private double mean, stddev;
	private int N;
	
	/*
Initialize all sites to be blocked.
Repeat the following until the system percolates:
	Choose a site (row i, column j) uniformly at random among all blocked sites.
	Open the site (row i, column j).
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
	 */
   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   {
	   this.N = N;
	   this.T= T;
	   double openSites = 0;//NOT INT OR ELSE YOU GET 0 WHEN DIVIDING
	   double threshold = 0;
	   thresArray = new double[T];
	   
	   for(int i = 0; i < T; i++){
		   
		   //System.out.println(i);
		   openSites = 0;
		   threshold = 0;
		   Percolation test = new Percolation(N);
		  // System.out.println("test "+ i);
		   while(!test.percolates()){
			 //n+1 SINCE THE SECOND PARAM IS EXCLUSIVE!!!!
			   int random1 = StdRandom.uniform(1, N+1);
			   int random2 = StdRandom.uniform(1, N+1);
			   
			   if(!test.isOpen(random1, random2)){
				   test.open(random1, random2);
				   openSites=openSites+1;//only add 1 if the site isn't previously open
			   }
			   
			  
			   //System.out.println("     "+openSites);
		   }
		   threshold = openSites / (N*N);
		   thresArray[i] = threshold;
		  // System.out.println("threshold is "+ threshold);
		   }
	   
   }
   public double mean()                      // sample mean of percolation threshold
   {
	   mean = StdStats.mean(thresArray);
	   return mean;
	
	}
   public double stddev()                    // sample standard deviation of percolation threshold
   {
	   stddev = StdStats.stddev(thresArray);
	return stddev;
	}
   
   public double confidenceLo()              // low  endpoint of 95% confidence interval
   {
	return mean-((1.96*stddev)/(Math.sqrt(T)));
	   
   }
   public double confidenceHi()              // high endpoint of 95% confidence interval
   {
	   
	   return mean+((1.96*stddev)/(Math.sqrt(T)));
	}

   public static void main(String[] args)    // test client (described below)
   {
	
	   int N = StdIn.readInt();
       int T = StdIn.readInt();
	   
       if(T<=0 || N<=0)
       {
    	   throw new IllegalArgumentException("T and N has to be greater than 0");
       }
       
       //System.out.println(N + " " +T);
       //System.out.println(Math.sqrt(T));
       PercolationStats test1 = new PercolationStats(N, T);
       System.out.println("mean                =" + test1.mean());
       System.out.println("stddev                =" + test1.stddev());
       System.out.println("95% confidence interval =" + test1.confidenceLo() +", "+ test1.confidenceHi());
   }
}
