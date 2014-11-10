package mergearray;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] A = new int[1];
		int[] B = {1};
		int m = 0;
		int n = 1;
		solution.merge(A, m, B, n);
		Assert.assertEquals(1, A.length);
		Assert.assertEquals(1, A[0]);
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] A = new int[2];
		A[0] = 1;
		int[] B = {2};
		int m = 1;
		int n = 1;
		solution.merge(A, m, B, n);
		Assert.assertArrayEquals(new int[]{1,2}, A);
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		int[] A = new int[6];
		A[0] = 1;
		A[1] = 2;
		A[2] = 3;
		int[] B = {2, 5, 6};
		int m = 3;
		int n = 3;
		solution.merge(A, m, B, n);
		Assert.assertArrayEquals(new int[]{1,2,2,3,5,6}, A);	
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		int[] A = new int[2];
		A[0] = 2;
		
		int[] B = {1};
		int m = 1;
		int n = 1;
		solution.merge(A, m, B, n);
		Assert.assertArrayEquals(new int[]{1,2}, A);	
	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();
		int[] A = new int[6];
		A[0] = 4;
		A[1] = 5;
		A[2] = 6;
		int[] B = {1, 2, 3};
		int m = 3;
		int n = 3;
		solution.merge(A, m, B, n);
		Assert.assertArrayEquals(new int[]{1,2,3,4,5,6}, A);	
	}
}
