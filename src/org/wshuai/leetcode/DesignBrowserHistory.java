package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 06/07/2020.
 * #1472 https://leetcode.com/problems/design-browser-history/
 */
public class DesignBrowserHistory {

	// time O(n), space O(n * L)
	private static class BrowserHistoryDynamicArray {

		private int curr;
		private int size;
		private List<String> links;

		public BrowserHistoryDynamicArray(String homepage) {
			links = new ArrayList<>();
			links.add(homepage);
			curr = 0;
			size = 1;
		}

		// time O(1)
		public void visit(String url) {
			curr++;
			if (curr < links.size()) {
				links.set(curr, url);
			} else {
				links.add(url);
			}
			size = curr + 1;
		}

		// time O(1)
		public String back(int steps) {
			curr = Math.max(0, curr - steps);
			return links.get(curr);
		}

		// time O(1)
		public String forward(int steps) {
			curr = Math.min(size - 1, curr + steps);
			return links.get(curr);
		}
	}

	// time O(n * s), space O(n * L)
	private static class BrowserHistoryDoublyLinkedList {

		private DoublyLinkedList root;
		private DoublyLinkedList curr;

		public BrowserHistoryDoublyLinkedList(String homepage) {
			root = new DoublyLinkedList(homepage);
			curr = root;
		}

		// time O(1)
		public void visit(String url) {
			DoublyLinkedList node = new DoublyLinkedList(url);
			curr.next = node;
			node.prev = curr;
			curr = node;
		}

		// time O(s)
		public String back(int steps) {
			while (steps-- > 0 && curr.prev != null) {
				curr = curr.prev;
			}
			return curr.val;
		}

		// time O(s)
		public String forward(int steps) {
			while (steps-- > 0 && curr.next != null) {
				curr = curr.next;
			}
			return curr.val;
		}

		private static class DoublyLinkedList {
			private String val;
			private DoublyLinkedList prev;
			private DoublyLinkedList next;

			public DoublyLinkedList(String val) {
				this.val = val;
			}
		}
	}

	// time O(n * s), space O(n * L)
    private static class BrowserHistoryStack {

        private String curr;
        private Deque<String> forward;
        private Deque<String> backward;

        public BrowserHistoryStack(String homepage) {
            forward = new ArrayDeque<>();
            backward = new ArrayDeque<>();
            curr = homepage;
        }

		// time O(1)
        public void visit(String url) {
            backward.push(curr);
            curr = url;
            forward = new ArrayDeque<>();
        }

		// time O(s)
        public String back(int steps) {
            while (steps-- > 0 && !backward.isEmpty()) {
                forward.push(curr);
                curr = backward.pop();
            }
            return curr;
        }

		// time O(s)
        public String forward(int steps) {
            while (steps-- > 0 && !forward.isEmpty()) {
                backward.push(curr);
                curr = forward.pop();
            }
            return curr;
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
