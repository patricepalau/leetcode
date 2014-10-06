package longestprefix;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		String[] strs = {"foo", "foobar", "football"};
		Assert.assertEquals("foo", solution.longestCommonPrefix(strs));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		String[] strs = {};
		Assert.assertEquals("", solution.longestCommonPrefix(strs));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		String[] strs = {"a", "b"};
		Assert.assertEquals("", solution.longestCommonPrefix(strs));
	}
}
