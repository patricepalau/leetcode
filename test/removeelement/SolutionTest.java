package removeelement;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] A = {1, 2, 3, 4, 5, 6, 7};
		Assert.assertEquals(6, solution.removeElement(A, 3));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] A = {3, 3};
		Assert.assertEquals(0, solution.removeElement(A, 3));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		int[] A = {4, 5};
		Assert.assertEquals(1, solution.removeElement(A, 4));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		int[] A = {2, 2, 3};
		Assert.assertEquals(1, solution.removeElement(A, 2));
	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();
		int[] A = {4, 5};
		Assert.assertEquals(1, solution.removeElement(A, 5));
	}
	
	@Test
	public void test6() {
		Solution solution = new Solution();
		int[] A = {1, 2, 3, 3, 5, 6, 3};
		Assert.assertEquals(4, solution.removeElement(A, 3));
	}
}
