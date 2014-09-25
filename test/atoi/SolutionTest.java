package atoi;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(123, solution.atoi("123"));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertEquals(-123, solution.atoi("-123"));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		Assert.assertEquals(0, solution.atoi("-+2"));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		Assert.assertEquals(-12, solution.atoi("  -0012a42"));
	}
	  
}
