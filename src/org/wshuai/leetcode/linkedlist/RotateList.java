package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/09/2016.
 * #0061 https://leetcode.com/problems/rotate-list/
 */
public class RotateList {

	// time O(n), space O(1)
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode root = new ListNode(-1, head), // dummy 根结点
				right = head, // 前指针
				left = head, // 后指针
				curr = head; // 当前节点
		// 统计节点总数
        int cnt = 0;
        while (curr != null) {
            curr = curr.next;
            cnt++;
        }
        k %= cnt; // 因为 k 可以很大，计算 k % cnt 的值
		if (k == 0) {
			return head;
		}
		// 前指针先走 k 步
        while (k-- > 0) {
            right = right.next;
        }
		// 两个指针一起走，走完 left 指向旋转后的最后一个节点
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
		// left.next 是旋转后的第一个节点，将 root 的 next 指针指向它
        root.next = left.next;
		// 将 left.next 指针设为空
        left.next = null;
		// 将原尾节点的 next 指针指向原头节点
        right.next = head;
        return root.next;
    }

    /**
     * Definition for singly-linked list.
     */
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
