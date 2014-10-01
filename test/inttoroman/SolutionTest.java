package inttoroman;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		int num = 10;
		String expected = "X";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
	
	@Test
	public void test2() {
		int num = 3;
		String expected = "III";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
	
	@Test
	public void test3() {
		int num = 2500;
		String expected = "MMD";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
	
	@Test
	public void test4() {
		int num = 2313;
		String expected = "MMCCCXIII";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
	
	@Test
	public void test5() {
		int num = 92;
		String expected = "XCII";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
	
	@Test
	public void test6() {
		int num = 94;
		String expected = "XCIV";
		Solution solution = new Solution();
		Assert.assertEquals(expected, solution.intToRoman(num));
	}
}
