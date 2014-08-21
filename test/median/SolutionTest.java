package median;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		int A[] = {1, 3, 4, 10, 15};
		int B[] = {12, 14, 16};
		Solution solution = new Solution();
		Assert.assertEquals(11.0, solution.findMedianSortedArrays(A, B), 0);
	}
	
	@Test
	public void test2() {
		int A[] = {1, 4, 5, 10, 15, 17, 18};
		int B[] = {2, 3, 14, 16, 20, 21};
		Solution solution = new Solution();
		Assert.assertEquals(14.0, solution.findMedianSortedArrays(A, B), 0);
	}
	
	@Test
	public void test3() {
		int A[] = {1};
		int B[] = {1};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(1.0, median, 0);
	}
	
	@Test
	public void test4() {
		int A[] = {1, 1};
		int B[] = {1, 1};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(1.0, median, 0);
	}
	
	@Test
	public void test5() {
		int A[] = {1, 2};
		int B[] = {1, 2, 3};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(2.0, median, 0);
	}
	
	@Test
	public void test6() {
		int A[] = {};
		int B[] = {2, 3};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(2.5, median, 0);
	}
	
	@Test
	public void test7() {
		int A[] = {1 ,2};
		int B[] = {1, 1};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(1.0, median, 0);
	}
	
	@Test
	public void test8() {
		int A[] = {4, 5};
		int B[] = {1, 2, 3, 6, 7, 8, 9};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(5.0, median, 0);
	}
	
	@Test
	public void test9() {
		int A[] = {100000};
		int B[] = {100001};
		Solution solution = new Solution();
		double median = solution.findMedianSortedArrays(A, B);
		Assert.assertEquals(100000.5, median, 0);
	}
}
