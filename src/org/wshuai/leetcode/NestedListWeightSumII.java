package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/01/2016.
 * #0364 https://leetcode.com/problems/nested-list-weight-sum-ii/
 */
public class NestedListWeightSumII {

	// time O(n), space O(n)
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int res = 0, sum = 0;
		Deque<NestedInteger> queue = new ArrayDeque<>();
		for (NestedInteger ni : nestedList) {
			queue.offer(ni);
		}
		while (!queue.isEmpty()) {
			int size = queue.size(), levelSum = 0;
			while (size-- > 0) {
				NestedInteger curr = queue.poll();
				if (curr.isInteger()) {
					levelSum += curr.getInteger();
				} else {
					for (NestedInteger ni : curr.getList()) {

						queue.offer(ni);
					}
				}
			}
			// The weight is decreased by 1 for each level. For example, is the max depth is 3, the 3, 2, 1 will be the weights from top to bottom
			sum += levelSum;
			res += sum;
		}
		return res;
	}

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	private class NestedInteger {
		// Constructor initializes an empty nested list.
		public NestedInteger() {
		}

		// Constructor initializes a single integer.
		public NestedInteger(int value) {
		}

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger() {
			return false;
		}

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {
			return 0;
		}

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value) {
		}

		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni) {
		}

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {
			return null;
		}
	}
}
