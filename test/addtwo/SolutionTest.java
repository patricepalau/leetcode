package addtwo;

import org.junit.Assert;
import org.junit.Test;

import addtwo.Solution.ListNode;

public class SolutionTest {
	@Test
	public void test1() {
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(4);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		
		ListNode l4 = new ListNode(5);
		ListNode l5 = new ListNode(6);
		ListNode l6 = new ListNode(4);
		l4.next = l5;
		l5.next = l6;
		Solution solution = new Solution();
		ListNode result = solution.addTwoNumbers(l1, l4);
		Assert.assertNotNull(result);
		Assert.assertEquals(7, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(8, result.val);
	}
	
	@Test
	public void test2() {
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(4);
		l1.next = l2;
		
		ListNode l4 = new ListNode(5);
		ListNode l5 = new ListNode(6);
		ListNode l6 = new ListNode(4);
		l4.next = l5;
		l5.next = l6;
		
		Solution solution = new Solution();
		ListNode result = solution.addTwoNumbers(l1, l4);
		Assert.assertNotNull(result);
		Assert.assertEquals(7, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.val);
	}
	
	@Test
	public void test3() {
		ListNode l1 = new ListNode(5);
		ListNode l2 = new ListNode(5);
		
		Solution solution = new Solution();
		ListNode result = solution.addTwoNumbers(l1, l2);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.val);
	}
	
	@Test
	public void test4() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(9);
		ListNode l3 = new ListNode(9);
		l2.next = l3;
		
		Solution solution = new Solution();
		ListNode result = solution.addTwoNumbers(l1, l2);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.val);
		result = result.next;
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.val);
	}
}
