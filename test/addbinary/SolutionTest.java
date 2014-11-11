package addbinary;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		String a = "11";
		String b = "1";
		String result = solution.addBinary(a, b);
		Assert.assertEquals("100", result);
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		String a = "1";
		String b = "111";
		String result = solution.addBinary(a, b);
		Assert.assertEquals("1000", result);
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		String a = "1010";
		String b = "1011";
		String result = solution.addBinary(a, b);
		Assert.assertEquals("10101", result);
	}
}
