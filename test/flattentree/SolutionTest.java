package flattentree;

import org.junit.Assert;
import org.junit.Test;

import flattentree.Solution.TreeNode;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		root.left = n2;
		root.right = n5;
		n2.left = n3;
		n2.right = n4;
		n5.right = n6;
		solution.flatten(root);
		Assert.assertNull(root.left);
		Assert.assertNull(n2.left);
		Assert.assertNull(n3.left);
		Assert.assertNull(n4.left);
		Assert.assertNull(n5.left);
		Assert.assertNull(n6.left);
		Assert.assertSame(n2, root.right);
		Assert.assertSame(n3, n2.right);
		Assert.assertSame(n4, n3.right);
		Assert.assertSame(n5, n4.right);
		Assert.assertSame(n6, n5.right);	
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		root.left = n2;
		root.right = n5;
		n2.left = n3;
		n2.right = n4;
		n5.left = n6;
		n5.right = n7;
		solution.flatten(root);
		Assert.assertNull(root.left);
		Assert.assertNull(n2.left);
		Assert.assertNull(n3.left);
		Assert.assertNull(n4.left);
		Assert.assertNull(n5.left);
		Assert.assertNull(n6.left);
		Assert.assertSame(n2, root.right);
		Assert.assertSame(n3, n2.right);
		Assert.assertSame(n4, n3.right);
		Assert.assertSame(n5, n4.right);
		Assert.assertSame(n6, n5.right);
		Assert.assertSame(n7, n6.right);		
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		
		root.left = null;
		root.right = n2;
		n2.left = n3;
		n2.right = null;
		
		solution.flatten(root);
		Assert.assertNull(root.left);
		Assert.assertNull(n2.left);
		Assert.assertNull(n3.left);
		
		Assert.assertSame(n2, root.right);
		Assert.assertSame(n3, n2.right);
			
	}
}
