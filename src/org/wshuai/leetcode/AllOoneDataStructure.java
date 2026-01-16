package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 01/01/2020.
 * #0432 https://leetcode.com/problems/all-oone-data-structure/
 */
public class AllOoneDataStructure {

    // time O(n), space O(n)
    private static class AllOne {

        private final DoublyLinkedListNode head;
        private final DoublyLinkedListNode tail;
        private final Map<DoublyLinkedListNode, Set<String>> valueMap;
        private final Map<String, DoublyLinkedListNode> nodeMap;

        public AllOne() {
            head = new DoublyLinkedListNode(-1);
            tail = new DoublyLinkedListNode(-1);
            head.next = tail;
            tail.prev = head;
            valueMap = new HashMap<>();
            nodeMap = new HashMap<>();
        }

        // time O(1)
        public void inc(String key) {
            if (nodeMap.containsKey(key)) {
                // add to new freq node
                DoublyLinkedListNode node = nodeMap.get(key);
                DoublyLinkedListNode next = node.next;
                if (next.val != node.val + 1) {
                    next = new DoublyLinkedListNode(node.val + 1);
                    insertAfter(node, next);
                }
                valueMap.computeIfAbsent(next, k -> new HashSet<>()).add(key);
                nodeMap.put(key, next);
                // remove from existing freq node
                Set<String> set = valueMap.get(node);
                set.remove(key);
                if (set.isEmpty()) {
                    valueMap.remove(node);
                    remove(node);
                }
            } else {
                DoublyLinkedListNode next = head.next;
                if (next.val != 1) {
                    next = new DoublyLinkedListNode(1);
                    insertAfter(head, next);
                }
                valueMap.computeIfAbsent(next, k -> new HashSet<>()).add(key);
                nodeMap.put(key, next);
            }
        }

        // time O(1)
        public void dec(String key) {
            DoublyLinkedListNode node = nodeMap.get(key);
            if (node.val > 1) {
                DoublyLinkedListNode prev = node.prev;
                if (prev.val != node.val - 1) {
                    prev = new DoublyLinkedListNode(node.val - 1);
                    insertAfter(node.prev, prev);
                }
                valueMap.computeIfAbsent(prev, k -> new HashSet<>()).add(key);
                nodeMap.put(key, prev);
            } else {
                nodeMap.remove(key);
            }
            Set<String> set = valueMap.get(node);
            set.remove(key);
            if (set.isEmpty()) {
                valueMap.remove(node);
                remove(node);
            }
        }

        // time O(1)
        public String getMaxKey() {
            if (head.next == tail) {
                return "";
            }
            DoublyLinkedListNode node = tail.prev;
            return getNextKey(valueMap.get(node));
        }

        // time O(1)
        public String getMinKey() {
            if (head.next == tail) {
                return "";
            }
            DoublyLinkedListNode node = head.next;
            return getNextKey(valueMap.get(node));
        }

        private void insertAfter(DoublyLinkedListNode node,
                                 DoublyLinkedListNode newNode) {
            DoublyLinkedListNode next = node.next;
            node.next = newNode;
            newNode.prev = node;
            newNode.next = next;
            next.prev = newNode;
        }

        private void remove(DoublyLinkedListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        private String getNextKey(Set<String> set) {
            Iterator<String> itr = set.iterator();
            return itr.next();
        }

        private static class DoublyLinkedListNode {

            private int val;
            private DoublyLinkedListNode prev;
            private DoublyLinkedListNode next;

            public DoublyLinkedListNode(int val) {
                this.val = val;
                prev = null;
                next = null;
            }
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}
