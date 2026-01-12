package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2016.
 * #0025 https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {

    // time O(n), space O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        // 统计链表中节点总数
        int cnt = 0;
        for (ListNode node = head; node != null; node = node.next) {
            cnt++;
        }
        // 计算需要反转的长度为 k 的节点组数
        cnt /= k;
        ListNode root = new ListNode(-1, head), // dummy 根结点
                last = root, // 上一组的尾节点
                curr = head, // 当前节点
                prev = null; // 前一个节点
        while (cnt-- > 0) {
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev; // 反转
                prev = curr;
                curr = next;
            }
            // 此时 curr 指向下一组的首节点 而 prev 指向当前组的原来的尾节点 (反转后的首节点)
            ListNode nxt = last.next; // nxt 指向当前组内原来的首节点 (反转后的尾节点)
            last.next.next = curr; // 将组内原来的首节点 (反转后的尾节点) 的 next 指针指向下一组首节点
            last.next = prev; // 将上一组的尾节点的 next 指针指向 prev (反转后的首节点)
            last = nxt; // 更新 last 指向 nxt (反转后的尾节点)
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
