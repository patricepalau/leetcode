package recoverbst;

import org.junit.Test;

import recoverbst.Solution.TreeNode;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		TreeNode n8 = new TreeNode(8);
		//
		//			 6
		//		3		 10
		//	   1  8        14 
		//       4 7      13
		//
		TreeNode n3 = new TreeNode(3);
		TreeNode n10 = new TreeNode(10);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		TreeNode n4 = new TreeNode(4);
		TreeNode n7 = new TreeNode(7);
		TreeNode n13 = new TreeNode(13);
		TreeNode n14 = new TreeNode(14);
		n6.left = n3;
		n6.right = n10;
		
		n3.left = n1;
		n3.right = n8;
		n8.left = n4;
		n8.right = n7;
		
		n10.right = n14;
		n14.left = n13;
		
		
		//solution.recoverTree(n6);
		solution.recoverTree(n6);
		
		System.out.println(n6);
	}
	
	//   3			1
	//     2		  2
	//       1			3
	@Test
	public void test2() {
		TreeNode n3 = new TreeNode(3);
		TreeNode n2 = new TreeNode(2);
		TreeNode n1 = new TreeNode(1);
		
		n3.right = n2;
		n2.right = n1;
		Solution solution = new Solution();
		solution.recoverTree(n3);
	}
	
	//   2			1
	//      3		   3
	//     1		  2
	@Test
	public void test3() {
		TreeNode n3 = new TreeNode(3);
		TreeNode n2 = new TreeNode(2);
		TreeNode n1 = new TreeNode(1);
		
		n2.right = n3;
		n3.left  = n1;
		Solution solution = new Solution();
		solution.recoverTree(n2);
	}
}
