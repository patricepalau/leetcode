package addtwo;

/**
 * https://oj.leetcode.com/problems/add-two-numbers/
 * 
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 */
public class Solution {
	/**
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
	static public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int carryOver = 0;
    	ListNode result = null;
    	ListNode head = null;
    	ListNode prev = null;
    	while (l1 != null || l2 != null) {
    		int term1 = l1 != null ? l1.val : 0;
    		int term2 = l2 != null ? l2.val : 0;
        	int interm = term1 + term2 + carryOver;
        	carryOver = 0;
        	
        	if (interm >= 10) {
        		interm -= 10;
        		carryOver = 1;
        	}
        	
        	prev = result;
        	result = new ListNode(interm);
        	if (head == null) {
        		head = result;
        	}
        	if (prev != null) {
        		prev.next = result;
        	}
        	prev = result;
        	
        	if (l1 != null) l1 = l1.next;
        	if (l2 != null) l2 = l2.next;
    	}
    	if (carryOver == 1) {
    		result = new ListNode(1);
    		prev.next = result;
    	}
    	
        return head;
    }
}
