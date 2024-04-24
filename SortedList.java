import java.util.Iterator;


public class SortedList<E extends Comparable<? super E>> extends List<E> {


        //Insert method
        public void insert(E data) {
            //newNode is created with parameter data
            Node<E> newNode = new Node<E>(data);
            //check if the list is empty
            if(head==null){
                head=newNode;
            }

            //head its the first element
            if (data.compareTo(head.data) < 0) {
                //Consequencia:new node que contiene el valor de data, debe ser declarado como el head
                newNode.next = head;
                head = newNode;
            }
            else {
                Node<E> currNode = new Node<E>(data);
                currNode = head;
                //recorrer lista
                head = recursiveInsert(head, data);
            }
        }

        private Node<E> recursiveInsert(Node<E> currNode, E data) {
            //means if currNode has reached the end of the list, put it on the right place
            if (currNode == null) {
                return new Node<E>(data);
            } else if (data.compareTo(currNode.data) < 0) {
            //newnode points CurrentPoint
                Node<E> newNode = new Node<E>(data);
                newNode.next = currNode;
                return newNode;
            } else {
                currNode.next = recursiveInsert(currNode.next, data);
                return currNode;
            }
        }
        public void remove(E data) {
            recursiveremove(head, data);
        }
        private Node<E> recursiveremove(Node<E> currNode, E data) {
            //if it doesnt exist, return null
            if (currNode == null) {
                return null;
            }
            if (data.equals(currNode.data)) {
                return currNode.next;
            } else {
                currNode.next = recursiveremove(currNode.next, data);
                return currNode;
            }
        }


        //retrieve gives node from index
        private E recursiveretrieve(int index, Node<E> currNode) {
            if (currNode == null) {
                return null;
            }
            if (index == 0) {
                return currNode.data;
            }
            return recursiveretrieve(index - 1, currNode.next);
        }

        public E retrieve(int index) {
            return recursiveretrieve(index - 1, head);
        }
        public boolean search(E data, Node<E> currNode) {
            if (currNode == null) {
                return false;
            }
            if (currNode.data.equals(data)) {
                return true;
            }
            return search(data, currNode.next);
        }

        public boolean search(E data) {
            return search(data, head);
        }
        private class SortedListIterator implements Iterator<E> {
            private Node<E> current = head;
            public boolean hasNext() {
                return current != null;
            }
            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        }
        @Override
        public Iterator<E> iterator() {
            return new SortedListIterator();
        }

    }


