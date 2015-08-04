package unionFind;

import princetonLib.StdIn;

public class UnionFind {
	
	
	
	public static void main(String[] args) {
		/*
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				System.out.println(p + " " + q);
			}
		}*/
		System.out.println("quickfind");
		QuickFindUF uf = new QuickFindUF(10);
		uf.print();
		uf.union(6,9);
		uf.union(8,1);
		uf.union(0,7);
		uf.union(4,5);
		uf.union(4,7);
		uf.union(7,9);
		
		System.out.println();
		
		uf.print();
		
		
		/*******quick- union*********/
		System.out.println("quick-union");
		QuickUnionUF uf1 = new QuickUnionUF(10);
		/*2-3 9-3 0-5 6-9 8-7 6-4 9-1 5-7 1-0 */
		uf1.union(2, 3);
		uf1.print();
		uf1.union(9, 3);
		uf1.union(0, 5);
		uf1.union(6, 9);
		uf1.union(8, 7);
		uf1.union(6, 4);
		uf1.union(9, 1);
		uf1.union(5, 7);
		uf1.union(1, 0);
		
		System.out.println();
		uf1.print();
		
		
		

		/*******weighted quick- union*********/
		System.out.println("weighted quick union");
		WeightedQuickUnionUF uf2 = new WeightedQuickUnionUF(10);
		/*  0-4 8-5 3-1 6-9 6-0 3-5 3-2 0-5 8-7 */
		uf2.union(0, 4);
		uf2.union(8, 5);
		uf2.union(3, 1);
		uf2.union(6, 9);
		uf2.union(6, 0);
		uf2.union(3, 5);
		uf2.union(3, 2);
		uf2.union(0, 5);
		uf2.union(8, 7);
		
		System.out.println();
		uf2.print();
		//index 2 = 10 because all the nodes are in 1 tree with 2 being the root
		//index 0 = 4 because 4 nodes are children of node 0
		uf2.printSZ();
		

	}
}
