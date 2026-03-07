package org.wshuai.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/22/2019.
 * #0817 https://leetcode.com/problems/linked-list-components/
 */
public class LinkedListComponents {

    // time O(n), space O(n)
    public int numComponents(ListNode head, int[] nums) {
		// 类似并查集的做法，连通相邻两个节点如果它们都在 nums 中。
        int n = nums.length, res = n;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (head != null) {
            if (head.next != null
					&& set.contains(head.val)
					&& set.contains(head.next.val)) {
                res--;
            }
            head = head.next;
        }
        return res;
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
