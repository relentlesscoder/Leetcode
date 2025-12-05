package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/10/2019.
 * #0895 https://leetcode.com/problems/maximum-frequency-stack/
 */
public class MaximumFrequencyStack {

	// time O(n), space O(n)
	private static class FreqStack {

		private int maxFreq;
		private Map<Integer, Integer> freq;
		private Map<Integer, Deque<Integer>> stacks;

		public FreqStack() {
			maxFreq = 0;
			freq = new HashMap<>();
			stacks = new HashMap<>();
		}

		// time O(1)
		public void push(int val) {
			int count = freq.getOrDefault(val, 0) + 1;
			if (count > maxFreq) {
				maxFreq = count;
			}
			freq.put(val, count);
			stacks.computeIfAbsent(count, k -> new ArrayDeque<>()).push(val);
		}

		// time O(1)
		public int pop() {
			int res = stacks.get(maxFreq).pop();
			if (stacks.get(maxFreq).isEmpty()) {
				maxFreq--;
			}
			freq.put(res, freq.get(res) - 1);
			return res;
		}
	}
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
