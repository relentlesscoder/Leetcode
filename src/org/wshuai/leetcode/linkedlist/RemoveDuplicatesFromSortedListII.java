package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/10/2016.
 * #0082 https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedListII {

	// time O(n), space O(1)
	public ListNode deleteDuplicates(ListNode head) {
		// 一次遍历
		ListNode root = new ListNode(-1), curr = head, prev = root;
		root.next = head;
		while (curr != null) {
			ListNode next = curr.next;
			// 找到所有重复的元素
			while (next != null && curr.val == next.val) {
				next = next.next;
			}
			// 如果发现重复元素，则直接将前面节点的 next 指针指向后面一个节点。
			if (curr.next != next) {
				prev.next = next;
				curr.next = null;
			} else {
				prev = curr;
			}
			curr = next;
		}
		return root.next;
	}

    // time O(n), space O(MAX)
    public ListNode deleteDuplicatesFrequencyMap(ListNode head) {
		// 两次遍历，第一遍统计每个元素出现的频率第二遍删除重复元素。
        int[] freq = new int[201];
        ListNode root = new ListNode(-1), curr = head, prev = root;
        root.next = head;
        while (curr != null) {
            freq[curr.val + 100]++;
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (freq[curr.val + 100] > 1) {
                prev.next = next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = next;
        }
        return root.next;
    }

    /**
     * Definition for singly-linked list.
     **/
    private static class ListNode {
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
