package reorderlist;

import org.junit.Assert;
import org.junit.Test;

import reorderlist.Solution.ListNode;

public class SolutionTest {
	@Test
	public void test1() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		
		ListNode head = l1;
		Solution solution = new Solution();
		solution.reorderList(head);
		// 1 -> 4 -> 2 -> 3
		Assert.assertEquals(1, head.val);
		Assert.assertEquals(l4, head.next);
		Assert.assertEquals(l2, l4.next);
		Assert.assertEquals(l3, l2.next);
		Assert.assertEquals(null, l3.next);
	}
	
	@Test
	public void test2() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		
		ListNode head = l1;
		Solution solution = new Solution();
		solution.reorderList(head);
		// 1 -> 4 -> 2 -> 3
		Assert.assertEquals(1, head.val);
		Assert.assertEquals(l5, head.next);
		Assert.assertEquals(l2, l5.next);
		Assert.assertEquals(l4, l2.next);
		Assert.assertEquals(l3, l4.next);
		Assert.assertEquals(null, l3.next);
	}
	
	@Test
	public void test3() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		
		l1.next = l2;
		
		ListNode head = l1;
		Solution solution = new Solution();
		solution.reorderList(head);
		// 1 -> 2
		Assert.assertEquals(1, head.val);
		Assert.assertEquals(l2, head.next);
		Assert.assertEquals(null, l2.next);
	}
	
	@Test
	public void test4() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		
		ListNode head = l1;
		Solution solution = new Solution();
		solution.reorderList(head);
		// 1 -> 3 -> 2
		Assert.assertEquals(1, head.val);
		Assert.assertEquals(l3, head.next);
		Assert.assertEquals(l2, l3.next);
		Assert.assertEquals(null, l2.next);
	}
}
