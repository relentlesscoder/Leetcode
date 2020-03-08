package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2016.
 * #0234 https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
	// time O(n), space O(1)
	public boolean isPalindrome(LinkedListNode head) {
		if(head == null){
			return true;
		}
		LinkedListNode slow = head, fast = head, tail = null;
		// find the middle node
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		// reverse the right half and compare to the left half
		tail = reverse(fast == null ? slow : slow.next);
		fast = head;
		while(tail != null){
			if(tail.val != fast.val){
				return false;
			}
			tail = tail.next;
			fast = fast.next;
		}
		return true;
	}

	private LinkedListNode reverse(LinkedListNode head){
		LinkedListNode prev = null, cur = head;
		while(cur != null){
			LinkedListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}
