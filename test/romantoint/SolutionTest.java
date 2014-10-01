package romantoint;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		String s = "XII";
		int expected = 12;
		
		Assert.assertEquals(expected, solution.romanToInt(s));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		String s = "VII";
		int expected = 7;
		
		Assert.assertEquals(expected, solution.romanToInt(s));
	}
	
	public void test3() {
		Solution solution = new Solution();
		String s = "IX";
		int expected = 9;
		
		Assert.assertEquals(expected, solution.romanToInt(s));
	}
}
