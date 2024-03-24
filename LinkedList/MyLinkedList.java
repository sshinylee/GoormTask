import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

	private int size = 0;
	private Node<E> head = null;
	private Node<E> tail = null;
	
	private static class Node<E> {
		E item;
		Node<E> prev, next;
		
		public Node(E item, Node<E> prev, Node<E> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void add(E item) {
		addLast(item);
	}
	
	public void addFirst(E item) {
		if(isEmpty()) {
			head = tail = new Node<E>(item, null, null);
		} else {
			head.prev = new Node<E>(item, null, head);
			head = head.prev;
		}
		size++;
	}
	
	public void addLast(E item) {
		if(isEmpty()) {
			head = tail = new Node<E>(item, null, null);
		} else {
			tail.next = new Node<E>(item, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	public E removeFirst() {
		
		if(isEmpty()) throw new NoSuchElementException();
		
		E item = head.item;
		Node<E> next = head.next;
		
		head.item = null;
		head.next = null;
		head = next;
		if(next == null)
			tail = null;
		else
			next.prev = null;
		size--;
		return item;
	}
	
	public E removeLast() {
		
		if(isEmpty()) throw new NoSuchElementException();
		
		E item = tail.item;
		Node<E> prev = tail.prev;
		
		tail.item = null;
		tail.prev = null;
		tail = prev;
		if(prev == null)
			head = null;
		else
			prev.next = null;
		size--;
		return item;
	}
	
	
	
	
	private E unlink(Node<E> node) {
		
		E elem = node.item;
		Node<E> next = node.next;
		Node<E> prev = node.prev;
		
		if(prev == null) {
			head = next;
		} else {
			prev.next = next;
			node.prev = null;
		}
		
		if(next == null) {
			tail = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}
		
		node.item = null;
		size--;
		return elem;
		
	}
	
	public E delete(int index) {
		
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		
		int i;
		Node<E> trav;
		
		if(index < size / 2) {
			for(i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		} else {
			for(i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		
		return unlink(trav);
		
	}
	
	public E get(int index) {
		
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		
		int i;
		Node<E> trav;
		
		if(index < size / 2) {
			for(i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		} else {
			for(i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		
		return trav.item;
	}
	
	
	
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			
			private Node<E> trav = head;
			
			@Override
			public boolean hasNext() {
				return trav != null;
			}
			
			@Override
			public E next() {
				E item = trav.item;
				trav = trav.next;
				return item;
			}
		};
	}
	
	

}
