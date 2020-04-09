package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2019.
 * #0707 https://leetcode.com/problems/design-linked-list/
 */
public class DesignLinkedList {
	private ListNode head = null;
	private int size = 0;

	/** Initialize your data structure here. */
	public DesignLinkedList() {
	}

	/** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	public int get(int index) {
		if(index < 0 || index >= size){
			return -1;
		}
		ListNode cur = head;
		while(index-- > 0){
			cur = cur.next;
		}
		return cur.val;
	}

	/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	public void addAtHead(int val) {
		ListNode cur = new ListNode(val);
		if(size > 0){
			cur.next = head;
		}
		head = cur;
		size++;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		ListNode cur = new ListNode(val);
		if(size == 0){
			head = cur;
			size++;
			return;
		}
		ListNode tail = head;
		while(tail.next != null){
			tail = tail.next;
		}
		tail.next = cur;
		size++;
	}

	/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
	public void addAtIndex(int index, int val) {
		if(index > size){
			return;
		}
		if(index == size){
			addAtTail(val);
		}else if(index == 0){
			addAtHead(val);
		}else{
			int i = 0;
			ListNode cur = head;
			while(i++ < index - 1){
				cur = cur.next;
			}
			ListNode next = cur.next;
			cur.next = new ListNode(val);
			cur.next.next = next;
			size++;
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if(index < 0 || index >= size){
			return;
		}
		if(index == 0){
			head = head.next;
		}else{
			int i = 0;
			ListNode cur = head;
			while(i++ < index - 1){
				cur = cur.next;
			}
			cur.next = cur.next.next;
		}
		size--;
	}

	private class ListNode{
		int val;

		ListNode next;

		public ListNode(int val){
			this.val = val;
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
