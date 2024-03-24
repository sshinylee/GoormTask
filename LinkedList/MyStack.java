public class MyStack<E> extends MyLinkedList<E>{
	
	public void push(E item) {
		addFirst(item);
	}
	
	public E pop() {
		return removeFirst();
	}
	
}
