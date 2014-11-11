package mergesortedlist;

import org.junit.Test;

import common.ListNode;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		n1.next = n2;
		ListNode n3 = new ListNode(3);
		n2.next = n3;
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n4.next = n5;
		ListNode n6 = new ListNode(6);
		n5.next = n6;
		
		ListNode result = solution.mergeTwoLists(n1, n4);
		System.out.println(result);
	}
	
	@Test
	public void test2() {
		Solution solution = new Solution();
		ListNode n1 = null;
		
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(2);
		n4.next = n5;
		ListNode n6 = new ListNode(4);
		n5.next = n6;
		
		ListNode result = solution.mergeTwoLists(n1, n4);
		System.out.println(result);
	}
	
	@Test
	public void test3() {
		Solution solution = new Solution();
		ListNode n1 = null;
		ListNode n4 = new ListNode(0);
		
		ListNode result = solution.mergeTwoLists(n1, n4);
		System.out.println(result);
	}
	
	@Test
	public void test4() {
		Solution solution = new Solution();
		ListNode n1 = new ListNode(2);
		ListNode n4 = new ListNode(1);
		
		ListNode result = solution.mergeTwoLists(n1, n4);
		System.out.println(result);
	}
}
