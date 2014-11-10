package validparenthesis;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertTrue(solution.isValid("()[[{}]]"));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		Assert.assertFalse(solution.isValid("([[{}]]"));
	}
}
