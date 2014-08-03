package threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * The following solution, based on the solution for 2-sum, got accepted:
 * - for every item a in the array, apply 2 sum on the remaining array, searching for (target-a)
 * 
 * Solution accepted after a few changes:
 * - use of primitive arrays and binary search
 * - result cache for twoSum
 * - also moved collection of results out of twosum out of the main loop
 * 
 */
public class Solution {
	// cache for twosum - the key is the searched value
	Map<Integer,List<Object>> cache;
	
	// record a result of twosum in cache
	void recordInCache(int target, List<List<Integer>> result) {
		List<Object> data = new ArrayList<Object>();
		for (List<Integer> list: result) {
			int[] a = new int[list.size()];
			int index = 0;
			for (Integer i: list) {
				a[index] = i;
				index++;
			}
			data.add(a);
		}
		cache.put(target, data);
	}
	
	// retrieve value from cache
	List<List<Integer>> getFromCache(int target) {
		List<Object> result = cache.get(target);
		if (result != null) {
			List<List<Integer>> cache = new ArrayList<List<Integer>>();
			for (Object o: result) {
				int[] a = (int[])o;
				List<Integer> copy = new ArrayList<Integer>();
				for (int i = 0; i < a.length; i++) {
					copy.add(a[i]);
				}
				cache.add(copy);
			}
			return cache;
		}
		
		return null;
	}
	
	// search for 2 items in numbers adding up to target
	// returns all matches as a list of lists (of 2 items)
	List<List<Integer>> twoSum(int[] numbers, int target) {
		List<List<Integer>> result = getFromCache(target);
		if (result != null) return result;
		result = new ArrayList<List<Integer>>();
		
        for (int i = 0; i < numbers.length - 1; i++) {
			// is (target-current) in the array?
        	int current = numbers[i];
        	int j;
    		int[] rem = new int[numbers.length - 1 - i];
        	System.arraycopy(numbers, i+1, rem, 0, numbers.length - 1 - i);
        	j = Arrays.binarySearch(rem, target - current);
        	if (j >= 0) {
        		List<Integer> list = new ArrayList<Integer>(2);
        		list.add(numbers[i]);
        		list.add(rem[j]);
        		if (!result.contains(list)) result.add(list);
        	}
       }
       
       // record result in cache
       recordInCache(target, result);
       
       return result; 
    }
	
	// given a src array and an index, build an array that does
	// not contain the item at the given index
	private void buildRemainder(int[] src, int i, int[] dest) {
		// copy src[0..i-1] to dest [0..i-1]
		// copy src[i+1..len-1] to dest [i..len-2]
		int len = src.length;
		System.arraycopy(src, 0, dest, 0, i);
    	System.arraycopy(src, i + 1, dest, i, (len - 1) - ( i + 1) + 1);
	}
	
    public List<List<Integer>> threeSum(int[] num) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	// if less that 3 items, return empty list
        if (num.length < 3) return result;
        
        // init cache...
        cache = new HashMap<Integer,List<Object>>();
        
    	// sort numbers
    	Arrays.sort(num);
    	
    	// array will collect results of twoSum for each iteration
    	Object[] results = new Object[num.length];
    	
    	// remainder is num minus the current element
    	int[] remainder = new int[num.length - 1];
    	for (int i = 0; i < num.length; i++) {
        	int a = num[i];
        	// build remainder array
        	buildRemainder(num, i, remainder);
        	// find -a in remainder array
        	List<List<Integer>> match = twoSum(remainder, -a);
        	// store result
        	results[i] = match;
        }
        
    	// build output list, eliminate duplicates
    	int index = 0;
    	for (int i = 0; i < num.length; i++) {
    		List<List<Integer>> data = (List<List<Integer>>)results[i];
    		if (!data.isEmpty()) {
    			for (List<Integer> list: data) {
    				list.add(num[index]);
    				Collections.sort(list);
        			if (!result.contains(list)) result.add(list);
    			}
    		}
    		
			index++;
    	}
    	
        return result;
    }
}
