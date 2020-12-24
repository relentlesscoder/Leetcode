package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2020.
 * #1670 https://leetcode.com/problems/design-front-middle-back-queue/
 */
public class DesignFrontMiddleBackQueue {

    private int size;
    private DoublyLinkedList head, tail, middle;

    public DesignFrontMiddleBackQueue() {
        size = 0;
        head = new DoublyLinkedList(0);
        tail = new DoublyLinkedList(0);
        middle = null;
        head.next = tail;
        tail.prev = head;
    }

    public void pushFront(int val) {
        DoublyLinkedList node = new DoublyLinkedList(val);
        insert(head, head.next, node);
        if(size++ == 0){
            middle = node;
            return;
        }
        if(size % 2 == 0){
            middle = middle.prev;
        }
    }

    public void pushMiddle(int val) {
        DoublyLinkedList node = new DoublyLinkedList(val);
        if(size++ == 0){
            insert(head, tail, node);
            middle = node;
            return;
        }
        if(size % 2 == 0){
            insert(middle.prev, middle, node);
            middle = middle.prev;
        }else{
            insert(middle, middle.next, node);
            middle = middle.next;
        }
    }

    public void pushBack(int val) {
        DoublyLinkedList node = new DoublyLinkedList(val);
        insert(tail.prev, tail, node);
        if(size++ == 0){
            middle = node;
            return;
        }
        if(size % 2 == 1){
            middle = middle.next;
        }
    }

    public int popFront() {
        if(size == 0){
            return -1;
        }
        int res = head.next.val;
        if(size % 2 == 0){
            middle = middle.next;
        }
        remove(head.next);
        if(--size == 0){
            middle = null;
        }
        return res;
    }

    public int popMiddle() {
        if(size == 0){
            return -1;
        }
        int res = middle.val;
        DoublyLinkedList temp = size % 2 == 0 ? middle.next : middle.prev;
        remove(middle);
        middle = --size == 0 ? null : temp;
        return res;
    }

    public int popBack() {
        if(size == 0){
            return -1;
        }
        int res = tail.prev.val;
        if(size % 2 == 1){
            middle = middle.prev;
        }
        remove(tail.prev);
        if(--size == 0){
            middle = null;
        }
        return res;
    }

    private void insert(DoublyLinkedList front, DoublyLinkedList back, DoublyLinkedList node){
        front.next = node;
        node.prev = front;
        node.next = back;
        back.prev = node;
    }

    private void remove(DoublyLinkedList node){
        DoublyLinkedList front = node.prev, back = node.next;
        front.next = back;
        back.prev = front;
    }

    private class DoublyLinkedList{

        private int val;
        private DoublyLinkedList prev;
        private DoublyLinkedList next;

        private DoublyLinkedList(int val){
            this.val = val;
        }
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
