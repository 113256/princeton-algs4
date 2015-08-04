package elementarySymbolTables;

/*************************************************************************
 *  Compilation:  javac LinkedListST.java
 *  Execution:    java LinkedListST
 *  
 *  Symbol table implementation with linked list.
 *
 *  % java LinkedListST
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *  Remarks
 *  -------
 *    - Consider using a sentinel to eliminate annoying special
 *      case when deleting at the beginning of the list.
 *      
 implements a symbol table with an (unordered) linked list. Both put and
get take linear time per operation: to search for a key, we need to traverse 
its links; to put a key-value pair, we need to search for the given key.
 *
 *
 *
 *
 *A symbol table is a data type that we use to associate values with keys.
 * Clients can store (put) an entry into the symbol table by specifying a 
 * key-value pair and then can retrieve (get) the value corresponding to a
 *  particular key from the symbol table. For example, a university might
 *   associate information such as a student's name, home address, and grades (the value)
 *    with that student's social security number (the key), so that each student's records
 *     can be accessed by specifying a social security number. 
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListST<Key, Value> implements Iterable<Key> {

    private int N;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        Key key;
        Value value;
        Node next;

        Node(Key key, Value value, Node next)  {
            this.key   = key;
            this.value = value;
            this.next  = next;
        }
    }

    // return number of key-value pairs
    public int size() { return N; }

    // does this symbol table contain the given key?
    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) return true;
        return false;
    }

    // add a key-value pair, replacing old key-value pair if key is already present
    public void put(Key key, Value value) {
    	//traverse until the end to see if key already exists then just replace it
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)) { x.value = value; return; }//
        }
        //first = first node in the linked list
        //here we create node called first that points to the old first node in the linked list
        //so the newest entry will be the first at the front
        first = new Node(key, value, first);
        N++;
    }

    // remove key-value pair with given key, and return associated value
    // return null if no such key
    public Value remove(Key key) {
        // special cases
        if (first == null) return null;

        if (key.equals(first.key)) {
            Value val = first.value;
            first = first.next;
            N--;
            return val;
        }

        // general case
        for (Node x = first; x.next != null; x = x.next)
            if (key.equals(x.next.key)) {
                Value val = x.next.value;
                x.next = x.next.next;
                N--;
                return val;
            }
        return null;
    }

    // return the value associated with the key, or null if the key is not present
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.value;
        }
        return null;     // not found
    }

    // a linked list iterator, doesn't implement remove() since it's optional
    public Iterator<Key> iterator()  { return new ListIterator(first);  }
    private class ListIterator implements Iterator<Key> {
        Node current;
        ListIterator(Node first)     { current = first;                            }
        public boolean hasNext()     { return current != null;                     }
        public void remove()         { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            Key key = current.key;
            current = current.next; 
            return key;
        }
    }




   /***********************************************************************
    * Test routine.
    **********************************************************************/
    public static void main(String[] args) {
        LinkedListST<String, String> st = new LinkedListST<String, String>();

        // insert some key-value pairs
        st.put("www.cs.princeton.edu",   "128.112.136.11");
        st.put("www.cs.princeton.edu",   "128.112.136.35");
        st.put("www.princeton.edu",      "128.112.130.211");
        st.put("www.math.princeton.edu", "128.112.18.11");
        st.put("www.yale.edu",           "130.132.51.8");
        st.put("www.amazon.com",         "207.171.163.90");
        st.put("www.simpsons.com",       "209.123.16.34");
        st.put("www.stanford.edu",       "171.67.16.120");
        st.put("www.google.com",         "64.233.161.99");
        st.put("www.ibm.com",            "129.42.16.99");
        st.put("www.apple.com",          "17.254.0.91");
        st.put("www.slashdot.com",       "66.35.250.150");
        st.put("www.whitehouse.gov",     "204.153.49.136");
        st.put("www.espn.com",           "199.181.132.250");
        st.put("www.snopes.com",         "66.165.133.65");
        st.put("www.movies.com",         "199.181.132.250");
        st.put("www.cnn.com",            "64.236.16.20");
        st.put("www.iitb.ac.in",         "202.68.145.210");

        // search for IP addresses given URL
        System.out.println("size = " + st.size());
        System.out.println(st.get("www.cs.princeton.edu"));
        System.out.println(st.get("www.amazon.com"));
        System.out.println(st.get("www.amazon.edu"));
        System.out.println();

        // test out the iterator
        for (String s : st) 
            System.out.println(s);


        // print out all key-value pairs
        for (String s : st)
            System.out.println(s + " " + st.get(s));
        System.out.println();

        System.out.println("Deleting");
        System.out.println(st.remove("www.princeton.edu"));
        System.out.println(st.remove("www.princeton.edu"));
    }

}
