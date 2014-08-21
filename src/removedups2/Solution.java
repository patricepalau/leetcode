package removedups2;

/**
 * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * 
 * For example
 * - Given 1->2->3->3->4->4->5, return 1->2->5.
 * - Given 1->1->1->2->3, return 2->3.
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
    	ListNode prev     = null;
    	
    	while (current != null) {
    		nextNode = current.next;
    		
    		boolean removeCurrentNode = false;
    		while (nextNode != null && current.val == nextNode.val) {
    			current.next = nextNode.next;
    			nextNode.next = null;
    			nextNode = current.next;
    			removeCurrentNode = true;
    		}
    		
    		if (removeCurrentNode) {
    			if (prev != null) {
        			prev.next = current.next;
        			current.next = null;
        			current = prev;
    			}
    			else {
    				head = current.next;
    				current.next = null;
    				current = head; 
    				prev = null;
    			}
    		}
    		else if (current != null) {
    			prev = current;
        		current = current.next;
    		}
    	}
    	
        return head;
    }
}
