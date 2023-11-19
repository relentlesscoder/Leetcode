package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Wei on 10/31/2023.
 * #2034 https://leetcode.com/problems/stock-price-fluctuation/
 */
public class StockPriceFluctuation {

	// time O(n * log(n)), space O(n)
	private class StockPrice {

		private int currentTime;
		private Map<Integer, Integer> priceAtTime;
		private PriorityQueue<int[]> minHeap, maxHeap;

		public StockPrice() {
			currentTime = 0;
			priceAtTime = new HashMap<>();
			minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
			maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		}

		public void update(int timestamp, int price) {
			currentTime = Math.max(currentTime, timestamp);
			priceAtTime.put(timestamp, price);
			minHeap.offer(new int[] {price, timestamp});
			maxHeap.offer(new int[] {price, timestamp});
		}

		public int current() {
			return priceAtTime.get(currentTime);
		}

		public int maximum() {
			while (!maxHeap.isEmpty() && priceAtTime.get(maxHeap.peek()[1]) != maxHeap.peek()[0]) {
				maxHeap.poll();
			}
			return maxHeap.isEmpty() ? -1 : maxHeap.peek()[0];
		}

		public int minimum() {
			while (!minHeap.isEmpty() && priceAtTime.get(minHeap.peek()[1]) != minHeap.peek()[0]) {
				minHeap.poll();
			}
			return minHeap.isEmpty() ? -1 : minHeap.peek()[0];
		}
	}

	// time O(n * log(n)), space O(n)
	private class StockPriceSortedSet {

		private int currentTime;
		private Map<Integer, Integer> priceAtTime;
		private TreeMap<Integer, Integer> priceFreq;

		public StockPriceSortedSet() {
			currentTime = 0;
			priceAtTime = new HashMap<>();
			priceFreq = new TreeMap<>();
		}

		public void update(int timestamp, int price) {
			currentTime = Math.max(currentTime, timestamp);
			if (priceAtTime.containsKey(timestamp)) {
				int prevPrice = priceAtTime.get(timestamp), freq = priceFreq.get(prevPrice);
				if (freq > 1) {
					priceFreq.put(prevPrice, freq - 1);
				} else {
					priceFreq.remove(prevPrice);
				}
			}
			priceAtTime.put(timestamp, price);
			priceFreq.put(price, priceFreq.getOrDefault(price, 0) + 1);
		}

		public int current() {
			return priceAtTime.get(currentTime);
		}

		public int maximum() {
			return priceFreq.lastKey();
		}

		public int minimum() {
			return priceFreq.firstKey();
		}
	}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
}
