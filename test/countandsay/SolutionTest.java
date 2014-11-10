package countandsay;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals("11", solution.countAndSay(2));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertEquals("21", solution.countAndSay(3));
	}	
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		Assert.assertEquals("1211", solution.countAndSay(4));
	}
	
}
