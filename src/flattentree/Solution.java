package flattentree;

/**
 * https://oj.leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * A node is flattened by moving its left node to the right
 * and connecting the left node's rightmost node to its right node
 * 
 * We move recursively to the left, then flatten the right node
 * 
 */
public class Solution {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		public String toString() {return String.valueOf(val);}
	}
	
	private TreeNode traverseRight(TreeNode node) {
		if (node == null) return null;
		if (node.right == null) return node;
		return traverseRight(node.right);
	}
	
	public void flatten(TreeNode root) {
		if (root == null) return;
		
		// remember the original left and right nodes
		TreeNode left = root.left;
		TreeNode right = root.right;
		
		if (left != null) {
			// flatten node.left
			flatten(root.left);
			
			// retrieve the rightmost node of left
			TreeNode rightmost = traverseRight(left);
			// move left node to right node
			root.left = null;
			root.right = left;
			// point left's rightmost node to node's previous right node
			rightmost.right = right;
		}
		
		flatten(right);
	}
}
