package com.bdyshare.adt;
/**
 * a realization of heap
 * @author huangbin
 *
 * @param <T>
 */
@SuppressWarnings({"unchecked"})
public class BinaryHeap<T extends Comparable<? super T>> {
	
	public BinaryHeap() {
		array = (T[]) new Comparable[DEFAULT_CAPACITY];
	}
	
	public BinaryHeap(int capacity) {
		array = (T[]) new Comparable[capacity];
	}
	
	public BinaryHeap(T[] items) {
		if(items == null || items.length == 0) {
			array = (T[]) new Comparable[DEFAULT_CAPACITY];
		} else {
			//remember to set the currentSize as the length of input array
			currentSize = items.length;
			array = (T[]) new Comparable[items.length * 2];
			int i = 1;
			for(T ele : items) {
				array[i++] = ele;
			}
			buildHeap();
		}
	}
	
	
	
	public void insert(T ele) {
		if(currentSize == array.length-1) {
			enlargeArray(array.length * 2);
		}
		int hole = ++ currentSize;
		for(array[0] = ele; ele.compareTo(array[hole/2])<0; hole = hole/2) {
			array[hole] = array[hole/2];
		}
		array[hole] = ele;
	}
	
	public T deleteMin() {
		if(isEmpty()) {
			throw new IllegalStateException("the heap is empty");
		}
		T min = array[1];
		array[1] = array[currentSize--];
		percolateDown(1);
		return min;
	}
	
	
	private boolean isEmpty() {
		return currentSize == 0;
	}
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int currentSize;
	private T[] array;
	
	
	public int size() {
		return currentSize;
	}
	
	
	private void percolateDown(int hole) {
		int child = 0;
		T tmp = array[hole];
		for( ; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if(child != currentSize && array[child+1].compareTo(array[child]) <0) {
				child ++;
			}
			if(array[child].compareTo(tmp) < 0) {
				array[hole] = array[child];
			} else {
				break;
			}
		}
		array[hole] = tmp;
	}
	
	private void buildHeap() {
		for(int i= currentSize/2; i>0; i--) {
			percolateDown(i);
		}
	}
	
	private void enlargeArray(int newSize) {
		if(newSize <= array.length) {
			throw new IllegalArgumentException(newSize +" is smaller than the original size");
		}
		T[] oldArr = array;
		T[] newArr = (T[]) new Comparable[newSize];
		System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
		array = newArr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
