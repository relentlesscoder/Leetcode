package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2019.
 * #0707 https://leetcode.com/problems/design-linked-list/
 */
public class DesignLinkedList {

    private static class MyLinkedList {

        private ListNode root;

        public MyLinkedList() {
            root = new ListNode(0);
        }

        // time O(n), space O(1)
        public int get(int index) {
            int i = 0;
            ListNode head = root.next;
            while (head != null && i < index) {
                head = head.next;
                i++;
            }
            return i == index && head != null ? head.val : -1;
        }

        // time O(1), space O(1)
        public void addAtHead(int val) {
            ListNode head = root.next;
            root.next = new ListNode(val, head);
        }

        // time O(n), space O(1)
        public void addAtTail(int val) {
            ListNode head = root.next, prev = root;
            while (head != null) {
                prev = head;
                head = head.next;
            }
            prev.next = new ListNode(val);
        }

        // time O(n), space O(1)
        public void addAtIndex(int index, int val) {
            int i = 0;
            ListNode head = root.next, prev = root;
            while (head != null && i < index) {
                prev = head;
                head = head.next;
                i++;
            }
            if (i == index) {
                prev.next = new ListNode(val, head);
            }
        }

        // time O(n), space O(1)
        public void deleteAtIndex(int index) {
            int i = 0;
            ListNode head = root.next, prev = root;
            while (head != null && i < index) {
                prev = head;
                head = head.next;
                i++;
            }
            if (i == index && head != null) {
                prev.next = head.next;
            }
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

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
