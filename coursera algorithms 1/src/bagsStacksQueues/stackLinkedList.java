package bagsStacksQueues;

import java.util.Iterator;
import princetonLib.StdOut;

//<item> so that it is a generic stack, "item" can be anything e.g. stack of books, trucks, cats etc., won't be restricted to stack of strings etc.
public class stackLinkedList<Item> implements Iterable<Item> {
	

	//some implementations call top as "first"
	private Node top = null;
	
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
		private Node current = top; //pointer to the first/top node!
		
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
		return top == null;
	}
	
	public void push(Item item)
	{
		//"pointer" top node 
		Node oldTop = top;
		top = new Node();
		top.item = item;
		top.next= oldTop;
	}
	
	public Item pop() //pop top of stack
	{

		Item poppedTop = top.item;
		top = top.next;
		if(isEmpty())top = null;
		return poppedTop;
	}
	
	public static void main(String[] args)
	{
		stackLinkedList<String> intStack = new stackLinkedList<String>();
		//foreach loop is syntactic sugar for java.util.iterator so it wont work if our class isn't iterable(not implementing iterable interface)
		
		intStack.push("5");
		intStack.push("6");
		intStack.push("7");
		intStack.push("88");
		intStack.push("82sdsds8");
		//intStack.pop();
		
		//StdOut.println(intStack.top.item);
		
		for(String i : intStack)
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
