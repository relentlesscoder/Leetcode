package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 09/18/2016.
 * #0339 https://leetcode.com/problems/nested-list-weight-sum/
 */
public class NestedListWeightSum {

	// time O(n), space O(n)
	public int depthSumBFS(List<NestedInteger> nestedList) {
		int sum = 0, depth = 1;
		Deque<List<NestedInteger>> queue = new ArrayDeque<>();
		queue.offer(nestedList);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				List<NestedInteger> curr = queue.poll();
				for (NestedInteger ni : curr) {
					if (ni.isInteger()) {
						sum += depth * ni.getInteger();
					} else {
						queue.offer(ni.getList());
					}
				}
			}
			depth++;
		}
		return sum;
	}

	// time O(n), space O(n)
	public int depthSumDFS(List<NestedInteger> nestedList) {
		return dfs(nestedList, 1);
	}

	private int dfs(List<NestedInteger> nestedList, int depth) {
		int res = 0;
		for (NestedInteger cur : nestedList) {
			if (cur.isInteger()) {
				res += depth * cur.getInteger();
			} else {
				res += dfs(cur.getList(), depth + 1);
			}
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
