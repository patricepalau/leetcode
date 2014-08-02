package gasstation;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SolutionTest {

	private int[] gas;
	private int[] cost;
	private int index;
	
	public SolutionTest(int[] gas, int[] cost, int index) {
		this.gas = gas;
		this.cost = cost;
		this.index = index;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
			{new int[]{5, 3, 3}, new int[]{3,3,3}, 0},
			{new int[]{3, 3, 3}, new int[]{3,3,4}, -1},
			{new int[]{1, 5, 3}, new int[]{3,3,3}, 1}
			
		};
		return Arrays.asList(data);
	}
	
	@Test
	public void testGasStation() {
		Solution solution = new Solution();
		Assert.assertEquals(index, solution.canCompleteCircuit(gas, cost));
	}
}