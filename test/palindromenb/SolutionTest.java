package palindromenb;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(true, solution.isPalindrome(121));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertEquals(true, solution.isPalindrome(11));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		Assert.assertEquals(false, solution.isPalindrome(-2147483648));
	}

	@Test
	public void test4() {
		Solution solution = new Solution();
		Assert.assertEquals(false, solution.isPalindrome(10));
	}
}
