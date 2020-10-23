package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2016.
 * #0025 https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {

    // time O(n)
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode root = new ListNode(0), prev = root, tail = head;
		root.next = head;
		int i = 0;
		while(tail != null){
			if(++i == k){
				prev = reverse(prev, tail);
				tail = prev;
				i = 0;
			}
			tail = tail.next;
		}
		return root.next;
	}

	private ListNode reverse(ListNode prev, ListNode tail){
		ListNode next = tail.next, cur = prev.next, res = cur;
		while(next != tail){
			ListNode temp = cur.next;
			cur.next = next;
			next = cur;
			cur = temp;
		}
		prev.next = tail;
		return res;
	}

    //Definition for singly-linked list.
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
