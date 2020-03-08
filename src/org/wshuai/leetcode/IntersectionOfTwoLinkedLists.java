package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0160 https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
	// time O(n)
	// https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
	public LinkedListNode getIntersectionNode(LinkedListNode headA, LinkedListNode headB) {
		if(headA == null && headB == null){
			return null;
		}
		LinkedListNode a = headA, b = headB;
		while(a != b){
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		return a;
	}
}
