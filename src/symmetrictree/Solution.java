package symmetrictree;

import java.util.ArrayList;
import java.util.List;

import common.TreeNode;

/**
 * https://oj.leetcode.com/problems/symmetric-tree/
 * 
 *  
 */
public class Solution {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		List<TreeNode> nodesLeft  = new ArrayList<TreeNode>();
		List<TreeNode> nodesRight = new ArrayList<TreeNode>();
		traverseLeft(nodesLeft, root.left);
		traverseRight(nodesRight, root.right);
		return areEquals(nodesLeft, nodesRight);
    }
	
	private void traverseLeft(List<TreeNode> nodes, TreeNode node) {
		nodes.add(node);
		if (node == null) return;
		traverseLeft(nodes, node.left);
		traverseLeft(nodes, node.right);
	}

	private void traverseRight(List<TreeNode> nodes, TreeNode node) {
		nodes.add(node);
		if (node == null) return;
		traverseRight(nodes, node.right);
		traverseRight(nodes, node.left);
	}
	
	private boolean areEquals(List<TreeNode> list1, List<TreeNode> list2) {
		if (list1 == null) {
			return list2 == null;
		}
		
		if (list2 == null) return false;
		
		if (list1.size() != list2.size()) return false;
		
		for (int i = 0; i < list1.size(); i++) {
			TreeNode node1 = list1.get(i);
			TreeNode node2 = list2.get(i);
			if (node1 != null && node2 != null) {
				if (node1.val != node2.val) {
					return false;
				}
			}
			else {
				if (!(node1 == null && node2 == null)) {
					return false;
				}
			}
		}
		
		return true;
	}
}
