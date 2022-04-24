// copied from slides

public class CircularlyLinkedList<E> {
    private Node<E> tail;
    private int size;

    public CircularlyLinkedList( ) {
        this.tail = null;
        this.size = 0;
    }

    // access methods
    public int size( ) { return size; }
    public boolean isEmpty( ) { return size == 0; }
    public E first( ) {
        if (isEmpty( ))
            return null;
        return tail.getNext( ).getElement( );
    }




    public E last( ) {
        if (isEmpty( ))
            return null;
        return tail.getElement( );
    }

    public Node<E> getFirst() {
        return tail.getNext();
    }

    public Node<E> getLast() {
        return tail;
    }

    // update methods
    public void rotate( ) {
        if (tail != null)
            tail = tail.getNext( );
    }
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, null);
            newest.setNext(tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }
    public void addLast(E e) {                      // adds element e to the end of the list
        addFirst(e);                                // insert new element at front of list
        tail = tail.getNext( );                         // now new element becomes the tail
    }

    public E removeAt(int position) {
        Node<E> currentNode = tail;
        for (int i = 0; i <= position; i++) {
            if (i == position) {
                Node<E> removedNode = currentNode.getNext();
                currentNode.setNext(removedNode.getNext());
                removedNode.setNext(null);
                size--;
                return removedNode.getElement();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public E removeFirst( ) {                       // removes and returns the first element
        if (isEmpty())                              // nothing to remove
            return null;
        Node<E> head = tail.getNext( );
        if (head == tail)
            tail = null;                            // must be the only node left
        else
            tail.setNext(head.getNext( ));         // removes ”head” from the list
        size--;
        return head.getElement( );
    }

    public String toString() {
        Node<E> currentNode = tail.getNext();
        String printedList = "{";
        for (int i = 0; i < size - 1; i++) {
            printedList += currentNode.toString() + ", ";
            currentNode = currentNode.getNext();
        }
        return printedList + currentNode.toString() + "}";
    }


}
