package zigzag;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/zigzag-conversion/
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a number of rows
 */
public class SolutionTest {
	@Test
	public void test1() {
		Solution s = new Solution();
		String t = "PAYPALISHIRING";
		String e = "PAHNAPLSIIGYIR";
		String result = s.convert(t, 3);
		
		Assert.assertEquals(e, result);
	}
}
