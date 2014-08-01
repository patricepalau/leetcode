package singlenumber2;

import java.util.HashSet;
import java.util.Set;

/**
 * https://oj.leetcode.com/problems/single-number-ii/
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 
 * Following the same idea than the version with 2, i.e. keeping track of which element has been seen once and twice.
 * We remove elements seen a 3rd time from both structures. The remaining element is our result.
 * 
 */
public class Solution {
	
	public int singleNumber(int[] a) {
		// HashSet provides O(1) for add, remove, contains
		Set<Integer> seenOnce = new HashSet<Integer>();
		Set<Integer> seenTwice = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			int current = a[i];
			if (!seenTwice.contains(current)) {
				if (seenOnce.contains(current)) {
					seenTwice.add(current);
				}
				else {
					seenOnce.add(current);
				}
			}
			else {
				seenOnce.remove(Integer.valueOf(current));
				seenTwice.remove(Integer.valueOf(current));
			}
		}
		
		return seenOnce.iterator().next();
    }
}
