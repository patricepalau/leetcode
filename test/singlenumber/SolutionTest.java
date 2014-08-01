package singlenumber;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(2, solution.singleNumber(new int[]{1,3,2,3,1}));
	}
}
