package mergesortedlist;

import common.ListNode;

/**
 * https://oj.leetcode.com/problems/merge-two-sorted-lists/
 * 
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * 
 */
public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode head = null;
        ListNode tail = null;
        while (curr1 != null && curr2 != null) {
        	ListNode smallest = (curr1.val < curr2.val ? curr1 : curr2);
        		
        	if (head == null) {
    			head = smallest;
    			tail = smallest;
    		}
    		else {
    			tail.next = smallest;
    			tail = smallest;
    		}
        	
        	if (curr1.val < curr2.val) {
        		curr1 = curr1.next;
        	}
        	else {
        		curr2 = curr2.next;
        	}
        }
        
        // if we didn't reach the end of l1
        if (curr1 != null) {
        	// append the rest of it
        	if (tail != null) {
        		tail.next = curr1;
        	}
        }
        if (curr2 != null) {    
        	// same if we didn't reach the end of l2
        	if (tail != null) {
        		tail.next = curr2;
        	} 	
        }
        
       
        
        return head;
    }
}
