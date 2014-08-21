package removedups2;

import org.junit.Assert;
import org.junit.Test;

import removedups2.Solution.ListNode;

public class SolutionTest {
	@Test
	public void test1() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		n1.next = n2;
		n2.next = n3;
		Solution solution = new Solution();
		ListNode result = solution.deleteDuplicates(n1);
		Assert.assertEquals(n3, result);
	}
	
	@Test
	public void test2() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		Solution solution = new Solution();
		ListNode result = solution.deleteDuplicates(n1);
		Assert.assertEquals(n3, result);
		Assert.assertNull(n3.next);
	}
	
	@Test
	public void test3() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		
		n1.next = n2;
		
		Solution solution = new Solution();
		ListNode result = solution.deleteDuplicates(n1);
		Assert.assertNotNull(result);
		Assert.assertEquals(n1, result);
		Assert.assertEquals(n2, n1.next);
	
	}
	
	@Test
	public void test4() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(1);
		
		n1.next = n2;
		n2.next = n3;
		
		Solution solution = new Solution();
		ListNode result = solution.deleteDuplicates(n1);
		Assert.assertNull(result);
	}
	

	@Test
	public void test5() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(2);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		
		Solution solution = new Solution();
		ListNode result = solution.deleteDuplicates(n1);
		Assert.assertNull(result);
	}
}
