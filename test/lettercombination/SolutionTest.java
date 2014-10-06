package lettercombination;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		String digits = "29";
		String[] expected = {"aw", "ax", "ay", "az", "bw", "bx", "by", "bz", "cw", "cx", "cy", "cz"};
		List<String> result = solution.letterCombinations(digits);
		Assert.assertNotNull(result);
		Assert.assertEquals(expected.length, result.size());
		for (String s: expected) {
			Assert.assertTrue(result.contains(s));
		}
	}
}
