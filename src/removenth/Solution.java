package removenth;

import java.util.Stack;

/**
 * https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example, given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class Solution {
	
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) {
		 	val = x;
		 	next = null;
		 }
	}
	 
	public ListNode removeNthFromEnd(ListNode head, int n) {
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode current = head;
		while (current != null) {
			stack.add(current);
			current = current.next;
		}
		
		ListNode beforeNthNode = null;
		ListNode nthNode = null;
		if (n == stack.size()) {
			return head.next;
		}
		
		int i = n + 1;
		while (i > 0) {
			beforeNthNode = stack.pop();
			i--;
		}
		
		nthNode = beforeNthNode.next;
		beforeNthNode.next = nthNode.next;
		
        return head;
    }
}
