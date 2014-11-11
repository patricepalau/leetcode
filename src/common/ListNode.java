package common;

public class ListNode {
	 public int val;
	 public ListNode next;
	 public ListNode(int x) {
	 	val = x;
	 	next = null;
	 }
	 public String toString() {
		 return val + " -> " + (next == null ? "null" : next.toString());
	 }
}
