
public class Subset {
	public static void main(String[] args)
	{
		int k = StdIn.readInt();
		StdOut.println(k);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
            //queue.addLast(StdIn.readString());
			
			String s = StdIn.readString();
			//StdOut.println(s);
			//StdOut.println(StdIn.isEmpty());
            queue.enqueue(s);
            /****in eclipse you need ctrl+z to break out of this loop since its not the command line********/   
        }
		
		
		//StdOut.println("f");
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.iterator().next());
        }
    
	}
}
