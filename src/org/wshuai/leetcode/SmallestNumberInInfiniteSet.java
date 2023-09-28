package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/27/2023.
 * #2336 https://leetcode.com/problems/smallest-number-in-infinite-set/
 */
public class SmallestNumberInInfiniteSet {

	private HashSet<Integer> poppedNumbers;
	private PriorityQueue<Integer> poppedNumbersMinQueue;
	private Integer currentMin;

	public SmallestNumberInInfiniteSet() {
		poppedNumbers = new HashSet<>();
		poppedNumbersMinQueue = new PriorityQueue<>();
		currentMin = 1;
	}

	// time O(log(n)), space O(n)
	public int popSmallest() {
		int smallest;
		if (!poppedNumbersMinQueue.isEmpty()) { // if previously popped numbers are added back, it will be definitely less than the current min, so we need to poll from the min queue
			smallest = poppedNumbersMinQueue.poll();
			poppedNumbers.remove(smallest);
		} else {
			smallest = currentMin;
			currentMin++;
		}
		return smallest;
	}

	// time O(log(n)), space O(n)
	public void addBack(int num) {
		if (poppedNumbers.contains(num) || currentMin <= num) { // if current min less or equal to num, no need to add it back since it will not have any affect
			return;
		}
		// added previously popped number back to the min queue
		poppedNumbers.add(num);
		poppedNumbersMinQueue.offer(num);
	}
}
