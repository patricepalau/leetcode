package rangesearch;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{1,4}, 4);
		Assert.assertArrayEquals(new int[]{1, 1}, result);
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{1}, 1);
		Assert.assertArrayEquals(new int[]{0, 0}, result);
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{2,2}, 3);
		Assert.assertArrayEquals(new int[]{-1, -1}, result);
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{5,7,7,8,8,10}, 8);
		Assert.assertArrayEquals(new int[]{3, 4}, result);
	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{5,7,7,7,8,8}, 8);
		Assert.assertArrayEquals(new int[]{4, 5}, result);
	}
	
	@Test
	public void test6() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{5,7,8,8,9,10}, 8);
		Assert.assertArrayEquals(new int[]{2, 3}, result);
	}
	
	@Test
	public void test7() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{5,8,8,9,10,11}, 8);
		Assert.assertArrayEquals(new int[]{1, 2}, result);
	}
	
	@Test
	public void test8() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{8,8,9,10,11,12}, 8);
		Assert.assertArrayEquals(new int[]{0, 1}, result);
	}
	
	@Test
	public void test9() {
		Solution solution = new Solution();
		int[] result = solution.searchRange(new int[]{2,2}, 2);
		Assert.assertArrayEquals(new int[]{0, 1}, result);
	}
}
