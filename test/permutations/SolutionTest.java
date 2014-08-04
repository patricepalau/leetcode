package permutations;

import java.util.List;

import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		int[] num = {1, 2, 3};
		Solution solution = new Solution();
		List<List<Integer>> result = solution.permute(num);
		System.out.println(result);
	}
	
	@Test
	public void test2() {
		int[] num = {5, 4, 6, 2};
		Solution solution = new Solution();
		List<List<Integer>> result = solution.permute(num);
		System.out.println(result);
	}
}
