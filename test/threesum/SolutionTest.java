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
		//System.out.println(result);
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
		//System.out.println(result);
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
		//System.out.println(result);
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
		//System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");
		Assert.assertNotNull(result);
		Assert.assertEquals(118, result.size());
		
//		[[-15, 1, 14], [-15, 2, 13], [-15, 3, 12], [-15, 4, 11], [-15, 5, 10], 
//		 [-15, 6, 9], [-15, 7, 8], [-14, 0, 14], [-14, 1, 13], [-14, 2, 12], 
//		 [-14, 3, 11], [-14, 4, 10], [-14, 5, 9], [-14, 6, 8], [-14, 7, 7], 
//		 [-13, -1, 14], [-13, 0, 13], [-13, 1, 12], [-13, 2, 11], [-13, 3, 10], 
//		 [-13, 4, 9], [-13, 5, 8], [-13, 6, 7], [-12, -2, 14], [-12, -1, 13],
//		 [-12, 0, 12], [-12, 1, 11], [-12, 2, 10], [-12, 3, 9], [-12, 4, 8], 
//		 [-12, 5, 7], [-12, 6, 6], [-11, -3, 14], [-11, -2, 13], [-11, -1, 12],
//		 [-11, 0, 11], [-11, 1, 10], [-11, 2, 9], [-11, 3, 8], [-11, 4, 7],
//		 [-11, 5, 6], [-10, -4, 14], [-10, -3, 13], [-10, -2, 12], [-10, -1, 11],
//		 [-10, 0, 10], [-10, 1, 9], [-10, 2, 8], [-10, 3, 7], [-10, 4, 6],
//		 [-10, 5, 5], [-9, -5, 14], [-9, -4, 13], [-9, -3, 12], [-9, -2, 11],
//		 [-9, -1, 10], [-9, 0, 9], [-9, 1, 8], [-9, 2, 7], [-9, 3, 6], 
//		 [-9, 4, 5], [-8, -6, 14], [-8, -5, 13], [-8, -4, 12], [-8, -3, 11], 
//		 [-8, -2, 10], [-8, -1, 9], [-8, 0, 8], [-8, 1, 7], [-8, 2, 6], 
//		 [-8, 3, 5], [-8, 4, 4], [-7, -7, 14], [-7, -6, 13], [-7, -5, 12],
//		 [-7, -4, 11], [-7, -3, 10], [-7, -2, 9], [-7, -1, 8], [-7, 0, 7], 
//		 [-7, 1, 6], [-7, 2, 5], [-7, 3, 4], [-6, -6, 12], [-6, -5, 11],
//		 [-6, -4, 10], [-6, -3, 9], [-6, -2, 8], [-6, -1, 7], [-6, 0, 6],
//		 [-6, 1, 5], [-6, 2, 4], [-6, 3, 3], [-5, -5, 10], [-5, -4, 9], 
//		 [-5, -3, 8], [-5, -2, 7], [-5, -1, 6], [-5, 0, 5], [-5, 1, 4],
//		 [-5, 2, 3], [-4, -4, 8], [-4, -3, 7], [-4, -2, 6], [-4, -1, 5],
//		 [-4, 0, 4], [-4, 1, 3], [-3, -3, 6], [-3, -2, 5], [-3, -1, 4],
//		 [-3, 0, 3], [-3, 1, 2], [-2, -2, 4], [-2, -1, 3], [-2, 0, 2], 
//		 [-2, 1, 1], [-1, -1, 2], [-1, 0, 1]]

	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();

		int[] numbers = {};
		long start = System.currentTimeMillis();

		List<List<Integer>> result = solution.threeSum(numbers);
		System.out.println((System.currentTimeMillis() - start) + "ms");

		Assert.assertNotNull(result);
		Assert.assertEquals("result has 0 triplets", 0, result.size());
	}
	
	@Test
	public void test6() {
		Solution solution = new Solution();

		int[] numbers = {-7,-11,12,-15,14,4,4,11,-11,2,-8,5,8,14,0,3,2,3,-3,-15,-2,3,6,1,2,8,-5,-7,3,1,8,11,-3,6,3,-4,-13,-15,14,-8,2,-8,4,-13,13,11,5,0,0,9,-8,5,-2,14,-9,-15,-1,-6,-15,9,10,9,-2,-8,-8,-14,-5,-14,-14,-6,-15,-5,-7,5,-11,14,-7,2,-9,0,-4,-1,-9,9,-10,-11,1,-4,-2,2,-9,-15,-12,-4,-8,-5,-11,-6,-4,-9,-4,-3,-7,4,9,-2,-5,-13,7,2,-5,-12,-14,1,13,-9,-3,-9,2,3,8,0,3};
		long start = System.currentTimeMillis();
		List<List<Integer>> result = solution.threeSum(numbers);
		//System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");
		Assert.assertNotNull(result);
		//Assert.assertEquals("result has 6 triplets", 6, result.size());
	}
	
	@Test
	public void test7() {
		Solution solution = new Solution();

		int[] numbers = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
		long start = System.currentTimeMillis();
		List<List<Integer>> result = solution.threeSum(numbers);
		//System.out.println(result);
		System.out.println((System.currentTimeMillis() - start) + "ms");
		Assert.assertNotNull(result);
	}
	
	@Test
	public void test8() {
		Solution solution = new Solution();
		int[] numbers = {0, 0};
		List<List<Integer>> result = solution.threeSum(numbers);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());
	}
	
	@Test
	public void test9() {
		Solution solution = new Solution();
		int[] numbers = {-1,0,1,2,-1,-4};
		List<List<Integer>> result = solution.threeSum(numbers);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains(Arrays.asList(-1,-1, 2)));
		Assert.assertTrue(result.contains(Arrays.asList(-1, 0, 1)));
	}
	
}
