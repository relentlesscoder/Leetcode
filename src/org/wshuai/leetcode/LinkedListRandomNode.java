package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 11/18/2016.
 * #0382 https://leetcode.com/problems/linked-list-random-node/
 */
public class LinkedListRandomNode {

	private LinkedListNode head;
	private Random random;

	/**
	 * @param head The linked list's head.
	 *             Note that the head is guaranteed to be not null, so it contains at least one node.
	 */
	public LinkedListRandomNode(LinkedListNode head) {
		this.head = head;
		this.random = new Random();
	}

	/**
	 * Returns a random node's value.
	 */
	// time O(n)
	// reservoir sampling http://www.geeksforgeeks.org/reservoir-sampling/
	public int getRandom() {
		int result = 0;
		LinkedListNode curr = head;

		for (int i = 1; curr != null; i++) {
			if (random.nextInt(i) == 0) {
				result = curr.val;
			}
			curr = curr.next;
		}
		return result;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
