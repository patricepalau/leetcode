package power;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(4, solution.pow(2, 2), 0);
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertEquals(15, solution.pow(15, 1), 0);
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		Assert.assertEquals(700.28148, solution.pow(8.88023, 3), 0.0001);
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();	
		Assert.assertEquals(0.00003, solution.pow(34.00515, -3), 0.0001);
	}
	
	@Test
	public void test5() {
		Solution solution = new Solution();	
		Assert.assertEquals(1.0, solution.pow(1.0, -2147483648), 0.0001);
	}
}
