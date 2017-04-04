package com.bdyshare.adt;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/**
 * a simple util for sorting, including bubble sorting, insertion sorting, shell sorting, merge sorting, quick sorting and so on...
 * @author huangbin
 *
 */
public class SortUtils {
	
	
	/**
	 * bubble sort
	 * @param array
	 */
	public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
		checkArrayLength(array);
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length-1; j > i; j--) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					T tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
				}
			}
		}
	}
	
	
	/**
	 * insertion sort
	 * @param array
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
		checkArrayLength(array);
		insertionSort(array, 0, array.length-1);
	}
	private static <T extends Comparable<? super T>> void insertionSort(T[] array, int left, int right) {
		for (int i = left+1; i <=right; i++) {
			int j;
			T current = array[i];
			for (j = i; j > 0 && current.compareTo(array[j - 1]) < 0; j--) {
				array[j] = array[j - 1];
			}
			array[j] = current;
		}
	}
	
	
	/**
	 * shell sort
	 * @param array
	 */
	public static <T extends Comparable<? super T>> void shellSort(T[] array) {
		checkArrayLength(array);
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				T tmp = array[i];
				int j;
				for (j = i; j >= gap && tmp.compareTo(array[j - gap]) < 0; j -= gap) {
					array[j] = array[j - gap];
				}
				array[j] = tmp;
			}
		}
	}
	
	
	
	/**
	 * heap sort
	 * @param array
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] array) {
		buildHeap(array);
		for(int i=array.length-1; i > 0; i--) {
			swapReference(array, 0, i);
			percolateDown(array, 0, i);
		}
	}
	private static int leftChild(int i) {
		return i * 2 + 1;
	}
	private static <T extends Comparable<? super T>> void percolateDown(
			T[] array, int h, int n) {
		T tmp;
		int child;
		for (tmp = array[h]; leftChild(h) < n; h = child) {
			child = leftChild(h);
			//as the child is already the left child, so here we judge child != n-1
			if (child != n-1
					&& array[child].compareTo(array[child + 1]) < 0) {
				child++;
			}
			if (tmp.compareTo(array[child]) < 0) {
				array[h] = array[child];
			} else {
				break;
			}
		}
		array[h] = tmp;
	}
	private static <T extends Comparable<? super T>> void buildHeap(T[] array) {
		for(int i = array.length/2-1; i>=0; i--) {
			percolateDown(array, i, array.length);
		}
	}
	private static <T extends Comparable<? super T>> void swapReference(T[] array, int a, int b) {
		T tmp = array[b];
		array[b] = array[a];
		array[a] = tmp;
	}
 	
	
	
	
	
	
	
	/**
	 * merge sort
	 * @param a
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[a.length];
		mergeSort(a, tmp, 0, a.length-1);
	}
	private static <T extends Comparable<? super T>> void mergeSort(T[] a,
			T[] tmp, int left, int right) {
		if(left < right) {
			int center = (left+right)/2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center+1, right);
			merge(a, tmp, left, center+1, right);
		}

	}
	private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp,
			int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			if(a[leftPos].compareTo(a[rightPos]) <= 0) {
				tmp[tmpPos++] = a[leftPos++];
			} else {
				tmp[tmpPos++] = a[rightPos++];
			}
		}
		while(leftPos <= leftEnd) {
			tmp[tmpPos++] = a[leftPos++];
		}
		while(rightPos <= rightEnd) {
			tmp[tmpPos++] = a[rightPos++];
		}
		for(int i=0; i< numElements; i++, rightEnd--) {
			a[rightEnd] = tmp[rightEnd];
		}

	}
	
	
	
	/**
	 * quick sort
	 * @param array
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] array) {
		checkArrayLength(array);
		quickSort(array, 0, array.length-1);
	}
	
	private static final int CUTOFF = 5;
	private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right) {
		if(left+CUTOFF <= right) {
			T pivot = decidePivot(a, left, right);
			int i = left;
			int j = right-1;
			while(true) {
				while(a[++i].compareTo(pivot) < 0) {}
				while(a[--j].compareTo(pivot) > 0) {}
				if(i < j) {
					swapReference(a, i, j);
				} else {
					break;
				}
			}
			swapReference(a, i, right-1);
			
			quickSort(a, left, i-1);
			quickSort(a, i+1, right);
		} else {
			insertSort(a, left, right);
		}
	}
	private static <T extends Comparable<? super T>> void insertSort(T[] a, int left, int right) {
		for(int i= left+1; i<= right; i++) {
			T tmp;
			int j =i;
			for(tmp = a[i]; j>left; j--) {
				if(tmp.compareTo(a[j-1]) < 0) {
					a[j] = a[j-1];
				} else {
					break;
				}
			}
			a[j] = tmp;
		}
	}
	
	private static <T extends Comparable<? super T>> T decidePivot(T[] a, int left, int right) {
		int center = (left+right)/2;
		if(a[center].compareTo(a[left]) < 0) {
			swapReference(a, left, center);
		}
		if(a[right].compareTo(a[left]) < 0) {
			swapReference(a, left, right);
		}
		if(a[right].compareTo(a[center]) < 0) {
			swapReference(a, right, center);
		}
		swapReference(a, center, right-1);
		return a[right-1];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * common method to make sure the legality of array length
	 * @param array
	 */
	private static <T extends Comparable<? super T>> void  checkArrayLength(T[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
			return;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		Integer[] array = new Integer[30];
		Random rand = new Random();
		for(int i=0; i< 30; i++) {
			array[i] = rand.nextInt(10000);
		}
		System.out.println(Arrays.toString(array));
		//SortUtils.bubbleSort(array);
		//SortUtils.insertionSort(array);
		//SortUtils.shellSort(array);
		//SortUtils.mergeSort(array);
		SortUtils.quickSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	

}
