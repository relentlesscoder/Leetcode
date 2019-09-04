package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/19.
 * #707 https://leetcode.com/problems/design-linked-list/
 */
public class DesignLinkedList {
    private LinkedListNode root = new LinkedListNode(-1);
    private int size = 0;

    /** Initialize your data structure here. */
    public DesignLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index >= size){
            return -1;
        }
        LinkedListNode curr = root;
        int i = 0;
        while(i <= index){
            curr = curr.next;
            i++;
        }
        return curr.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        LinkedListNode head = new LinkedListNode(val);
        head.next = root.next;
        root.next = head;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        LinkedListNode curr = root;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = new LinkedListNode(val);
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        LinkedListNode curr = root;
        int i = 0;
        while(i < index){
            curr = curr.next;
            i++;
        }
        LinkedListNode insert = new LinkedListNode(val);
        insert.next = curr.next;
        curr.next = insert;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        LinkedListNode prev = null;
        LinkedListNode curr = root;
        int i = 0;
        while(i <= index){
            prev = curr;
            curr = curr.next;
            i++;
        }
        prev.next = curr.next;
        size--;
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
