
/*----------------------------------------------------------------------------
 * RandomizedQueue.java
jack chan
 * A RandomizedQueue supports item removal chosen uniformly at random from items
 * within the structure. This RandomizedQueue implementation uses a
 * ResizingArray structure and supports each randomized queue operation 
 * (besides creating an iterator) in constant amortized time and use space 
 * proportional to the number of items currently in the queue.
 * 
 * RandomizedQueue.java implements the following API:
 * public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()           // construct an empty randomized queue
   public void emptyRandomizedQueue() // delete all items and resize array to 2
   public boolean isEmpty()           // is the queue empty?
   public int size()                  // return the number of items on the queue
   public void enqueue(Item item)     // add the item
   public Item dequeue()              // delete and return a random item
   public Item sample()               // return (do not delete) a random item
   public Iterator<Item> iterator()   // return an independent iterator over
                                         items // in random order (currently 
                                         this is not random)
}
 *---------------------------------------------------------------------------*/



import java.util.Iterator;

//A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.
//so its not actually a proper queue because the item removed doesnt have to be at the front, can be any index

//<item> so that it is a generic stack, "item" can be anything e.g. stack of books, trucks, cats etc., won't be restricted to stack of strings etc.
public class RandomizedQueue<Item> implements Iterable<Item> {
	

	//some implementations call top as "first"
	private Node front = null;//points to front
	private Node back = null;//points to back (2 so that cost of both enqueueing and dequeueing will be O(1))
	
	public RandomizedQueue(){}
	
	/*note: arraylists already implements iterator automatically*/
	
	/*
	 * Q. What is an Iterable ?
		A. Has a method that returns an Iterator.
		
	Q. What is an Iterator ?
		A. Has methods hasNext() and next().
	 * */
	
	/****************ITERABLE INTERFACE*******************/
	//Implementing this interface allows an object to be the target of the "foreach" statement.
	//method
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	/****************ITERATOR INTERFACE*******************/
	
	private class ListIterator implements Iterator<Item>{
		private Node current = front; //pointer to the first/top node!
		
		
		
		
		
		/*Methods from iterable interface - linked list implementation*/
		public boolean hasNext(){
			return current!=null;
		}
		public void remove(){}//not supported
		//we want a random interator too
		public Item next(){
			
			
			if(size() == 1)
			{
				current=null;
				return front.item;
			}
			//remember its 0 (inclusive) and N (exclusive)
			
				if(isEmpty()) throw new java.util.NoSuchElementException ("error");
				
				int index = StdRandom.uniform(size());	
				//StdOut.println("index is:  "+index);
				//"pointer" to front node
				Node pointer = front;
				
				//traverse to index-1 (THE NODE BEFORE THE INDEX SO THAT I CAN ADJUST LINKS)
				for(int i = 0; i <index-1; i++)
				{
					pointer = pointer.next;                     
				}
				
				Node nodeToDelete = null;
				
				if(index != 0){
				//the nodeToDelete is the index-1.next
				nodeToDelete= pointer.next;
				//if im deleting index 5, index4.next = index5.next i.e. index6
				pointer.next = nodeToDelete.next;
				} else { //if index is 0 (the front element)
					nodeToDelete = front;
					front = front.next;
				}
				//if (isEmpty()) front = back = null;
				return nodeToDelete.item;	
		}
	}
	
	
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty(){
		return front == null;
	}
	
	//front(item, link) ---->(item, link) ---->(item, link) ---->(item, link) ---->BACK (item, NULL)
	public void enqueue(Item item){
		if(item == null) throw new java.lang.NullPointerException("error");
		Node oldBack = back;//point to front 
		back = new Node();
		back.item = item;
		back.next = null;
		
		if (isEmpty()) front = back;
		else oldBack.next = back;
	}
	
	public Item dequeue(){
		
		if(isEmpty()) throw new java.util.NoSuchElementException("error");
		//remember its 0 (inclusive) and N (exclusive)
		int index = StdRandom.uniform(size());
		//StdOut.println("index is:  "+index);
		
		
		//"pointer" to front node
		Node pointer = front;
		
		//if(index == 0){
		//	pointer = pointer.next;
		//}
		
		//System.out.println("front is:  "+ pointer.item);
		//traverse to index-1 (THE NODE BEFORE THE INDEX SO THAT I CAN ADJUST LINKS)
		for(int i = 0; i <index-1; i++)
		{
			pointer = pointer.next;                     
		}
		
		Node nodeToDelete = null;
		
		if(index != 0){
		//the nodeToDelete is the index-1.next
		nodeToDelete= pointer.next;
		//if im deleting index 5, index4.next = index5.next i.e. index6
		pointer.next = nodeToDelete.next;
		} else { //if index is 0 (the front element)
			nodeToDelete = front;
			front = front.next;
		}
		//if (isEmpty()) front = back = null;
		return nodeToDelete.item;	
	}
	
	
	//return (but do not remove) a random item
	 public Item sample()                     
	 {
		
			int index = StdRandom.uniform(size());
			Node pointer = front;
			//traverse to index itself 
			for(int i = 0; i <index; i++)
			{
				pointer = pointer.next;                     
			}

			return pointer.item;	
	 }
	
	public int size(){
		int size = 0;
		/*for(Item i : this)
		{
			size++;
		}*/
		Node pointer = front;
		 while(pointer != null){
	            size++;
	            pointer = pointer.next;
	        }
		return size;
	}

	
	public static void main(String[] args)
	{
		RandomizedQueue<String> stringQueue = new RandomizedQueue<String>();
		//foreach loop is syntactic sugar for java.util.iterator so it wont work if our class isn't iterable(not implementing iterable interface)
		
		stringQueue.enqueue("52");
		stringQueue.enqueue("6");
		StdOut.println(stringQueue.isEmpty());
		stringQueue.enqueue("7");
		stringQueue.enqueue("88");
		stringQueue.enqueue("82sdsds8");
		StdOut.println("size:   "+stringQueue.size());
		
		/*
		StdOut.println("dequeued  :"+ stringQueue.dequeue());
		StdOut.println("dequeued  :"+ stringQueue.dequeue());
		StdOut.println("dequeued  :"+ stringQueue.dequeue());
		StdOut.println("dequeued  :"+ stringQueue.dequeue());
		StdOut.println("dequeued  :"+ stringQueue.dequeue());
		*/
		
		
		//prints from front to back
		
		for(String i : stringQueue)
		{
			StdOut.println(i);
		}
		
		/*
		 
		//equivalent
		Iterator<String> i = intStack.iterator();
	
		while (i.hasNext())
		{
		String s = i.next();
		StdOut.println(s);
		}*/
			
	}
}
