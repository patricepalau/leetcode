package mergearray;

import java.util.Arrays;

/**
 * https://oj.leetcode.com/problems/merge-sorted-array/
 *
 * A = 1, 3, 5 - B = 2, 4
 * current = 0, A[current] = 1, 
 * B[i] = 2 
 * current = 1 A[current] = 3
 * => copy 3, 5 => A = 1, 2, 3, 5
 */
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
    	int indexA = 0;
    	int indexB = 0;
    	
    	// if A is empty, just copy B to A
    	if (m == 0) {
    		System.arraycopy(B, 0, A, 0, B.length);
    		return;
    	}
    	
    	// if B is empty, nothing to do
    	if (n == 0) {
    		return;
    	}
    	
    	// otherwise, try to merge each item in B
        while (indexB < n) {
        	if (indexA >= m + indexB) {
        		// we have examined all items in A
        		// just append the rest of B to A
        		A[indexA] = B[indexB];
        		indexA++;
        		indexB++;
        	}
        	else {
        		if (B[indexB] < A[indexA]) {
        			// insert B[indexB] at A[indexA]
        			System.arraycopy(A, indexA, A, indexA + 1, m - indexA + indexB);
        			A[indexA] = B[indexB];
        			indexB++;
        			indexA++;
        		}
        		else {
        			indexA++;
        		}
        	}
        }
    }
}
