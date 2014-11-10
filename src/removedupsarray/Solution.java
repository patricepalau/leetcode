package removedupsarray;

/**
 * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
 * 
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2].
 * 1, 1, 2, 2
 * i=0, j=2 -> copy 2 chars from 2 to 1 => 1, 2, 2, 2
 * i=2, j=3 ->
 */
public class Solution {
	public int removeDuplicates(int[] A) {
        int length = A.length;
        
        for (int i = 0; i < length; i++) {
        	int current = A[i];
        	int j = i + 1;
        	while (j < length && A[j] == current) {
        		j++;
        	}
        	// A[j] is the first different item after A[i]
        	if (j > i + 1) {
        		// i+1 to j-1 are dups of ith item: remove them
        		// by copying j..n-1 to i+1..n-1
        		System.arraycopy(A, j, A, i + 1, length - j);
        		length -= j - 1 - i;
        	}
        }
        
        return length;
    }
}
