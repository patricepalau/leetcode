package foursum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * WORK IN PROGRESS: DOES NOT WORK
 * 
 * https://oj.leetcode.com/problems/4sum/
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S
 * such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a <= b <= c <= d)
 * The solution set must not contain duplicate quadruplets.
 * 
 * 
 * 
 * 
 */

// find 4 in S that add up to target:
///  - for i=1..n
//   - choose ai
//   - find 3 in S\{ai} that add up to (target-ai)
//     - for j=1..n-1
//     - choose aj
//     - find 2 in S\{ai,aj} that add up to (target-ai-aj)
//       - choose ak
//       - find 1 in S\{ai,aj,ak} that add up to target-ai-aj-ak
//			returns a list of list of size 1 / or nothing
//       - add ak to each list
//     - add aj to each list
//   - add ai to each list
public class Solution {
	
	
	public List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
				
		int n = num.length;
		if (n < 4) return result;
		
		Arrays.sort(num);
		
		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				for (int k = j + 1; k < n - 1; k++) {
					// is there a p in i+2...n such that a[p] = target - (a[i] + a[j] + a[k])
					int value = target - (num[i] + num[j] + num[k]);
					if (num[k + 1] > value || num[n - 1] < value) {
						continue;
					}
					int index = binarySearch(value, num, k + 1, n - 1);
					if (index >= 0) {
						// add it to results
						List<Integer> list = new ArrayList<Integer>();
						list.add(num[i]);
						list.add(num[j]);
						list.add(num[k]);
						list.add(num[index]);
						if (!result.contains(list)) result.add(list);
					}
				}
				
			}
		}
		
		return result;
	}
	
	
	private int binarySearch(int value, int[] a, int start, int end) {
		if (start > end) {
			return -1;
		}
		
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
	
	int cnt2 = 0;
	long total2 = 0;
	int cnt1 = 0;
	int total1 = 0;
	int cnt4 = 0;
	int hit = 0;
	int cached = 0;
	long total6 = 0;
	long total7 = 0;
	int count7 = 0;
	
	Map<String,Long> totals = new HashMap<String,Long>();
	Map<String,Integer> counts = new HashMap<String,Integer>();
	
	private boolean search(int target, List<Integer> nums, int start, int end) {
		cnt4++;
		boolean done = false;
		boolean found = false;
		while (!done) {
			if (start > end) {
				done = true;
			}
			else {
				int mid = (start + end) / 2;
				if (nums.get(mid) == target) {
					found = true;
					done = true;
				}
				else if (nums.get(mid) > target) {
					return search(target, nums, start, mid - 1);
				}
				else {
					return search(target, nums, mid + 1, end);
				}
			}
		}
		
		return found;
	}
	
	private Object[] store1;
	private Object[][] store2;
	private Object[][][] store3;
	
	private List<List<Integer>> cache(List<Integer> indexes) {
		hit++;
		List<Integer> tmp = new ArrayList<Integer>(indexes);
		Collections.sort(tmp);
		List<List<Integer>> result = null;
		if (indexes.size() == 1) {
			result = (List<List<Integer>>)store1[indexes.get(0)];
		}
		if (indexes.size() == 2) {
			result = (List<List<Integer>>)store2[indexes.get(0)][indexes.get(1)];
		}
		if (indexes.size() == 3) {
			result = (List<List<Integer>>)store3[indexes.get(0)][indexes.get(1)][indexes.get(2)];
		}
		
		if (result != null) cached++;
		return result;
	}
	
	private void setCache(List<List<Integer>> result, List<Integer> indexes) {
		if (indexes.size() == 1) {
			store1[indexes.get(0)] = result;
		}
		if (indexes.size() == 2) {
			store2[indexes.get(0)][indexes.get(1)] = result;
		}
		if (indexes.size() == 3) {
			store3[indexes.get(0)][indexes.get(1)][indexes.get(2)] = result;
		}
	}
	
	//private List<List<Integer>> find(List<Integer> num,  int target, Set<Integer> indexes) {
	private List<List<Integer>> find(List<Integer> num, int target, int searchingfor, List<Integer> indexes) {
		//
		// did we already calculate the same combination?
		//
		
		int len = num.size() - 1; //length - 1;
		List<List<Integer>> all = cache(indexes);
		if (all != null) {
			return all;
		}
		
		all = new ArrayList<List<Integer>>();
				
		long t0 = System.currentTimeMillis();
		long t1 = 0,t2 = 0,t3 = 0,t4 = 0,t5 = 0,t6 = 0;
		
		// we picked 3 already
		if (searchingfor == 1) {
			// we want 1 item = target
			int start = 0;
			int end = num.size() - 1;
			int nSmallest = num.get(start);
			int nLargest = num.get(end);
			
			if (target <= nLargest && target >= nSmallest) {
				// TODO need to take care of unwanted indexes
				// as this may invalidate the result
				if (search(target, num, 0, num.size() - 1)) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(target);
					all.add(list);
				}
				t2 = System.currentTimeMillis();
			}
			t3 = System.currentTimeMillis();
		}
		else {
			int nLargest = 0;
			int nSmallest = 0;
			int end = num.size() - 1;
			for (int k = 0; k < searchingfor; k++) {
				nSmallest += num.get(k);
				nLargest += num.get(end - k);
			}

			t4 = System.currentTimeMillis();
			
//			String name = "before"+searchingfor;
//			Long t = totals.get(name);
//			if (t == null) totals.put(name, (t5-t4));
//			else totals.put(name, t + (t5-t4));
//			Integer c = counts.get(name);
//			if (c == null) counts.put(name, 1);
//			else counts.put(name, c + 1);

			for (int i = 0; i < len; i++) {
				long loop1 = System.currentTimeMillis();
				int b = num.get(i);
				long loop2 = System.currentTimeMillis();	
				long loop3 = 0, loop4 = 0, loop5 = 0;
				if (target - b > nLargest || target - b < nSmallest) {
//					long t9 = System.currentTimeMillis();
//					name = "failedcomp"+searchingfor;
//					t = totals.get(name);
//					if (t == null) totals.put(name, (t9-t3));
//					else totals.put(name, t + (t9-t3));
//					c = counts.get(name);
//					if (c == null) counts.put(name, 1);
//					else counts.put(name, c + 1);
					loop3 = System.currentTimeMillis();
				}
				else {
					
					num.remove(i);
					loop4 = System.currentTimeMillis();
					indexes.add(i);
					List<List<Integer>> numbers = find(num, target - b, searchingfor - 1, indexes);
					indexes.remove(Integer.valueOf(i));
					loop5 = System.currentTimeMillis();
					//					t = totals.get("calc"+searchingfor);
//					if (t == null) totals.put("calc" + searchingfor, (t6-t10));
//					else totals.put("calc" + searchingfor, t + (t6-t10));
//					c = counts.get("calc" + searchingfor);
//					if (c == null) counts.put("calc" + searchingfor, 1);
//					else counts.put("calc" + searchingfor, c + 1);
					
					num.add(i, b);

					for (List<Integer> list: numbers) {
						list.add(b);
						all.add(list);
					}
					
//					long t7 = System.currentTimeMillis();
//					t = totals.get("last"+searchingfor);
//					if (t == null) totals.put("last" + searchingfor, (t7-t6));
//					else totals.put("last" + searchingfor, t + (t7-t6));
//					c = counts.get("last" + searchingfor);
//					if (c == null) counts.put("last" + searchingfor, 1);
//					else counts.put("last" + searchingfor, c + 1);
				}
//				System.out.println("loop1: " + loop1);
//				System.out.println("loop2: " + loop2);
//				System.out.println("loop3: " + loop3);
//				System.out.println("loop4: " + loop4);
//				System.out.println("loop5: " + loop5 + " - " + (loop5-loop4));
//				System.out.println();
			}
			t5 = System.currentTimeMillis();
//			long t6 = System.currentTimeMillis();
//			if (searchingfor == 2) {
//				total6 += (t6-t5);
//			}
//			
		}
//		if (searchingfor==2) {
//			System.out.println("t0: " + t0);
//			System.out.println("t1: " + t1);
//			System.out.println("t2: " + t2);
//			System.out.println("t3: " + t3);
//			System.out.println("t4: " + t4);
//			System.out.println("t5: " + t5 + " - " + (t5-t4));
//		}
		long tend = System.currentTimeMillis();
		String name = "find"+searchingfor;
		Long t = totals.get(name);
		if (t == null) totals.put(name, (tend-t0));
		else totals.put(name, t + (tend-t0));
		name = "count"+searchingfor;
		Integer c = counts.get(name);
		if (c == null) counts.put(name, 1);
		else counts.put(name, c + 1);
		
		setCache(all, indexes);
		
		return all;
	}
	
    public List<List<Integer>> fourSum2(int[] num, int target) {
    	long tstart = System.currentTimeMillis();
    	System.out.println();
    	System.out.println("Running for: " + num.length);
    	
    	store1 = new Object[num.length];
    	store2 = new Object[num.length][num.length];
    	store3 = new Object[num.length][num.length][num.length];
    	
    	List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
    	long loopstart = 0;
    	if (num.length >= 4) {
    		
        	int len = num.length;
        	List<Integer> data = new ArrayList<Integer>(num.length);
        	for (int i = 0; i < num.length; i++) {
        		data.add(num[i]);
        	}
        	Collections.sort(data);
        	Set<List<Integer>> temp = new HashSet<List<Integer>>();
    		//Set<Integer> indexes = new TreeSet<Integer>();
        	List<Integer> indexes = new ArrayList<Integer>();
    		loopstart = System.currentTimeMillis();
        	for (int i = 0; i < len; i++) {
        		Integer a = data.get(i); //[i];
        		indexes.add(i);

    			data.remove(i);
        		List<List<Integer>> numbers = find(data, target - a, 3, indexes);
        		data.add(i, a);
        		// add a to all found lists
        		for (List<Integer> list: numbers) {
        			list.add(a);
        			Collections.sort(list);
        			temp.add(list);
        		}
        		indexes.remove(Integer.valueOf(i));

        	}
        
        	quadruplets = new ArrayList<List<Integer>>(temp);
    	}
    	
    	long loopend = System.currentTimeMillis();
    	System.out.println("total time in outter loop: " + (loopend-loopstart) + "ms");
    	
    	System.out.println("total for find3: "  + totals.get("find3") + "ms - iterations: " + counts.get("count3"));
    	System.out.println("total for find2: "  + totals.get("find2") + "ms - iterations: " + counts.get("count2"));
//    	System.out.println("        before2: "  + totals.get("before2") + "ms - iterations: " + counts.get("before2"));
//    	System.out.println("          last2: "  + totals.get("last2") + "ms - iterations: " + counts.get("last2"));
//    	System.out.println("    failedcomp2: "  + totals.get("failedcomp2") + "ms - iterations: " + counts.get("failedcomp2"));
//    	System.out.println("          calc2: "  + totals.get("calc2") + "ms - iterations: " + counts.get("calc2"));
    	System.out.println("total for find1: "  + totals.get("find1") + "ms - iterations: " + counts.get("count1"));
    	System.out.println("total running time: " + (loopend-tstart) + "ms");
    	System.out.println("cache hits: " + cached + " / " + hit);

    	return quadruplets;
    }
}
