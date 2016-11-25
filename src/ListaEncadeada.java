public class ListaEncadeada<T> implements Iterable<T> {
	private Node head;
	private Node tail;

	public Node getHead() {
		return head;
	}
	
	public Node getTail() {
		return tail;
	}
	
	void append(T value) {
		Node novo = new Node(value);
		
		if (tail != null) {
			novo.previous = tail;
			tail.next = novo;
		} else {
			head = novo;
		}
		
		tail = novo;
	}
	
	void append(T value, Node downLevel) {
		Node novo = new Node(value);
		
		if (tail != null) {
			novo.previous = tail;
			tail.next = novo;
		} else {
			head = novo;
		}
		
		novo.downLevel = downLevel;
		
		tail = novo;
	}

	void pushFront(T value) {
		Node novo = new Node(value);
		novo.next = head;
		
		if (head == null)
			tail = novo;
		else
			head.previous = novo;
		
		head = novo;
	}
	
	public Iterador<T> iterator() {
		return new ListIterator();
	}
	
	protected class Node {
		private T data;
		private Node downLevel;
		private Node previous;
		private Node next;
		
		public Node(T value) {
			data = value;
		}
		
		public Node getDownLevel() {
			return downLevel;
		}
		
		public Node getNext() {
			return next;
		}
		
		public Node getPrevious() {
			return previous;
		}
		
		public T getData() {
			return data;
		}
	}
	
	private class ListIterator implements Iterador<T> {
		private Node current = null;
		private Node previous = null;
		
		public Node getCurrent() {
			return current;
		}
		
		@Override
		public boolean hasNext() {
			if (current == null)
				return head != null;
			return current.next != null;
		}
		
		@Override
		public T next() {
			if (!hasNext())
				throw new IllegalStateException("Sem next!");
			
			if (current == null) {
				current = head;
			} else {
				previous = current;
				current = current.next;
			}
			
			return current.data;
		}
		
		@Override
		public void remove() {
			if (current == null)
				throw new IllegalStateException("Use next()!");
			
			Node next = current.next;
			
			if (previous == null) {
				head = next;
			} else {
				previous.next = next;
			}
			
			if (next == null) {
				tail = previous;
			} else {
				next.previous = previous;
			}
		}
		
		@Override
		public void append(T dado) {
			if (current == null)
				throw new IllegalStateException("Use next()!");
			
			Node node = new Node(dado);
			Node next = current.next;
			
			node.next = next;
			node.previous = current;
			
			current.next = node;
			
			if (current == tail)
				tail = node;
		}
		
		@Override
		public void append(T dado, Node downLevel) {
			if (current == null)
				throw new IllegalStateException("Use next()!");
			
			Node node = new Node(dado);
			Node next = current.next;
			
			node.next = next;
			node.previous = current;
			
			current.next = node;
			current.downLevel = downLevel;
			
			if (current == tail)
				tail = node;
		}
		
		@Override
		public void insert(T dado) {
			if (current == null)
				throw new IllegalStateException("Use next()!");
			
			Node node = new Node(dado);
			
			node.next = current;
			current.previous = node;
			
			if (previous != null) {
				node.previous = previous;
				previous.next = node;
			}
			else
				head = node;
		}
	}
}