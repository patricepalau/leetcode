package reorderlist;

/**
 * https://oj.leetcode.com/problems/reorder-list/
 * 
 * Given a singly linked list L: L0 -> L1 -> ... -> Ln-1 -> Ln,
 * reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * 
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * 
 * 
 * The solution is using a recursion:
 * 
 * f(L1 -> L2 -> ... Ln-1 -> Ln)
 *   L1 -> Ln
 *   Ln -> L2
 *   L2 -> Ln-1
 *   Ln-1 -> L3
 *   // call recursively on L3
 *   Ln-2 -> null
 *   f(L3 -> L4 -> ... -> Ln-3 -> Ln-2)
 *     etc
 */
public class Solution {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
		public String toString() {
			return val + " -> " + (next != null ? next.val : "null");
		}
	}
	
	// this finds the node which is the last before last before tail
	// if there is such a node
	private ListNode findLastBeforeTail(ListNode head) {
		ListNode current = head;
		while (current != null) {
			if (current.next != null && current.next.next != null
					&& current.next.next.next == null) {
				break;
			}
			current = current.next;
		}
		return current;
	}
	
    public void reorderList(ListNode head) {
    	// covers single node or empty lists
    	if (head == null || head.next == null) return;
    	
    	// let's find the last before last before tail node...
    	ListNode lastBeforeLastBeforeTail = findLastBeforeTail(head);
    	
    	// if it doesn't exist, we have 2 nodes left - nothing to do
    	if (lastBeforeLastBeforeTail == null) return;
    	
    	// second node in original list
    	ListNode second = head.next;
    	// last before tail node in original list
    	ListNode lastBeforeTail = lastBeforeLastBeforeTail.next;
    	// tail node in original list
    	ListNode tail = lastBeforeTail.next;
    	// the node on which we're going to call reorderList recursively
    	// (the 3rd node in the original list)
    	ListNode nextNode = second.next;
    	
    	// now reorder as asked:
    	// - head now points to tail (L1 -> Ln)
    	head.next = tail;
    	// - second now points to last before tail (L2 -> Ln-1 -> Ln ...)
    	second.next = lastBeforeTail;
    	// - tail points to second (L1 -> Ln -> L2 -> Ln-1 -> Ln...)
    	tail.next = second;
    	
    	// only 3 nodes left
    	if (lastBeforeLastBeforeTail == head) {
    		second.next = null; // (L1 -> L3 -> L2 -> null)
    	}
    	// we have more than 4 nodes left...we need to continue the recursion
    	else if (lastBeforeTail != nextNode) {
    		// - last before tail now points at next node (... -> Ln-1 -> L3)
    		lastBeforeTail.next = nextNode;
    		// - this is now the tail of the list on which we're recursing
    		lastBeforeLastBeforeTail.next = null;
    		// call reorderList on nextNode
    		reorderList(nextNode);
    	}
    	else {
    		// we have exactly 4 nodes left
    		// last before tail is the new tail
    		lastBeforeTail.next = null;
    	}
    }
}
