package validbst;


/**
 * https://oj.leetcode.com/problems/validate-binary-search-tree/
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees
 * 
 * My first solution involved keeping track of the complete path from the root
 * which is unnecessary since all is needed is the min and max allowable values
 * for a given node, defined by its ancestry as follows:
 * - for the left child: the max is the immediate parent's value, the min is the same than the parent
 *  -for the right child: the max is the same than the parent's max, the min is the immediate parent's value
 */
public class Solution {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public boolean isValidBST(TreeNode root) {
		// initialize recursion
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
	
	// each node in a BST has a min and max which depend on the node's ancestry
	private boolean isValidBST(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		
		if (node.val <= min || node.val >= max) {
			return false;
		}
		
		return isValidBST(node.left,  min ,node.val)
			&& isValidBST(node.right, node.val, max);
	}
}
