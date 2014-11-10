package removenth;


import org.junit.Assert;
import org.junit.Test;

import removenth.Solution.ListNode;

public class SolutionTest {
	@Test
	public void test1() {
		Solution solution = new Solution();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		ListNode result = solution.removeNthFromEnd(n1, 2);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.val);
		Assert.assertNotNull(result.next);
		Assert.assertEquals(2, result.next.val);
		Assert.assertNotNull(result.next.next);
		Assert.assertEquals(3, result.next.next.val);
	}
}
