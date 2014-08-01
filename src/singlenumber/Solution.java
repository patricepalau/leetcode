package singlenumber;

import java.util.HashSet;
import java.util.Set;

/**
 * https://oj.leetcode.com/problems/single-number/
 * Given an array of integers, every element appears twice except one.
 * Find that element.
 * 
 * This solution is the alternative to using the xor operator
 * over all elements in the array, probably the best approach - which I totally missed.
 * 
 * The key here is to use the proper data structure to add/remove elements:
 * using a HashSet -which provides O(1) for add/remove/contains- allows a linear
 * algorithm - while using an ArrayList would make it quadratic.
 * 
 * @author Patrice Palau
 */
public class Solution {
	
	public int singleNumber(int[] a) {
		// HashSet provides O(1) for add, remove, contains
		Set<Integer> b = new HashSet<Integer>();
		
		for (int i = 0; i < a.length; i++) {
			if (!b.contains(a[i])) {
				b.add(a[i]);
			}
			else {
				b.remove(Integer.valueOf(a[i]));
			}
		}
		
		return b.iterator().next();
	}
}
