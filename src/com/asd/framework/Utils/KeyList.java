package com.asd.framework.Utils;

import java.util.HashMap;
import java.util.Iterator;

public class KeyList<T> implements Iterable<T> {

	HashMap<Long, T> data;

	private class KeyIterator implements Iterator<T> {
		@Override
		public boolean hasNext() {
			return data.entrySet().iterator().hasNext();
		}

		@Override
		public T next() {
			return data.entrySet().iterator().next().getValue();
		}
	}

	public KeyList() {
		data = new HashMap<Long, T>();
	}

	@Override
	public Iterator<T> iterator() {
		return new KeyIterator();
	}

	public T get(Long i) {
		return data.get(i);
	}

	public boolean add(Long Id, T item) {
		data.put(Id, item);
		return true;
	}

	public boolean set(Long Id, T item) {
		return data.replace(Id, item) != null;
	}

	public boolean remove(Long Id) {
		return data.remove(Id) != null;
	}

	public boolean isEmpty() {
		return data.size() <= 0;
	}

	public int size() {
		return data.size();
	}

}