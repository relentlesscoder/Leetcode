package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/18/2016.
 * #0146 https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

    // time O(n), space O(C)
    private static class LRUCacheDLL {

        private final int capacity;
        private final Map<Integer, DoublyLinkedListNode> map;
        private final DoublyLinkedListNode head;
        private final DoublyLinkedListNode tail;

        public LRUCacheDLL(int capacity) {
            this.capacity = capacity;
            head = new DoublyLinkedListNode(-1, -1);
            tail = new DoublyLinkedListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            DoublyLinkedListNode node = map.get(key);
            remove(node);
            addToFront(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
                map.put(key, node);
                addToFront(node);
                if (map.size() > capacity) {
                    map.remove(tail.prev.key);
                    remove(tail.prev);
                }
            } else {
                DoublyLinkedListNode node = map.get(key);
                node.val = value;
                remove(node);
                addToFront(node);
            }
        }

        private void remove(DoublyLinkedListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        private void addToFront(DoublyLinkedListNode node) {
            DoublyLinkedListNode next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
        }

        private static class DoublyLinkedListNode {
            private int key;
            private int val;
            private DoublyLinkedListNode prev;
            private DoublyLinkedListNode next;

            public DoublyLinkedListNode(int key, int val) {
                this.key = key;
                this.val = val;
                prev = null;
                next = null;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
