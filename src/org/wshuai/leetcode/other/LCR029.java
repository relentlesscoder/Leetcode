package org.wshuai.leetcode.other;

/**
 * Created by Wei on 01/11/2026.
 * #LCR029 https://leetcode.cn/problems/4ueAj6/
 */
public class LCR029 {

    // time O(n), space O(1)
    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal);
        // 如果给定的节点是 null ，创建一个循环有序列表并返回这个节点。
        if (head == null) {
            insert.next = insert;
            return insert;
        }
        Node curr = head;
        // 找到头节点和尾节点，注意有可能输入的链表所有元素值都一样。
        while (curr.val <= curr.next.val && curr.next != head) {
            curr = curr.next;
        }
        Node prev = curr; // 尾节点 - 最大值
        curr = curr.next; // 头节点 - 最小值
        // 只有输入节点的值在最大值和最小值之间才需要遍历链表寻找插入点
        if (insertVal > curr.val && insertVal < prev.val) {
            while (curr.val < insertVal) {
                prev = curr;
                curr = curr.next;
            }
        }
        // 插入元素
        prev.next = insert;
        insert.next = curr;
        return head;
    }

    /**
     * Definition for a Node.
     */
    private static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
