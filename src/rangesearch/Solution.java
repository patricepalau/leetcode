package rangesearch;

/**
 * https://oj.leetcode.com/problems/search-for-a-range/
 * 
 * 	Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * A simple solution consists in find target + scan left and right
 * This solution is however O(logn + p) where p is the number of occurrences.
 * 
 * A better solution consists in using a slightly modified binary search (see below)
 * and searching for target-e and target+e - where e is arbitrarily small.
 * The returned index is the index at which both values would be inserted, hence
 * designated the first and last index of the occurrence of the target value.
 * 
 */
public class Solution {
	
    public int[] searchRange(int[] A, int target) {
    	double epsilon = 0.1;
    	
    	int indexLower = binarySearch(A, 0, A.length - 1, target - epsilon);
    	int indexUpper = binarySearch(A, 0, A.length - 1, target + epsilon);
    	
    	if (indexLower == indexUpper) {
    		// value was not found: return -1, -1 as per problem statement
    		indexLower = -1;
    		indexUpper = -1;
    	}
    	else {
    		// indexLower points at the first occurrence of target
    		// indexUpper points at the first element which is not target
    		// (so possibly beyond the end of the array)
    		indexUpper--;
    	}
    	
    	return new int[]{indexLower, indexUpper};
    }
    
    // binary search is modified by removing the equality condition
    // and returning the start index instead of -1
    private int binarySearch(int[] A, int start, int end, double target) {
    	// not that the strict < is important here
    	if (end < start) {
    		return start;
    	}
    	
    	int mid = (start + end) / 2;
    	
    	if (A[mid] > target) {
    		return binarySearch(A, start, mid - 1, target);
    	}
    	else {
    		return binarySearch(A, mid + 1, end, target);
    	}
    }
}