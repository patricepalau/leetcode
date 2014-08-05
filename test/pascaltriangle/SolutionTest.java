package pascaltriangle;

import java.util.List;

import org.junit.Test;


public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		
		List<List<Integer>> test = solution.generate(4);
		System.out.println(test);
	}
}
