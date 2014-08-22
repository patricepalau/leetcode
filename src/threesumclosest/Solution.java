package threesumclosest;

import java.util.Arrays;

/**
 * https://oj.leetcode.com/problems/3sum-closest/
 * 
 * Given an array S of n integers, find three integers in S such that
 * the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 */
public class Solution {
	public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[2];
        
        // sort num
        Arrays.sort(num);
        
        int n = num.length;
        
        for (int i = 0; i < n - 2; i++) {
        	boolean match = false;
        	for (int j = i + 1; j < n - 1 && !match; j++) {
        		int value = target - (num[i] + num[j]);
        		int index = binarySearch(value, num, j + 1, n - 1);
        		if (index >= 0) {
        			// got an exact match
        			result = target;
        			// there won't be better match than that
        			match = true;
        		}
        		else {
        			int sum = 0;
        			// no exact match for value
        			// we got the index at which the exact match would be inserted
        			if (-index < n) {
        				// if index is within the array's limit
        				if (-index - 1 > j) {
        					// the current values at -index - 1 and -index are respectively
        					// smaller and larger than the exact match
        					// find out which one will give the least difference with target
        					int sum1 = num[i] + num[j] + num[-index - 1];
        					int sum2 = num[i] + num[j] + num[-index];
        					if (Math.abs(sum1 - target) < Math.abs(sum2 - target)) {
        						sum = sum1;
        					}
        					else {
        						sum = sum2;
        					}
        					
        				}
        				else {
        					// best match would be inserted between element at j and j + 1
        					sum = num[i] + num[j] + num[-index];
        				}
        			}
        			else {
        				// element at last index is the closet
        				sum = num[i] + num[j] + num[n - 1];
        			}
        			
        			// is the current sum less than the current best result?
        			if (Math.abs(sum - target) < Math.abs(result - target)) {
        				// yes - remember it
        				result = sum;
        			}
        		}
        	}
        	
        	// got an exact match - stop here
        	if (match) break;
        }
        
        return result;
    }
	
	private int binarySearch(int value, int[] a, int start, int end) {
		if (start > end) return -start;
		
		int mid = (start + end) / 2;
		if (a[mid] == value) {
			return mid;
		}
		if (a[mid] < value) {
			return binarySearch(value, a, mid + 1, end);
		}
		else {
			return binarySearch(value, a, start, mid - 1);
		}
	}
}
