package largestrectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * 
 * For example:
 * Given height = [2,1,5,6,2,3], return 10
 * 
 * 
 * Calculating all possible surfaces, even with dp, proved too slow.
 * This algorithm keeps a roughly linear complexity (it is slower if the number of distinct
 * heights is large and close to n) by scanning through the histogram
 * and keeping track of consecutive bars of a given height.
 * 
 * In the example above, the algorithm does the following
 * - we have orderedHeights = [1, 2, 3, 5, 6]
 * - we keep track of consecutives in an array of the same size, initially [0, 0, 0, 0, 0]
 * - iteration #1 (bar of height 2)
 *     - [1, 1, 0, 0 ,0] i.e. 1 bar of height >= 2 and >= 1
 * - iteration #2 (bar of height 1)
 * 	   - a) [2, 1, 0, 0, 0] i.e. 2  bars of height 1
 *     - b) [2, 0, 0, 0, 0] end of serie for bar 2: we clear 1 bar of height 2 => maxarea = 2
 * - iteration #3 (bar of height 5)
 *     - [3, 1, 1, 1, 0] i.e. 1 bar of height >= 5, >= 3 and >=2 , and >= 1
 * - iteration #4 (bar of height 6)
 *     - [4, 2, 2, 2, 1] we increment every height in this step
 * - iteration #5 (2)
 *     - [5, 3, 0, 0, 0] we cleared:
 *          - 2 bars of height >= 3 -> maxare = 6
 *          - 2 bars of height >= 5 -> maxarea = 10
 *          - 1 bar of height >= 6  -> 6 // discarded
 *          - maxarea is now = 10
 * - iteration #6 (3)
 *    - [6, 4, 1, 0, 0]
 * - finally we have areas of 6x1=6, 4x2=8, 1x3=3 - none of them beating maxarea
 *   ==> return 10 
 * 
 * 
 * @author Patrice Palau
 */
public class Solution {	
	
	public int largestRectangleArea(int[] heights) {
		// this array keeps track of the number of consecutive bars of a given height
		// consecutives[k] = number of consecutive bars of a height >= k
		int[] consecutives;
		
		// this is the final result returned by this method
		int area = 0;
		
		// number of bars in the histogram
		int len = heights.length;
		if (len == 0) return 0;
		
		// for optimization purpose, we keep track of the max height seen so far
		int maxHeightSoFar = 0;
		
		// we keep an array containing all distinct heights, in sorted order
		List<Integer> orderedHeights = new ArrayList<Integer>();
		
		// small optimization - in case the histogram is in increasing order only (see further down)
		boolean isAlreadyOrdered = true;
		
		// we build orderedHeights manually
		int max = heights[0]; // keep track of max
		orderedHeights.add(max);
		// for each bar in the histogram
		for (int i = 1; i < heights.length; i++) {
			int currenth = heights[i];
			// current height is larger than max - just append it at the end of the list
			if (max < currenth) {
				orderedHeights.add(currenth);
				// current height is also the max item in our array now
				max = currenth;
			}
			else {
				// the histogram is not already in ascending order
				isAlreadyOrdered = false;
				// find where in the array it should be inserted
				// (we do a simple scan here - this could be further improved)
				for (int j = 0; j < orderedHeights.size(); j++) {
					int current = orderedHeights.get(j);
					if (current == currenth) {
						// we don't insert duplicates
						break;
					}
					if (currenth < current) {
						// stop as soon as we find an item larger than current height
						// and insert at this position
						orderedHeights.add(j, currenth);
						// reset max to the last item in the array
						max = orderedHeights.get(orderedHeights.size() - 1);
						break;
					}
				}
			}
		}
		
		// if the histogram is already a rectangle...
		if (orderedHeights.size() == 1) {
			// return the result right away
			return heights[0] * len;
		}
		
		// for efficiency, we build a height->index mapping
		// (avoiding an indexOf)
		Map<Integer,Integer> m = new HashMap<Integer,Integer>();
		int index = 0;
		for (Integer h: orderedHeights) {
			m.put(h, index);
			index++;
		}
	
		// initialize 'consecutives' array - one element per height
		int nbHeights = orderedHeights.size();
		consecutives = new int[nbHeights];
				
		// we scan the histogram
		for (int i = 0; i < len; i++) {			
			// consider the current bar height
			int h = heights[i];
			// for optimization purposes, remember the max height seen so far
			if (h > maxHeightSoFar) maxHeightSoFar = h;
			
			// now get the index in our array where consecutive bars of height >= h are stored
			int indexForH = m.get(h);
			
			// get the current known consecutive bars of height >= h
			// and increment it by 1
			consecutives[indexForH] = consecutives[indexForH] + 1;
			
			// now we also need to increment the # of every consecutive bars
			// of every height lower than h
			// (note that we don't need this step is the heights came in already ordered)
			for (int k = 0; !isAlreadyOrdered && k < indexForH; k++) {
				consecutives[k] = consecutives[k] + 1;
			}
			
			// now we need to end the streak of consecutive bars for all bars heigher than h
			// but we need to check for the corresponding areas before doing that as
			// the largest area could be in there.
			
			// the next index contains the # of consecutive bars of the next height
			int k = indexForH + 1; 
			
			// we don't need to consider all heights - just the ones up to the max height seen so far
			int indexForMaxSoFar = m.get(maxHeightSoFar);
			
			// if we're examining the highest bar so far or we reached the end of the array
			// the following step is not necessary (this change made quite a big difference as
			// it cuts down the number of iterations when the data set is fairly large, with a lot of distinct heights)
			boolean done = (indexForH >= indexForMaxSoFar || k >= nbHeights);
			
			// keep scanning until we find a non-null value in our array
			while (!done) {
				int currentarea = 0;
				
				if (consecutives[k] != 0) {
					for (int l = k; l < nbHeights; l++) {
						int heightForIndexL = orderedHeights.get(l);
						currentarea = consecutives[l] * heightForIndexL ;
						if (currentarea > area) area = currentarea;
						consecutives[l] = 0;
					}
					break;
				}
				
				// check next height
				k++;
				
				// stop if we reached the end of consecutives
				done = (k >= nbHeights);
			}
		}
		
		// optimization for histogram in strict ascending order
		if (isAlreadyOrdered) {
			for (int i = 0; i < len; i++) {
				consecutives[i] = len - i;
			}
		}
		
		// finally go through each remaining consecutives
		// and check whether we have a larger area than the one we have
		// currently detected
		for (int k = 0; k < nbHeights; k++) {
			if (consecutives[k] != 0 && consecutives[k] * orderedHeights.get(k) > area) area = consecutives[k] * orderedHeights.get(k);
		}
		
		return area;
	}
}
