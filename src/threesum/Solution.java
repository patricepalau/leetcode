package threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * https://oj.leetcode.com/problems/3sum/
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * - Elements in a triplet (a,b,c) must be in non-descending order. (ie, a <= b <= c)
 * - The solution set must not contain duplicate triplets.
 * 
 * THIS SOLUTION IS TOO SLOW - NOT ACCEPTED
 */
public class Solution {
	private List<List<Integer>> twoSum(List<Integer> numbers, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < numbers.size(); i++) {
        	// is target-current in the array?
        	int current = numbers.get(i);
        	boolean found = false;
        	int j;
        	for (j = i + 1; j < numbers.size(); j++) {
        		found = (numbers.get(j) == target - current);
        		if (found) {
        			List<Integer> list = new ArrayList<Integer>(2);
            		list.add(numbers.get(i));
            		list.add(numbers.get(j));
            		if (!result.contains(list)) result.add(list);
        		}
        	}
       }
       
       return result; 
    }
	
    public List<List<Integer>> threeSum(int[] num) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();;
        
    	List<Integer> numbers = new ArrayList<Integer>();
    	for (int i = 0; i < num.length; i++) {
    		numbers.add(num[i]);
    	}
    	
    	for (int i = 0; i < num.length; i++) {
        	int a = num[i];
        	numbers.remove(i);
        	List<List<Integer>> match = twoSum(numbers, -a);
        	if (!match.isEmpty()) {
        		// we found at least 1 match
        		for (List<Integer> list: match) {
        			list.add(a);
        			Collections.sort(list);
        			if (!result.contains(list)) result.add(list);
        		}
        	}
        	numbers.add(i, a);
        }
        
        return result;
    }
}
