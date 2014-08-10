package validbst;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * https://oj.leetcode.com/problems/validate-binary-search-tree/
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees
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
	
	public boolean isValidBST(TreeNode root) {
		List<TreeNode> path = new ArrayList<TreeNode>();
		return isValidBST(root, path);
    }
	
	private boolean isValidBST(TreeNode node, List<TreeNode> path) {
		if (node == null) return true;
		
		boolean isValid = true;
		
		int val = node.val;
		
		ListIterator<TreeNode> lit = path.listIterator(path.size());
		TreeNode current = node;
		while (lit.hasPrevious()) {
			TreeNode parent = lit.previous();
			if (current == parent.right) {
				if (val <= parent.val) {
					isValid = false;
				}
			}
			else {
				if (val >= parent.val) {
					System.out.println("invalid: " + node);
					isValid = false;
				}
			}
			current = parent;
			if (!isValid) break;
		}
		
		if (isValid) {
			path.add(node);
			isValid  = isValidBST(node.left, path);
			isValid = isValid && isValidBST(node.right, path);
			path.remove(node);
		}
		
		return isValid;
	}
}
