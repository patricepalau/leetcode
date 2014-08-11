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
}
