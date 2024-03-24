public class MyQueue<E> extends MyLinkedList<E> {
	
	
	public void offer(E item) {
		addLast(item);
	}
	
	public E poll() {
		return removeFirst();
	}

}
