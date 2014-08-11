package jumpgame;

import java.util.ArrayList;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/jump-game/
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * 
 * 
 * scan array to find a 0
 *   - no zero: return true
 *   - found a zero at index k:
 *   	- can we jump over it?
 *   		- go back to k-1 if possible
 *   		- is A[k-1] >= 2 (k - (k-1) + 1)
 *   		- k--, stop if we reached 0
 */

public class Solution {
	public boolean canJump(int[] A) {
		System.out.println("solving " + A);
		
		boolean reachedLastIndex = false;
		int k = 0;
		int n = A.length;
		
		// keep looping as long as k is a valid index
		while (k < n) {
			// if k is at the last index or further
			if (k >= A.length - 1) {
				// we're done
				reachedLastIndex = true;
			}
			// otherwise
			else {
				// if we reach a 0
				if (A[k] == 0) {
					boolean canJumpOverZero = false;
					// try to jump over it
					int i = k - 1;
					// find an element before k that allows jumping over
					while (i >= 0) {
						if (A[i] >= k - i + 1) {
							canJumpOverZero = true;
							break;
						}
						i--;
					}
					// if we could not jump over a zero
					if (!canJumpOverZero) {
						// stop searching
						break;
					}
				}
			}
			// stop here if we reached the last index
			if (reachedLastIndex) break;
			
			// otherwise move on to the next index
			k++;
		}
		
        return reachedLastIndex;
    }
}
