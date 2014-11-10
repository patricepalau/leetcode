package lastword;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution ();
		String s = "Hello World";
		Assert.assertEquals(5, solution.lengthOfLastWord(s));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution ();
		String s = "a";
		Assert.assertEquals(1, solution.lengthOfLastWord(s));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution ();
		String s = "a ";
		Assert.assertEquals(1, solution.lengthOfLastWord(s));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution ();
		String s = "Today is a nice day";
		Assert.assertEquals(3, solution.lengthOfLastWord(s));
	}
}
