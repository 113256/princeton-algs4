package unionFind;

public class QuickUnionUF {
	private int[] id;
	
	/*
	 Integer array id[] of length N.
	Interpretation: id[i] is parent of i.
	Root of i is id[id[id[...id[i]...]]].
	 */
	
	
	/*set id of each object to itself (N array accesses)*/
	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}
	/*chase parent pointers until reach root (depth of i array accesses)*/
	private int root(int i) {
		while (i != id[i])
			i = id[i];//keep backtracking node until you reach root
		return i;
	}
	/*check if p and q have same root (depth of p and q array accesses)*/
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	/*change root of p to point to root of q (depth of p and q array accesses)*/
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	
	public void print() {
		System.out.println();
		for (int i = 0; i < id.length; i++)
			System.out.print(id[i]+ "  ");
		System.out.println();
	}
}