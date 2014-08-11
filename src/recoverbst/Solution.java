package recoverbst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * 
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * This is the O(n) solution.
 * 
 * 
 */
public class Solution {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		public String toString() {
			
			return "node: " + val + " (l:"
					+ (left != null ? left.val : "null")
					+ ", r:"
					+ (right != null ? right.val : "null")
					+ ")";
		
		}
	}
	
	// O(1) space solution
	// see threaded binary trees
	// 
	// given any node N:
	// - if the left subtree, the largest node is the rightmost node
	// - this rightmost node should point back at N
	// 
	//
	public void recoverTree(TreeNode root) {
		// traversal in O(1) space
		
		TreeNode current   = root;
		
		while (current != null) {
			//System.out.println(current);
			
			if (current.left == null) {
				// we found a leftmost node - process it
				System.out.println("leftmost: " + current);
				process(current);
				// and continue to its right
				// at this point, we might be following a thread back
				current = current.right;				
			}
			else {
				TreeNode left      = current.left;
				TreeNode rightmost = left;
				// determine the rightmost node in the left subtree
				// (the rightmost node contains the largest value in the left subtree)
				// however we need to make sure we don't get in an infinite loop:
				// we know that if rightmost gets back to current
				// so instead of rightmost.right != null
				// we use the following condition
				while (rightmost.right != null && rightmost.right != current) {
					rightmost = rightmost.right;
				}
				
				// so we found the rightmost node which is not current
				// now 2 possibilities:
				// - either rightmost has no right child
				if (rightmost.right == null) {
					// and we need to assign current
					// the rightmost node points back at current i.e. its in-order successor
					rightmost.right = current;
					// continue left
					current = current.left;
				}
				// - or it has a right child (current)
				else {
					// we followed a thread back to this node:
					// print it
					System.out.println("right: " + current);
					process(current);
					
					// clean up behind us
					rightmost.right = null;
					
					// and move to the right
					// since left has already been visited
					current = current.right;
				}
			}
		}
		
		swap(max, min);
	}
	
	// O(n) space solution
	// -------------------
	// will contain the largest misplaced node
	private TreeNode max = null;
	// will contain the smallest misplaced node
	private TreeNode min = null;

	public void recoverTreeRec(TreeNode root) {
		// traverse the tree to spot the 2 misplaced nodes
    	traverseRec(root);
    	// swap them
    	swap(max, min);
    }
	
	// in-order recursive traversal
	private void traverseRec(TreeNode node) {
		if (node == null) return;
		traverseRec(node.left);
		process(node);
		traverseRec(node.right);
	}
	
	// each node must be larger than its predecessor in-order
	// once we found the first misplaced node, we then search for
	// the smallest node in the remaining of the tree
	private TreeNode prev = null;
	
	private void process(TreeNode node) {
		if (prev == null) {
			// first node in order: just initialize prev
			prev = node;
			return;
		}
		
		// if max is set then we're looking for the smallest node
		if (max != null) {
			if (node.val < min.val) min = node;
		}
		else if (node.val < prev.val) {
			// otherwise if we found a node smaller than its predecessor
			// = prev is out of place
			max = prev;
			// start looking for the min starting at node
			min = node;
		}
		
		prev = node;		
	}
	
	// swaps 2 nodes
    private void swap(TreeNode parent, TreeNode node) {
    	int val1 = parent.val;
    	parent.val = node.val;
    	node.val   = val1;
    }
}
