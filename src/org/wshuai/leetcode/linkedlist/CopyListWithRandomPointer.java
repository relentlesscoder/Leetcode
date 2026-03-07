package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 01/17/2020.
 * #0138 https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    // time O(n), space O(1)
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 为链表中每个节点复制一个具有相同值的新节点，将该节点加入到当前节点后形成交错链表。
        // 比如 1 -> 2 -> 3 -> null 变成 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null。
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = next;
            curr = next;
        }
        // 遍历每个旧节点，将新节点的 random 设为旧节点 random 指向的节点的下一个节点 -
        // 因为在交错链表中，每个节点的新节点就是他的下一个节点。
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        // 将新旧链表分离
        Node root = new Node(0), // 新链表的 dummy 根结点
                tail = root; // 新链表当前的尾节点
        curr = head;
        // 遍历每个旧节点
        while (curr != null) {
            tail.next = curr.next; // 将新链表的下一个节点设为当前旧节点的下一个节点
            tail = tail.next; // 更新新链表的尾节点
            curr.next = curr.next.next; // 将旧节点的下一个节点还原为原链表中的下一个节点
            curr = curr.next; // 更新当前节点
            tail.next = null; // 将新链表尾节点设为空
        }
        return root.next;
    }

    /**
     * Definition for a Node.
     */
    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
