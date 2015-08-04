package bagsStacksQueues;

import java.util.Iterator;
import princetonLib.StdOut;

//<item> so that it is a generic stack, "item" can be anything e.g. stack of books, trucks, cats etc., won't be restricted to stack of strings etc.
public class queueLinkedList<Item> implements Iterable<Item> {
	

	//some implementations call top as "first"
	private Node front = null;//points to front
	private Node back = null;//points to back (2 so that cost of both enqueueing and dequeueing will be O(1))
	
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
			return current != null;
		}
		public void remove(){}//not supported
		
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
			
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
		Node oldBack = back;//point to front 
		back = new Node();
		back.item = item;
		back.next = null;
		
		if (isEmpty()) front = back;
		else oldBack.next = back;
	}
	
	public Item dequeue(){
		Node deletedNode = front;
		front = front.next; 
		if (isEmpty()) back = null;
		return deletedNode.item;
	}

	
	public static void main(String[] args)
	{
		queueLinkedList<String> stringQueue = new queueLinkedList<String>();
		//foreach loop is syntactic sugar for java.util.iterator so it wont work if our class isn't iterable(not implementing iterable interface)
		
		stringQueue.enqueue("5");
		stringQueue.enqueue("6");
		StdOut.println(stringQueue.isEmpty());
		stringQueue.enqueue("7");
		stringQueue.enqueue("88");
		stringQueue.enqueue("82sdsds8");
		
		stringQueue.dequeue();
		stringQueue.dequeue();
		
		
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
