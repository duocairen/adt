package com.bdyshare.adt.test;

import com.bdyshare.adt.BSTree;

public class TestBSTree {
	
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>();
		for(int i=0; i<100; i++) {
			tree.insert(i);
		}
		tree.insert(6666);
		System.out.println("max:"+tree.findMax());
		System.out.println("min:"+tree.findMin());
        System.out.println("contains 7777："+tree.contains(7777));
        tree.remove(6666);
        tree.printTree();
	}
	

}
