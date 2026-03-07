package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 09/22/2019.
 * #0430 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class FlattenAMultilevelDoublyLinkedList {

    // time O(n), space O(D)
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node[] res = flattenHelper(head);
        return res[0];
    }

    public Node[] flattenHelper(Node head) {
		// 遍历链表
        Node curr = head, tail = null;
        while (curr != null) {
            Node next = curr.next;
			// 如果当前节点有子节点
            if (curr.child != null) {
				// 递归扁平化子节点得到以子节点开始的双链表的头节点和尾节点
                Node[] nodes = flattenHelper(curr.child);
                Node head1 = nodes[0], tail1 = nodes[1];
				// 将子节点指针设为空
                curr.child = null;
				// 将当前节点和子节点相连
                curr.next = head1;
                head1.prev = curr;
				// 将尾节点和当前节点的下一个节点相连
                if (next != null) {
                    tail1.next = next;
                    next.prev = tail1;
                }
				// 更新尾节点
                tail = tail1;
            } else {
				// 更新尾节点
                tail = curr;
            }
            curr = next;
        }
        return new Node[]{head, tail};
    }

    /**
     * Definition for a Node.
     */
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
