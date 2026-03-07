package org.wshuai.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/26/2023.
 * #2487 https://leetcode.com/problems/remove-nodes-from-linked-list/
 */
public class RemoveNodesFromLinkedList {

    // time O(n), space O(1)
    public ListNode removeNodes(ListNode head) {
		// 翻转链表，这样对每个节点只需要判断它是否是目前的最大值。如果不是则需要删除当前节点。
        ListNode reversed = reverse(head), curr = reversed.next, prev = reversed;
        int max = reversed.val;
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val < max) {
                prev.next = next;
                curr.next = null;
            } else {
                prev = curr;
            }
            max = Math.max(max, curr.val);
            curr = next;
        }
		// 再次翻转链表，注意第一个节点是不会被删的 (它前面不会有比它大的节点) 所以不要使用
		// dummy 节点
        return reverse(reversed);
    }

    private ListNode reverse(ListNode head) {
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // time O(n), space O(n)
    public ListNode removeNodesMonotonicStack(ListNode head) {
		// 利用单调栈
        ListNode root = new ListNode((int) 1e6);
        root.next = head;
        Deque<ListNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (head != null) {
			// 弹出栈顶所有小于当前节点值的节点
            while (stack.size() > 1 && stack.peek().val < head.val) {
                ListNode prev = stack.pop();
                prev.next = null;
            }
			// 将栈顶节点的 next 指针指向当前节点
            stack.peek().next = head;
            stack.push(head);
            head = head.next;
        }
        return root.next;
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
