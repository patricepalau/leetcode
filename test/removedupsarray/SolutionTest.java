package removedupsarray;

import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {
	@Test
	public void test1() {
		int[] A = {1, 1, 2};
		Solution solution = new Solution();
		Assert.assertEquals(2, solution.removeDuplicates(A));
	}
	
	@Test
	public void test2() {
		int[] A = {1, 1, 2, 2};
		Solution solution = new Solution();
		Assert.assertEquals(2, solution.removeDuplicates(A));
	}
	
}
