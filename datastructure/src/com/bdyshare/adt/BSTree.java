package com.bdyshare.adt;

/**
 * a common binary search tree
 * @author hank
 *
 * @param <T>
 */
public class BSTree<T extends Comparable<? super T>> {
	
	private Node<T> root;
	
	

	
	public void makeEmpty() {
		root = null;
	}
	
	
	public void insert(T ele) {
		root = insert(ele, root);
	}
	private Node<T> insert(T ele, Node<T> node) {
		if(ele == null) {
			return node;
		}
		//the end condition of recursive
		if(node == null) {
			return new Node<T>(ele);
		}
		int comp = ele.compareTo(node.ele);
		if(comp > 0) {
			node.right = insert(ele, node.right);
		} else if(comp < 0) {
			node.left = insert(ele, node.left);
		} else {
			//duplicate, do nothing
		}
		return node;
	}
	
	
	
	public void remove(T ele) {
		//remember to set the root with the return Node
		root = remove(ele, root);
	}
	private Node<T> remove(T ele, Node<T> node) {
		if(ele == null || node == null) {
			return node;
		}
		int comp = ele.compareTo(node.ele);
		if(comp > 0) {
			node.right = remove(ele, node.right);
		} else if(comp < 0) {
			node.left = remove(ele, node.left);
		} else {
			//ele is equal with the elements of current node
			if(node.left != null && node.right != null) {
				node.ele = findMin(node.right);
				node.right = remove(node.ele, node.right);
			} else {
				node = node.left != null ? node.left : node.right;
			}
		}
		return node;
	}
	
	
	public T findMax() {
		return findMax(root);
	}
	private T findMax(Node<T> node) {
		if(node == null) {
			return null;
		}
		while(node.right != null) {
			node = node.right;
		}
		return node.ele;
	}
	
	
	public T findMin() {
		return findMin(root);
	}
	private T findMin(Node<T> node) {
		if(node == null) {
			return null;
		}
		if(node.left != null) {
			return findMin(node.left);
		}
		return node.ele;
	}
	
	
	
	public boolean contains(T ele) {
		return contains(ele, root);
	}
	private boolean contains(T ele, Node<T> node) {
		if(ele == null || node == null) {
			return false;
		}
		int comp = ele.compareTo(node.ele);
		if(comp == 0) {
			return true;
		} else if(comp > 0) {
			return contains(ele, node.right);
		} else {
			return contains(ele, node.left);
		}
	}
	
	
	public void printTree() {
		printTree(root);
	}
	private void printTree(Node<T> node) {
		if(node == null){
			return;
		}
		printTree(node.left);
		System.out.println(node.ele);
		printTree(node.right);
	}
	

	
	
	private static class Node<T> {
		T ele;
		Node<T> left;
		Node<T> right;
		
		public Node(T ele, Node<T> left, Node<T> right) {
			this.ele = ele;
			this.left = left;
			this.right = right;
		}
		
		public Node(T ele) {
			this(ele, null, null);
		}
	}
	
	
	
	
	


}
