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
	
	// will contain the largest misplaced node
	private TreeNode max = null;
	// will contain the smallest misplaced node
	private TreeNode min = null;
	
	public void recoverTree(TreeNode root) {
		// traverse the tree to spot the 2 misplaced nodes
    	traverse(root);
    	// swap them
    	swap(max, min);
    }
	
	// in-order traversal
	private void traverse(TreeNode node) {
		if (node == null) return;
		traverse(node.left);
		process(node);
		traverse(node.right);
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
