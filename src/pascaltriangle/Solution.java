package pascaltriangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/pascals-triangle/
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 */
public class Solution {
	
	
	// 1 0 0 0
	// 1 1 0 0
	// 1 2 1 0
	// 1 3 3 1
	private List<Integer> generateNextRow(List<Integer> row) {
		List<Integer> currentRow = new ArrayList<Integer>();
		for (int i = 0; i < row.size() + 1; i++) {
			if (i == 0) {
				currentRow.add(1);
			}
			else {
				if (i < row.size()) {
					currentRow.add(row.get(i) + row.get(i - 1));
				}
				else {
					currentRow.add(1);
				}
			}
		}
		return currentRow;
	}
	
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		if (numRows > 0) {
			List<Integer> nextRow = Arrays.asList(1);
			result.add(nextRow);
			int i = 1;
			while (i < numRows) {
				nextRow = generateNextRow(nextRow);
				result.add(nextRow);
				i++;
			}
		}
		
		return result;
    }
}
