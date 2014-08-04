package permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/permutations/
 * 
 * Given a collection of numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * Backtracking solves the problem - for small values of n.
 * 
 */
public class Solution {
	List<List<Integer>> permutations;
	int n;
	int[] num;
	
	// evaluates whether this is a solution
	private boolean isASolution(int[] a, int k) {
		// a is a solution when we filled all spots
		// i.e. k is set to the last index
		return (k + 1 == n);
	}
	
	// returns the possible candidates for the kth position of a
	// given the elements already at 0..k-1
	private int[] buildCandidates(int[] a, int k) {
		// elements in S which are not already in A
		int[] candidates = new int[n - k];
		int index = 0;
		for (int i = 0; i < num.length; i++) {
			int current = num[i];
			boolean found = false;
			for (int j = 0; j < k; j++) {
				if (current == a[j]) {
					found = true;
					break;
				}
			}
			if (!found) {
				candidates[index] = current;
				index++;
			}
		}
		return candidates;
	}
	
	private void processSolution(int a[]) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) list.add(a[i]);
		permutations.add(list);
	}
	
	// given a current solution a0...ak-1
	// backtrack to determine all possibilities to fill ak
	private void backtrack(int[] a, int k) {
		if (isASolution(a, k)) {
			processSolution(a);
		}
		else {
			k = k + 1;
			int[] candidates = buildCandidates(a, k);
			for (int i = 0; i < candidates.length; i++) {
				a[k] = candidates[i];
				backtrack(a, k);
			}
		}
	}
	
	public List<List<Integer>> permute(int[] num) {
		permutations = new ArrayList<List<Integer>>();
		this.n = num.length;
		this.num = num;
        backtrack(new int[num.length], -1);
        return permutations;
    }
}
