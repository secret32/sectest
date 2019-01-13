package org.sec.core.datastructure;

public class SecStack {
	
	private Object[] value;
	
	private volatile int size;
	
	private final int FULLSIZE = 65535;
	
	private int init = 10;
	private int maxFree = 10;
	
	public SecStack() {
		this.value = new Object[init];
		this.size = 0;
	}
	
	private synchronized void checkBound() {
		if (size == FULLSIZE)
			throw new IllegalArgumentException("stack is full");
		if (size == value.length || size < value.length / 20) {
			int newLen = (size + maxFree) >= FULLSIZE ? FULLSIZE : (size + maxFree);
			Object[] newValue = new Object[newLen];
			System.arraycopy(value, 0, newValue, 0, size);
			value = newValue;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void push(Object o) {
		checkBound();
		synchronized (value) {
			value[size++] = o;
		}
	}
	
	public void pop() {
		if (size == 0) 
			throw new IllegalArgumentException("stack is empty");
		synchronized (value) {
			value[--size] = null;
		}
	}
	
	public Object peek() {
		if (size == 0) 
			throw new IllegalArgumentException("stack is empty");
		synchronized (value) {
			return value[size - 1];
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		synchronized (value) {
			for (int i = 0; i < size; i++) {
				if (i > 0) sb.append(", ");
				sb.append(value[i]);
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}
