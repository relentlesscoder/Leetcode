package org.wshuai.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/15/2019.
 * #0460 https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

    private static class LFUCacheDLL {

        private final int capacity;
        private int minFreqency;
        private final Map<Integer, DoublyLinkedListNode> valueMap;
        private final Map<Integer, DoublyLinkedListNode> freqMap;

        public LFUCacheDLL(int capacity) {
            this.capacity = capacity;
            this.minFreqency = 0;
            valueMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public int get(int key) {
            DoublyLinkedListNode node = getNode(key);
            return node != null ? node.val : -1;
        }

        public void put(int key, int value) {
            DoublyLinkedListNode node = getNode(key);
            if (node != null) {
                node.val = value; // 更新节点值
                return;
            }
            if (valueMap.size() == capacity) { // 容量已满
                DoublyLinkedListNode root = freqMap.get(minFreqency); // 找到当前最小频率对应的双链表
                DoublyLinkedListNode tail = root.prev; // 找到双链表中最久未使用的节点
                valueMap.remove(tail.key); // 从哈希表中删除
                remove(tail); // 从节点所在的链表中删除
                if (root.prev == root) { // 如果删除后双链表空了
                    freqMap.remove(minFreqency); // 从哈希表中删除这个频率
                }
            }
            node = new DoublyLinkedListNode(key, value);
            valueMap.put(key, node); // 将新节点加入哈希表
            addToFront(1, node); // 将新节点加到频率为 1 的双链表的队首
            minFreqency = 1; // 将当前最小频率设为 1
        }

        private DoublyLinkedListNode getNode(int key) {
            if (!valueMap.containsKey(key)) {
                return null;
            }
            DoublyLinkedListNode node = valueMap.get(key);
            remove(node); // 从含有节点的双链表删除节点
            DoublyLinkedListNode root = freqMap.get(node.freq); // 找到含有节点的双链表
            if (root.prev == root) { // 删除节点后该频率对应的双链表为空
                freqMap.remove(node.freq); // 将该频率从哈希表中删除
                if (minFreqency == node.freq) { // 如果该频率正好等于当前最小频率
                    minFreqency++; // 则最小频率加 1
                }
            }
            node.freq++; // 将节点的最小频率加 1
            addToFront(node.freq, node); // 把节点加到哈希表中下一个频率的双链表的队首
            return node;
        }

        private void remove(DoublyLinkedListNode node) {
            // 移除节点
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        private DoublyLinkedListNode newList() {
            // 新建一个双链表
            DoublyLinkedListNode head = new DoublyLinkedListNode(-1, -1);
            head.prev = head;
            head.next = head;
            return head;
        }

        private void addToFront(int freq, DoublyLinkedListNode node) {
            // 将 node 加到频率 freq 对应的双链表的队首
            DoublyLinkedListNode head = freqMap.computeIfAbsent(freq, k -> newList());
            node.prev = head;
            node.next = head.next;
            node.prev.next = node;
            node.next.prev = node;
        }

        private static class DoublyLinkedListNode {

            private int key;
            private int val;
            private int freq;
            private DoublyLinkedListNode prev;
            private DoublyLinkedListNode next;

            public DoublyLinkedListNode(int key, int val) {
                this.key = key;
                this.val = val;
                this.freq = 1;
                prev = null;
                next = null;
            }
        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
