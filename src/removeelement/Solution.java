package removeelement;


/**
 * https://oj.leetcode.com/problems/remove-element/
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 */

public class Solution {
	public int removeElement(int[] A, int elem) {
 		int origLength = A.length;
		
		int current = 0; // index being looked at
		int end = origLength - 1;     // last index of interest
		
		
		while (current <= end) {
			// if the current item is not what we're looking for
			if (A[current] != elem) {
				current++;
				// just move to the next index
			}
			else {
				int nbHits = 0;
				// we found a match
				// remember current in case we have several hits
				int insertionPoint = current;
				// look at next
				current++;
				// any more match?
				while (current <= end && A[current] == elem ) {
					current++;
				}
				
				// we found (current - insertionPoint + 1) matches
				nbHits = current - insertionPoint;
				
				// nb of items to copy = current + 1 to end
				
				// copy from insertion point to end
				for (int j = 0; j < end - current + 1; j++) {
					A[j + insertionPoint] = A[j + current];
				}
				
				end = end - nbHits;
				
				// restore current
				current = insertionPoint + 1;
			}
		}
        
        return (end + 1);
    }
}
