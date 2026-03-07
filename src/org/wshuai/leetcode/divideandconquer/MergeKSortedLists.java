package org.wshuai.leetcode.divideandconquer;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/11/2016.
 * #0023 https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {

    // time O(L * log(n)), space O(n)
    public ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        // 用最小堆存链表头节点，每次弹出最小的头节点将其加入结果链表的末端并将后面的
        // 节点 (如果不为空) 加回到堆中。
        PriorityQueue<ListNode> minQueue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                minQueue.offer(node);
            }
        }
        ListNode root = new ListNode(0), curr = root;
        while (!minQueue.isEmpty()) {
            ListNode min = minQueue.poll(), next = min.next;
            min.next = null;
            curr.next = min;
            curr = curr.next;
            if (next != null) {
                minQueue.offer(next);
            }
        }
        return root.next;
    }

    // time O(L * log(n)), space O(1)
    public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        // 分治层层合并每两个相邻的排序后的链表
        if (lists.length == 0) {
            return null;
        }
        int n = lists.length;
        return merge(lists, 0, n - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode list1 = merge(lists, left, mid);
        ListNode list2 = merge(lists, mid + 1, right);
        return mergeTwoLists(list1, list2); // O(L)
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // #0021
        ListNode root = new ListNode(0), curr = root;
        while (list1 != null || list2 != null) {
            int v1 = list1 == null ? Integer.MAX_VALUE : list1.val;
            int v2 = list2 == null ? Integer.MAX_VALUE : list2.val;
            if (v1 < v2) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
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
