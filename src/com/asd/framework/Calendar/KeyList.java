package com.asd.framework.Calendar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KeyList<T> implements Iterable<T> {

	HashMap<Long, T> data;
	Iterator<Map.Entry<Long, T>> iterator;

	private class KeyIterator implements Iterator<T> {
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next().getValue();
		}
	}

	public KeyList() {
		data = new HashMap<Long, T>();
	}

	@Override
	public Iterator<T> iterator() {
		iterator = data.entrySet().iterator();
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

	public T remove(Long Id) {
		return data.remove(Id);
	}

	public boolean isEmpty() {
		return data.size() <= 0;
	}

	public int size() {
		return data.size();
	}

}