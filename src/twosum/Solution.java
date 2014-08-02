package twosum;

/**
 * https://oj.leetcode.com/problems/two-sum/
 * 
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * 
 * 
 * This solution is fairly simple: we pick a number in the array and try to find whether (target-number) also
 * belongs to the array. The solution below got accepted but could be further enhanced by first sorting the array O(nlogn)
 * then using binary search to find the second value O(logn)
 * 
 */
public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int[] indexes = new int[2];
        for (int i = 0; i < numbers.length; i++) {
        	// is target-current in the array?
        	int current = numbers[i];
        	boolean found = false;
        	int j;
        	for (j = i + 1; j < numbers.length; j++) {
        		//if (j == i) continue;
        		found = (numbers[j] == target - current);
        		if (found) break;
        	}
        	if (found) {
        		indexes[0] = i + 1;
        		indexes[1] = j + 1;
        		break;
        	}
       }
       
       return indexes; 
    }
}
