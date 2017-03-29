package com.bdyshare.adt;
/**
 * 自平衡的二叉搜索树
 * @author hank
 *
 * @param <T>
 */
public class AVLTree<T extends Comparable<? super T>> {

	private Node<T> root;
	
	
	
	public void remove(T ele) {
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
			if(node.left != null && node.right != null) {
				node.ele = findMin(node.right);
				node.right = remove(node.ele, node.right);
			} else {
				node = node.left != null ? node.left : node.right;
			}
		}
		return balance(node);
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
		while(node.left != null) {
			node = node.left;
		}
		return node.ele;
	}
	
	
	public void insert(T ele) {
		root = insert(ele, root);
	}
	private Node<T> insert(T ele, Node<T> node) {
		if(ele == null) {
			return node;
		}
		if(node == null) {
			return new Node<T>(ele);
		}
		int comp = ele.compareTo(node.ele);
		if(comp > 0) {
			node.right = insert(ele, node.right);
		} else if(comp < 0) {
			node.left = insert(ele, node.left);
		} else {
			//duplicate element, do nothing
		}
		return balance(node);
	}
	
	
	
	private static final int ALLOW_IMBALANCE = 1;
	
	private Node<T> balance(Node<T> node) {
		if(node == null) {
			return node;
		}
		if(height(node.left) - height(node.right) > ALLOW_IMBALANCE) {
			if(height(node.left.left) >= height(node.left.right)) {
				node = rotateWithLeftChild(node);
			} else {
				node = doubleWithLeftChild(node);
			}
		} else if(height(node.right) - height(node.left) > ALLOW_IMBALANCE) {
			if(height(node.right.right) >= height(node.right.left)) {
				node = rotateWithRightChild(node);
			} else {
				node = doubleWithRightChild(node);
			}
		}
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
	}
	
	
	
	
	private Node<T> rotateWithLeftChild(Node<T> node) {
		Node<T> newRoot = node.left;
		node.left = newRoot.right;
		newRoot.right = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		return newRoot;
	}
	
	
	private Node<T> rotateWithRightChild(Node<T> node) {
		Node<T> newRoot = node.right;
		node.right = newRoot.left;
		newRoot.left = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newRoot.height = Math.max(height(node.left), height(node.right)) + 1;
		return newRoot;
	}
	
	private Node<T> doubleWithLeftChild(Node<T> node) {
		node.left =  rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}
	
	
	
	private Node<T> doubleWithRightChild(Node<T> node) {
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}
	
	

	private int height(Node<T> node) {
		return node==null ? -1 : node.height;
	}
	
	
	
	public void printTree() {
		printTree(root);
	}
	private void printTree(Node<T> node) {
		if(node == null) {
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
		int height;
		
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
