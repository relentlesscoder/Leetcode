package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2026.
 * #2674 https://leetcode.com/problems/split-a-circular-linked-list/
 */
public class SplitACircularLinkedList {

    // time O(n), space O(1)
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode[] res = new ListNode[2];
        int cnt = 0;
        ListNode fast = list, slow = list, last = list, prev = null;
        // 遍历链表找到最后一个节点并统计节点数
        while (last.next != list) {
            cnt++;
            last = last.next;
        }
        // 把循环链表断开
        last.next = null;
        // 快慢指针找到中间节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        res[0] = list;
        // 根据节点总数的奇偶性决定断点，注意 cnt 等于 总节点数 - 1。
        if (cnt % 2 == 1) {
            prev.next = list;
            last.next = slow;
            res[1] = slow;
        } else {
            last.next = slow.next;
            slow.next = list;
            res[0] = list;
            res[1] = last.next;
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
