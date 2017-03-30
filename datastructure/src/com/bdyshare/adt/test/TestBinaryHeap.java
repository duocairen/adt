package com.bdyshare.adt.test;

import java.util.Random;

import com.bdyshare.adt.BinaryHeap;

public class TestBinaryHeap {
	
	public static void main(String[] args) {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		Random rand = new Random();
		for(int i=0; i<100; i++) {
			heap.insert(rand.nextInt(10000));
		}
		System.out.println("heap size:" + heap.size());
		for(int i=0; i<101; i++) {
			System.out.println(heap.deleteMin());
		}
		System.out.println("heap size:"+ heap.size());
	}

}
