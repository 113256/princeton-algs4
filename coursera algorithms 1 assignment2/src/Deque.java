

import java.util.Iterator;


//<item> so that it is a generic stack, "item" can be anything e.g. stack of books, trucks, cats etc., won't be restricted to stack of strings etc.
public class Deque<Item> implements Iterable<Item> {
	

	//some implementations call top as "first"
	private Node front = null;//points to front
	private Node back = null;//points to back (2 so that cost of both enqueueing and dequeueing will be O(1))
	
	
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
			return current != null;
		}
		public void remove(){}//not supported
		
		public Item next(){
			if(current == null) throw new java.util.NoSuchElementException("error");
			Item item = current.item;
			current = current.next;
			return item;
			
		}
	}
	/***********************************************************/
	
	public Deque(){
		
	}
	
	private class Node{
		Item item;
		Node next, prev;
		private Node(){
			//next = null;
			//prev = null;
		}
	}
	
	public boolean isEmpty(){
		return ((back == null)||(front==null));
	}
	
	public int size(){
		int size = 0;
		for(Item i : this)
		{
			size++;
		}
		return size;
	}
	//front(item.NULL) <-----(item,prev)<-----(item,prev)<-----(item,prev)<-----BACK(item,prev)
	//front(item, next) ---->(item, next) ---->(item, next) ---->(item, next) ---->BACK (item, NULL)
	public void addLast(Item item){
		
		if(item == null) throw new java.lang.NullPointerException("error");
		Node oldBack = back;//point to front 
		back = new Node();
		back.item = item;
		back.next = null;
		
		
		if (isEmpty()) front = back;
		else {
			oldBack.next = back;
			back.prev = oldBack;
		}
	}
	//remove front
	public Item removeFirst(){
		if(isEmpty()) throw new java.util.NoSuchElementException("error");
		Node deletedNode = front;
		front = front.next; 
		front.prev = null;
		//if (isEmpty()) back = null;
		return deletedNode.item;
	}

	
	public void addFirst(Item item){
		
		if(item == null) throw new java.lang.NullPointerException("error");
		//boolean wasEmpty;
		//wasEmpty= front == null;
		//StdOut.println(wasEmpty);
		Node oldFront = front;//point to front 
		front = new Node();
		front.item = item;
		front.prev = null;
		//if it was empty (i.e. this is the first insert)
		if (isEmpty()) {
			back=front;
			//System.out.println("empty");
		}else {
			//System.out.println("NOTempty");
			oldFront.prev = front;
			front.next = oldFront;
			
		}
	}
	
	public Item removeLast(){
		if(isEmpty()) throw new java.util.NoSuchElementException("error");
		Node deletedNode = back;
		back = back.prev; 
		//if (isEmpty()) front = null;
		return deletedNode.item;
	}
	
	/*public void print(){
		for(Item i : this)
		{
			StdOut.print(" "+i);
		}
	}
	*/
	public static void main(String[] args)
	{
		/*
		Deque<Integer> deque = new Deque<Integer>();
		StdOut.println("----");
		StdOut.println(deque.isEmpty());
		deque.addFirst(5);
		StdOut.println("----");
		StdOut.println(deque.size());
		StdOut.println("----");
		deque.addFirst(6);
		StdOut.println("----");
		deque.addFirst(7);
		deque.addLast(4);
		deque.print();
		StdOut.println();
		StdOut.println(deque.size());
		*/
	}
}
