package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2023.
 * #1756 https://leetcode.com/problems/design-most-recently-used-queue/
 */
public class DesignMostRecentlyUsedQueue {

	private class MRUQueue {

		private DoublyLinkedListNode[] nodes;

		private int bucket;

		// time O(n), space O(n)
		public MRUQueue(int n) {
			this.bucket = (int) Math.sqrt(n);
			nodes = new DoublyLinkedListNode[(n + bucket - 1) / bucket]; // the number of buckets is ceiling of sqrt(n)
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = new DoublyLinkedListNode(-1); // add a sentinel node as the head of each bucket
			}
			for (int i = 1; i <= n; i++) {
				// add actual nodes
				// for example, if n = 10, bucket = 4
				// the first bucket has below cyclic doubly linked list:
				// sentinel: val -> -1 next -> node 0  prev -> node 2
				// node 0: val -> 1 next -> node 1  prev -> sentinel
				// node 1: val -> 2 next -> node 2  prev -> node 0
				// node 2: val -> 3 next -> node sentinel  prev -> node 1
				nodes[(i - 1) / bucket].prev.append(new DoublyLinkedListNode(i));
			}
		}

		// time O(sqrt(n)), space O(1)
		public int fetch(int k) {
			DoublyLinkedListNode target = nodes[(k - 1) / bucket].next; // find the first node in the bucket
			for (int i = (k - 1) % bucket; i > 0; i--) { // use the index to find the node in the bucket
				target = target.next;
			}
			target.remove(); // remove the node
			for (int i = 1 + (k - 1) / bucket; i < nodes.length; ++i) { // re-balance the buckets by moving the first node of the next bucket to the current
				nodes[i - 1].prev.append(nodes[i].next.remove());
			}
			nodes[nodes.length - 1].prev.append(target); // append the node to the last bucket
			return target.val;
		}

		private class DoublyLinkedListNode {

			private int val;

			private DoublyLinkedListNode prev, next;

			public DoublyLinkedListNode(int val) {
				this.val = val;
				this.prev = this;
				this.next = this;
			}

			public void append(DoublyLinkedListNode node) {
				DoublyLinkedListNode temp = next;
				next = node;
				node.prev = this;
				node.next = temp;
				temp.prev = node;
			}

			public DoublyLinkedListNode remove() {
				this.prev.next = next;
				this.next.prev = prev;
				this.prev = this;
				this.next = this;
				return this;
			}
		}
	}

	/**
	 * Your MRUQueue object will be instantiated and called as such:
	 * MRUQueue obj = new MRUQueue(n);
	 * int param_1 = obj.fetch(k);
	 */
}
