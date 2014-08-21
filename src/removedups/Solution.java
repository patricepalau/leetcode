package removedups;

/**
 * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * 
 * For example
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * 
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
	
    public ListNode deleteDuplicates(ListNode head) {
    	if (head == null) return null;
    	if (head.next == null) return head;
    	
    	ListNode current  = head;
    	ListNode nextNode = null;
    	
    	while (current != null) {
    		nextNode = current.next;
    		while (nextNode != null && current.val == nextNode.val) {
    			current.next = nextNode.next;
    			nextNode.next = null;
    			nextNode = current.next;
    		} 
    		
    		current = current.next;
    	}
    	
        return head;
    }	
}
