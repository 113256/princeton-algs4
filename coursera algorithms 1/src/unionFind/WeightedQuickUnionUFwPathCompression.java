package unionFind;

public class WeightedQuickUnionUFwPathCompression {
	private int[] id;
	//number of objects in the tree rooted at index i e.g. if sz[2] = 3, 3 objects have node (3) as the root
	//at the end all the nodes will be under 1 tree, so 1 index will = 10, but other index can be 1 or more so they might have their own child nodes
	private int[] sz;
	
	/*set id of each object to itself (N array accesses)*/
	public WeightedQuickUnionUFwPathCompression(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
		
		for (int i = 0; i < N; i++)
			sz[i] = 1;
	}
	/*chase parent pointers until reach root (depth of i array accesses)*/
	private int root(int i) {
		while (i != id[i])
			/*********FOR PATH COMPRESSION*******************/
			id[i] = id[id[i]];//
		/*******************************************/
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
		//id[i] = j;
		if (i == j) return;
		if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
		else { id[j] = i; sz[i] += sz[j]; }
	}
	
	public void print() {
		System.out.println();
		for (int i = 0; i < id.length; i++)
			System.out.print(id[i]+ "  ");
		System.out.println();
	}
	
	public void printSZ() {
		System.out.println();
		for (int i = 0; i < id.length; i++)
			System.out.print(sz[i]+ "  ");
		System.out.println();
	}
}
