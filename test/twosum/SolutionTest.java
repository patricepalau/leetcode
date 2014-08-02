package twosum;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] numbers = {1, 3, 5, 6};
		int target = 9;
		Assert.assertArrayEquals(new int[]{2, 4}, solution.twoSum(numbers, target));;
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] numbers = {3, 2, 4};
		int target = 6;
		Assert.assertArrayEquals(new int[]{2, 3}, solution.twoSum(numbers, target));;
	}
}
