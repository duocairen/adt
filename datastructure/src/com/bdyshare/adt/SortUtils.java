package com.bdyshare.adt;

import java.util.Arrays;
import java.util.Random;

/**
 * a simple util for sorting, including bubble sorting, insertion sorting, 
 * @author huangbin
 *
 */
public class SortUtils {
	
	
	public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
		checkArrayLength(array);
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length; j > i; j--) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					T tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
				}
			}
		}
	}
	
	
	public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
		checkArrayLength(array);
		for (int i = 1; i < array.length; i++) {
			int j;
			T current = array[i];
			for (j = i; j > 0 && current.compareTo(array[j - 1]) < 0; j--) {
				array[j] = array[j - 1];
			}
			array[j] = current;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static <T extends Comparable<? super T>> void  checkArrayLength(T[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
			return;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		Integer[] intArr = new Integer[20];
		Random rand = new Random();
		for(int i=0; i< 20; i++) {
			intArr[i] = rand.nextInt(10000);
		}
		SortUtils.bubbleSort(intArr);
		System.out.println(intArr);
	}
	
	

}
