package maxprofit;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		Assert.assertEquals(3, solution.maxProfit(new int[]{1, 2, 1, 3, 4}));
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] prices = new int[10000];
		for (int i = 1; i <= 10000; i++) {
			prices[i - 1] = 10000 - (i - 1);
		}
		Assert.assertEquals(0, solution.maxProfit(prices));
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		Assert.assertEquals(3, solution.maxProfit(new int[]{1, 4, 2}));
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		Assert.assertEquals(4, solution.maxProfit(new int[]{3,2,6,5,0,3}));
	}
}
