package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2016.
 * #0025 https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {

    // time O(n)
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode root = new ListNode(0), cur = head, lastEnd = root;
		root.next = head;
		for(int i = 0; cur != null; cur = cur.next){
			if(++i == k){
				lastEnd = reverse(lastEnd, cur);
				cur = lastEnd;
				i = 0;
			}
		}
		return root.next;
	}

	private ListNode reverse(ListNode lastEnd, ListNode curEnd){
		ListNode cur = lastEnd.next, res = cur, prev = curEnd.next;
		while(prev != curEnd){
			ListNode next = cur.next; // record next node
			cur.next = prev; // reverse
			prev = cur;
			cur = next;
		}
		lastEnd.next = curEnd;
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
