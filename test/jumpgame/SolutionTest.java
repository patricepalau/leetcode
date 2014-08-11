package jumpgame;

import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] A = {0};
		Assert.assertTrue(solution.canJump(A));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] A = {2,3,0,1,4};
		Assert.assertTrue(solution.canJump(A));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		int[] A = {1};
		Assert.assertTrue(solution.canJump(A));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		//int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0};
		int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5};
		System.out.println("solving problem of size: " + A.length);
		boolean canjump = solution.canJump(A);
//		System.out.println("count: " + solution.count);
//		System.out.println("tgp: " + solution.tgp);
//		System.out.println("tis: " + solution.tis);
		Assert.assertFalse(canjump);
	}
	
	
	@Test(timeout=5000)
	public void test5() {
		Solution solution = new Solution();
		int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4};
		System.out.println("solving problem of size: " + A.length);
		boolean canjump = solution.canJump(A);
//		System.out.println("count: " + solution.count);
//		System.out.println("tgp: " + solution.tgp);
//		System.out.println("tis: " + solution.tis);
		Assert.assertFalse(canjump);
	}
	
	@Test(timeout=5000)
	public void test6() {
		Solution solution = new Solution();
		int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0};
		System.out.println("solving problem of size: " + A.length);
		boolean canjump = solution.canJump(A);
//		System.out.println("count: " + solution.count);
//		System.out.println("tgp: " + solution.tgp);
//		System.out.println("tis: " + solution.tis);
		Assert.assertFalse(canjump);
	}
	
	@Test(timeout=5000)
	public void test7() {
		Solution solution = new Solution();
		int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6};
		System.out.println("solving problem of size: " + A.length);
		boolean canjump = solution.canJump(A);
//		System.out.println("count: " + solution.count);
//		System.out.println("tgp: " + solution.tgp);
//		System.out.println("tis: " + solution.tis);
		Assert.assertFalse(canjump);
	}
	
	
	@Test
	public void test8() {
		Solution solution = new Solution();
		int[] A = new int[25001];
		for (int i = 0; i <= 25000; i++) {
			A[i] = 25000 - i;
		}
		//int[] A = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6};
		System.out.println("solving problem of size: " + A.length);
		boolean canjump = solution.canJump(A);
//		System.out.println("count: " + solution.count);
//		System.out.println("tgp: " + solution.tgp);
//		System.out.println("tis: " + solution.tis);
		Assert.assertTrue(canjump);
	}
}
