import java.lang.*;
public class Percolation {
	
//https://class.coursera.org/algs4partI-008/assignment/view?assignment_id=1
	//http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
    private boolean[][] opened;
    private int top = 0;
    private int bottom = 0;
    private int N;
    private WeightedQuickUnionUF uf;
	
	 public Percolation(int N)               // create N-by-N grid, with all sites blocked
	 {
		 if(N <=0)
		 {
			 throw new IllegalArgumentException ("N has to be greater than 0");
		 }
		 
		 this.N = N;
		 opened = new boolean[N][N];
		 //UF array to correspond to opened array, need uf for union and connected functions to work!!!
		 uf = new WeightedQuickUnionUF(N*N + 2);//last 2 indexes are for the top and bot virtual sites
		 
		
		 
		 top = N*N+(2-1);
		 bottom = N*N+(2-2);
		 //System.out.println("top bottom" + top + " "+ bottom);
	
	 }
	 
	 //1,1, = upper left (in array its 0,0 so we have to decrease by 1)
	 public void open(int i, int j)          // open site (row i, column j) if it is not open already
	 {
		 //System.out.println(opened[2][2]);
		 if(!isOpen(i,j)){
			 opened[i-1][j-1] = true;
			//union with all adjacent (opened sites)
			 
			 if(i == 1){
				 
			 }
			 
			 //site above
			 if(i != 1){
				 if(isOpen(i-1,j))
				 	{
					 uf.union(ufIndex(i, j), ufIndex(i-1, j));
				 	}
			 }
			 
			 if(j != 1){
				 if (isOpen(i,j-1))//site left
				 {
					 uf.union(ufIndex(i, j), ufIndex(i, j-1));
				 }
			 }
			 
			 if(j!=N){
				 if (isOpen(i,j+1))//site right
				 {
					 uf.union(ufIndex(i, j), ufIndex(i, j+1));
				 }
			 }
			 
			 if(i!=N){
				 if (isOpen(i+1,j))//site below
				 {
					 uf.union(ufIndex(i, j), ufIndex(i+1, j));
				 }
			 }
		 }
		 
		
		 
	 }
	 public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	 {
		 if((i<=0 && i >= N)||(j<=0 && j >= N)){
			 throw new IndexOutOfBoundsException ("i, j has to be between 1 and N");
		 }
		 //-1 because they want 1,1 to be top left but in array its 0,0
		 return opened[i-1][j-1];
	 }
	 
	 
	 /*
	   A full site is an open site that can be connected to an open site in the top row
	    via a chain of neighboring (left, right, up, down) open sites. We say the system
	     percolates if there is a full site in the bottom row. 
	  * */
	 public boolean isFull(int i, int j)     // is site (row i, column j) full?
	 {
		 if((i<=0 && i >= N)||(j<=0 && j >= N)){
			 throw new IndexOutOfBoundsException ("i, j has to be between 1 and N");
		 }
		 return uf.connected(i-1, j-1);
	 }
	 
	 public boolean percolates()             // does the system percolate? i.e. is there a full site in the bottom row
	 {
		 //last 2 indexes are for the top and bot virtual sites
		// top = N*N+(2-1);
		// bottom = N*N+(2-2);
		// System.out.println("top bottom" + top + " "+ bottom);
		 
		 //top virtual site
		 for(int i = 1; i <= N; i++)
		 {
			 if(isOpen(1,i)){
				 uf.union(top,  ufIndex(1, i));
			 }
		 }
		 
		//bottom virtual site
		 for(int i = 1; i <= N; i++)
		 {
			 if(isOpen(N,i)){
				 uf.union(bottom,  ufIndex(N, i));
			 }
		 }
		 //uf.union(top,  ufIndex(1,3));
		// System.out.println(uf.connected(top, ufIndex(1,3)));
		 //System.out.println(uf.connected(top, ufIndex(1,3)));
		 //System.out.println(uf.connected(bottom, ufIndex(3,3)));
		 return uf.connected(top, bottom);
	 }
	 
	 /*
	  convert boolean 2d array into WeightedQuickUnionUF array 
	  
	  2d array 3x3 (n=3):
	  1 0 0
	  0 1 0
	  1 1 1
	  
	  ufarray:
	  index: 0 1 2 3 4 5 6 7 8
	         1 0 0 0 1 0 1 1 1
	         
	  bottom right of 2d array = 3,3 (opened[3-1][3-1]) , ufarray = index 9  
	  
	  n*(i-1) + j = 3*2 + 2 = 8
	  * */
	 private int ufIndex(int i, int j)
	 {
		// decrease by 1 since we wont do it when calling method
		 i = i-1;
		 j = j-1;
		 
		 return N * i + j;
	 }
	 
	 
			   
	public static void main(String[] args)
	{
		// StdArrayIO.readBoolean2D();
		//  boolean[][] d = StdArrayIO.readBoolean2D();  //stdaarayIO.readBool.. because its static and from class called stdArrayIO
	      //  StdArrayIO.print(d);
	      //  StdOut.println();
		Percolation a = new Percolation(3);
		a.open(1,1);
		a.open(3,3); 
		a.open(2,3); 
		a.open(1,3); 
		//a.open(1,3); 
		System.out.println(a.percolates());
		//print "opened" 2d array
		StdArrayIO.print(a.opened);
		
		
		int T = 10;
		int N = 5;
		 for(int i = 0; i < T; i++){
			   
			  int openSites = 0;
			   int threshold = 0;
			   Percolation test = new Percolation(N);
			   System.out.println("test "+ i);
			   while(!test.percolates()){
				   //n+1 SINCE THE SECOND PARAM IS EXCLUSIVE!!!!
				   test.open(StdRandom.uniform(1, N+1), StdRandom.uniform(1, N+1));
				   openSites++;
				   StdArrayIO.print(test.opened);
				   System.out.println();
				   //System.out.println("     "+openSites);
			   }
			   threshold = openSites / (N*N);
			   //thresArray[i] = threshold;
			   }
		   
		
	}
}
