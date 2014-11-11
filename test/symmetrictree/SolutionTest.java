package symmetrictree;

import org.junit.Assert;
import org.junit.Test;

import common.TreeNode;

public class SolutionTest {
	@Test
	public void test1() {
		TreeNode node = new TreeNode(1);
		Solution solution = new Solution();
		Assert.assertTrue(solution.isSymmetric(node));
	}
	
	@Test
	public void test2() {
		TreeNode node = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		node.left = left;
		node.right = right;
		Solution solution = new Solution();
		Assert.assertTrue(solution.isSymmetric(node));
	}
	
	@Test
	public void test3() {
		TreeNode node = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		node.left = left;
		node.right = right;
		TreeNode leftleft = null;
		TreeNode leftright = new TreeNode(3);
		node.left.left = leftleft;
		node.left.right = leftright;
		TreeNode rightleft = null;
		TreeNode rightright = new TreeNode(3);
		node.right.left = rightleft;
		node.right.right = rightright;
		Solution solution = new Solution();
		Assert.assertFalse(solution.isSymmetric(node));
	}
}
