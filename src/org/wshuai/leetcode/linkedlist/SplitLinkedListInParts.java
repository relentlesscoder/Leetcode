package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/16/2019.
 * #0725 https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListInParts {

    // time O(n), space O(1)
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int cnt = 0;
		// first 代表当前部分的头节点
        ListNode curr = head, prev = null, first = head;
		// 遍历链表，统计节点总数
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        int group = cnt / k, extra = cnt % k;
        curr = head;
        for (int i = 0; i < k; i++) {
			// 计算当前部分的大小
            int size = group + (extra - 1 >= i ? 1 : 0);
			// 把链表前进 size 步
            while (size-- > 0) {
                prev = curr;
                curr = curr.next;
            }
			// 将当前部分的第一个节点和前一部分的最后一个节点分开
			// 注意有可能出现空的情况
			// 示例1: head = [], k = 3 预期的结果是 [[],[],[]]
            if (prev != null) {
                prev.next = null;
            }
            res[i] = first;
			// 更新头节点
            first = curr;
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
