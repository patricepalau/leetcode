package threesum;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		int[] numbers = {1, 3, -9, 6};
		long start = System.currentTimeMillis();

		List<List<Integer>> result = solution.threeSum(numbers);
		System.out.println((System.currentTimeMillis() - start) + "ms");

		Assert.assertNotNull(result);
		Assert.assertEquals("result has 1 triplet", 1, result.size());
		Assert.assertEquals(Arrays.asList(-9, 3, 6), result.get(0));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] numbers = {-1, 0, 1};
		long start = System.currentTimeMillis();

		List<List<Integer>> result = solution.threeSum(numbers);
		System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");

		Assert.assertNotNull(result);
		Assert.assertEquals("result has 1 triplet", 1, result.size());
		Assert.assertEquals(Arrays.asList(-1, 0, 1), result.get(0));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		int[] numbers = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		long start = System.currentTimeMillis();

		List<List<Integer>> result = solution.threeSum(numbers);
		System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");

		Assert.assertNotNull(result);
		Assert.assertEquals("result has 6 triplets", 6, result.size());
		Assert.assertTrue(result.contains(Arrays.asList(-4,-2,6)));
		Assert.assertTrue(result.contains(Arrays.asList(-4,0,4)));
		Assert.assertTrue(result.contains(Arrays.asList(-4,1,3)));
		Assert.assertTrue(result.contains(Arrays.asList(-4,2,2)));
		Assert.assertTrue(result.contains(Arrays.asList(-2,-2,4)));
		Assert.assertTrue(result.contains(Arrays.asList(-2,0,2)));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();

		int[] numbers = {4,-8,-9,9,10,-3,13,12,9,8,9,5,-4,-8,7,-12,-14,-11,-10,-6,2,7,-3,9,-8,9,-2,11,3,8,7,-8,-15,13,9,3,-5,-1,0,-11,-7,-5,1,4,-6,-7,-1,-13,-11,4,-4,-2,-12,0,-7,-5,-13,6,13,-3,-9,5,-4,-8,3,-10,10,5,5,-13,1,13,-11,-13,-6,-10,-4,1,-8,-8,-10,-4,6,-6,3,14,-4,5,-3,-5,9,4,-15,-9,3,-4,-4,-10,8,8,-8,-5,-2,-11};
		long start = System.currentTimeMillis();
		List<List<Integer>> result = solution.threeSum(numbers);
		System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");
		Assert.assertNotNull(result);
		//Assert.assertEquals("result has 6 triplets", 6, result.size());
	}
}
