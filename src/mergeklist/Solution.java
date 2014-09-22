package mergeklist;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://oj.leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	private PriorityQueue<ListNode> heap; 
	
	//
	// time complexity is O(nk)
	// 
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists == null || lists.isEmpty()) {
		    return null;
		}
    	Comparator<ListNode> comp = new Comparator<ListNode>() {
			public int compare(ListNode n1, ListNode n2) {
				if (n1 == null && n2 == null) {
					return 0;
				}
				if (n1 == null) return -1;
				if (n2 == null) return 1;
				return n1.val - n2.val;
			}
		};		
		// create a min heap
		this.heap = new PriorityQueue<ListNode>(lists.size(), comp);
		
		for (ListNode head: lists) {
			if (head != null)
				heap.add(head);
		}
		
		ListNode current = null;
		ListNode mergedList = null;
		ListNode tail = null;
		
		while ((current = extractMin(lists)) != null) {
			if (mergedList == null) {
				mergedList = current;
				tail = current;
			}
			else {
				tail.next = current;
				tail = current;
			}
		}
		
		return mergedList;
	}
	
	//
	// time complexity is O(k) since:
	// 	removal is O(k)
	// 	enqueue is O(logk)
	//
	private ListNode extractMin(List<ListNode> lists) {
		ListNode min = null;
		
		if (!this.heap.isEmpty()) {
			min = this.heap.remove();
			if (min != null && min.next != null) {
				this.heap.add(min.next);
			}
		}
		
		return min;
	}
}
