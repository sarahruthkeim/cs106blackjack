public class SinglyLinkedList<E> {
/**
* Singly Linked List implementation taken from slides
*/


   private Node<E> head;        // head node of the list (or null if empty)
   private Node<E> tail;        // last node of the list (or null if empty)
   private int size;               // number of nodes in the list

   public SinglyLinkedList() {    // constructs an initially empty list
       head = null;
       tail = null;
       size = 0;
   }

   /* continuation of Class SinglyLinkedList */
   public int size() {
       return size;
   }
   public boolean isEmpty() {
       return size == 0;
   }
   public E first( ) {                 // returns (but does not remove) the first element
       if (isEmpty( )) return null;
       return head.getElement( );
   }

   public E last() {                  // returns (but does not remove) the last element
       if (isEmpty( )) return null;
       return tail.getElement( );
   }

   // update methods
   public void addFirst(E e) {         // adds element e to the front of the list
       Node<E> newest = new Node<E>(e, null);
       newest.setNext(head);
       head = newest;

       if (isEmpty())
           tail = head;                // special case: new node becomes tail also

       size++;
   }
   public void addLast(E e) {          // adds element e to the end of the list
       Node<E> newest = new Node<E>(e, null); // node will eventually be the tail

       if (isEmpty())                 // special case: previously empty list
           head = newest;
       else
           tail.setNext(newest);       // new node after existing tail

       tail = newest;                  // new node becomes the tail
       size++;
   }

   public E removeFirst() {            // removes and returns the first element
       if (isEmpty( ))                 // nothing to remove
           return null;

       E answer = head.getElement( );
       head = head.getNext( );         // will become null if list had only one node
       size--;

       if (size == 0)
           tail = null;                // special case as list is now empty

       return answer;
   }

   public String toString() {
       if (this.isEmpty()){
           return "empty stack.";
       }
       Node<E> curr = head;
       String str = head.toString();                   // Using toString from the Node Class
       while(curr.getNext() != null){
           curr = curr.getNext();
           str = str + " -> " + curr.toString();       // Using toString from the Node Class
       }
       return str;
   }
}


