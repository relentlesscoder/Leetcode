package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/27/2023.
 * #2336 https://leetcode.com/problems/smallest-number-in-infinite-set/
 */
public class SmallestNumberInInfiniteSet {

	// time O(n * log(n)), space O(n)
	private class SmallestInfiniteSet {

		private HashSet<Integer> popedNumbers;
		private PriorityQueue<Integer> popedNumbersMinQueue;
		private Integer currentMin;

		public SmallestInfiniteSet() {
			popedNumbers = new HashSet<>();
			popedNumbersMinQueue = new PriorityQueue<>();
			currentMin = 1;
		}

		public int popSmallest() {
			int smallest;
			if (!popedNumbersMinQueue.isEmpty()) { // if previously popped numbers are added back, it will be definitely less than the current min, so we need to poll from the min queue
				smallest = popedNumbersMinQueue.poll();
				popedNumbers.remove(smallest);
			} else {
				smallest = currentMin;
				currentMin++;
			}
			return smallest;
		}

		public void addBack(int num) {
			if (popedNumbers.contains(num) || currentMin <= num) { // if current min less or equal to num, no need to add it back since it will not have any affect
				return;
			}
			// added previously popped number back to the min queue
			popedNumbers.add(num);
			popedNumbersMinQueue.offer(num);
		}
	}

	/**
	 * Your SmallestInfiniteSet object will be instantiated and called as such:
	 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
	 * int param_1 = obj.popSmallest();
	 * obj.addBack(num);
	 */
}
