package container;

/**
 * 
 * https://oj.leetcode.com/problems/container-with-most-water/
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * Somehow, I had to read the answer given in https://oj.leetcode.com/discuss/1074/anyone-who-has-a-o-n-algorithm.
 * 
 */
public class Solution {
    public int maxArea(int[] height) {
    	int maxArea = 0;
    	
// brute force is O(n^2)
//    	for (int i = 0; i < height.length - 1; i++) {
//    		for (int j = i + 1; j < height.length; j++) {
//    			int area = calculateArea(i, j, height[i], height[j]);
//    			if (area > maxArea) maxArea = area;
//    		}
//    	}
    	
    	int n = height.length;
    	int min = 0;
    	int max = n - 1;
    	
    	// we don't need to evaluate every pair...
    	// since if height[min] < height[max], every container formed by a(min) and a(i) where i in [i + 1, max - 1] 
    	// will necessarily give a smaller area (it is constrained by height[min], and it is narrower)
    	// we should then test the container formed by a(min+1) and a(max)
    	// if the opposite is true, we need tot est a(min) and a(max-1) next.
    	// In all case, we decrement the problem size at every iteration, hence O(n)
    	while (min < max) {
    		int currentArea = (max - min) * Math.min(height[min], height[max]);
    		if (maxArea < currentArea) maxArea = currentArea;
    		if (height[min] <= height[max]) {
    			min++;
    		}
    		else {
    			max--;
    		}
    	}
    	
    	return maxArea;
    }
}
