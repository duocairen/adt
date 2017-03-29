package com.bdyshare.adt.test;

import com.bdyshare.adt.AVLTree;

public class TestAVLTree {
	
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		for(int i=0; i<100; i++) {
			tree.insert(i);
		}
		System.out.println("min:"+tree.findMin());
		System.out.println("max:"+tree.findMax());
		System.out.println("contains 1:"+tree.contains(1));
		for(int i=0; i<50; i++) {
			tree.remove(i);
		}
		tree.printTree();
	}

}
