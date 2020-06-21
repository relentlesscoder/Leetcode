package org.wshuai.leetcode;

/**
 * Created by Wei on 06/07/2020.
 * #1472 https://leetcode.com/problems/design-browser-history/
 */
public class DesignBrowserHistory {

	private DoublyLinkedListNode cur, head, tail;

	public DesignBrowserHistory(String homepage) {
		head = new DoublyLinkedListNode(homepage);
		tail = new DoublyLinkedListNode("#");
		head.next = tail;
		tail.prev = head;
		cur = head;
	}

	public void visit(String url) {
		DoublyLinkedListNode node = new DoublyLinkedListNode(url);
		cur.next = node;
		node.prev = cur;
		node.next = tail;
		cur = node;
	}

	public String back(int steps) {
		while(steps-- > 0 && cur.prev != null){
			cur = cur.prev;
		}
		return cur.val;
	}

	public String forward(int steps) {
		while(steps-- > 0 && cur.next != tail){
			cur = cur.next;
		}
		return cur.val;
	}

	private class DoublyLinkedListNode{

		DoublyLinkedListNode prev, next;

		String val;

		public DoublyLinkedListNode(String val){
			this.val = val;
			prev = null;
			next = null;
		}
	}
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
