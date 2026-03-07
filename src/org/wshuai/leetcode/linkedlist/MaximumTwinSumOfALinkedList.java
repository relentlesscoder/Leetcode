package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 09/18/2023.
 * #2130 https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
 */
public class MaximumTwinSumOfALinkedList {

    // time O(n), space O(1)
    public int pairSum(ListNode head) {
        int res = 0;
        ListNode fast = head, slow = head, prev = null, curr = head;
        // 用快慢指针找到中间(靠右)节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转前半部分链表
        while (curr != slow) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 依次计算每一对孪生和
        while (slow != null) {
            res = Math.max(res, slow.val + prev.val);
            slow = slow.next;
            prev = prev.next;
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     **/
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
