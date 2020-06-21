package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Wei on 05/02/2020.
 * #1429 https://leetcode.com/problems/first-unique-number/
 */
public class FirstUniqueNumber {

    private DoublyLinkedListNode head, tail;

    private Map<Integer, DoublyLinkedListNode> map;

    public FirstUniqueNumber(int[] nums) {
        map = new HashMap<>();
        LinkedHashMap<Integer, Integer> count = new LinkedHashMap<>();
        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        head = new DoublyLinkedListNode(-1);
        tail = new DoublyLinkedListNode(-1);
        DoublyLinkedListNode cur = head;
        for(Map.Entry<Integer, Integer> entry : count.entrySet()){
            if(entry.getValue() > 1){
                map.put(entry.getKey(), new DoublyLinkedListNode(-1));
                continue;
            }
            int num = entry.getKey();
            DoublyLinkedListNode next = new DoublyLinkedListNode(num);
            cur.next = next;
            next.prev = cur;
            cur = next;
            map.put(num, next);
        }
        cur.next = tail;
        tail.prev = cur;
    }

    public int showFirstUnique() {
        if(head.next == tail){
            return -1;
        }
        return head.next.val;
    }

    public void add(int value) {
        if(!map.containsKey(value)){
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(value);
            newNode.next = tail;
            tail.prev.next = newNode;
            newNode.prev = tail.prev;
            tail.prev = newNode;
            map.put(value, newNode);
        }else{
            DoublyLinkedListNode node = map.get(value);
            if(node.val != -1){
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.val = -1;
            }
        }
    }

    private class DoublyLinkedListNode{

        int val;

        DoublyLinkedListNode prev;

        DoublyLinkedListNode next;

        public DoublyLinkedListNode(int val){
            this.val = val;
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
