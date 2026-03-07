package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 06/14/2020.
 * #1474 https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
 */
public class DeleteNNodesAfterMNodesOfALinkedList {

    // time O(n), space O(1)
    public ListNode deleteNodesSinglePointer(ListNode head, int m, int n) {
        ListNode root = new ListNode(-1, head), // dummy 根结点
				cur = head, // 当前节点
				prev = root; // 前一个节点
        for (int x = m, y = n; cur != null; x = m, y = n) {
			// 先走 m 步，走完 prev 指向本次第一个要删除的节点前面的节点
            while (x-- > 0 && cur != null) {
                prev = cur;
                cur = cur.next;
            }
			// 再走 n 步，走完 curr 指向下一个要保留的节点
            while (y-- > 0 && cur != null) {
                cur = cur.next;
            }
			// 执行删除操作。注意一个特殊情况是连 m 步都没走完则最后 cur 为 null，
			// 那 prev 正好是最后一个节点，所以下面将 prev 的 next 指针指向 null
			// 的操作也是正确的。
			prev.next = cur;
        }
        return root.next;
    }

    // time O(n), space O(1)
    public ListNode deleteNodesTwoPointers(ListNode head, int m, int n) {
        ListNode root = new ListNode(-1, head), // dummy 根结点
				right = head, // 前指针
				left = head, // 后指针
				prev = root; // 前一个节点
		for (int x = m, y = n; right != null; x = m, y = n) {
			// 前节点先走 n 步
            while (y-- > 0 && right != null) {
                right = right.next;
            }
			// 两个节点一起走 m 步
            while (x-- > 0 && left != null) {
                right = right == null ? null : right.next;
                prev = left;
                left = left.next;
            }
			// 执行删除操作
			prev.next = right;
			// 对齐前后指针
			left = right;
        }
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
