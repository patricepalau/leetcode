package reverseint;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(321, solution.reverse(123));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertEquals(-321, solution.reverse(-123));
	}
}
