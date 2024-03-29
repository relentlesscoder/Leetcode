package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/04/2019.
 * #0426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

	// time O(n)
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        Node left = treeToDoublyList(root.left);
        Node right = treeToDoublyList(root.right);
        root.left = root;
        root.right = root;
        return connect(connect(left, root), right);
    }

    private Node connect(Node n1, Node n2){
        if(n1 == null || n2 == null){
            return n1 == null ? n2 : n1;
        }
        Node tail1 = n1.left, tail2 = n2.left; // use head element's left pointer to find tail
        tail1.right = n2;
        n2.left = tail1; // connect tail of left part to head of right part
        tail2.right = n1;
        n1.left = tail2; // connect tail of right part to head of left part
        return n1;
    }

	// time O(n), space O(n)
    public Node treeToDoublyListInorder(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root, prev = null, head = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node pred = stack.pop();
                if (pred.val < head.val) { // find head of the linked list
                    head = pred;
                }
                cur = pred.right;
                if (prev != null) {
                    prev.right = pred;
                    pred.left = prev;
                }
                prev = pred;
            }
        }
        head.left = prev;
        prev.right = head;
        return head;
    }

    // Definition for a Node.
    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
