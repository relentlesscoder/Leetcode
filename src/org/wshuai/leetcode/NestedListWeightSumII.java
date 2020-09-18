package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/01/2016.
 * #0364 https://leetcode.com/problems/nested-list-weight-sum-ii/
 */
public class NestedListWeightSumII {
	// time O(n), space O(n)
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if(nestedList == null){
			return 0;
		}
		int res = 0, prev = 0;
		LinkedList<NestedInteger> queue = new LinkedList<>();
		for(NestedInteger ni : nestedList){
			queue.offerLast(ni);
		}
		while(!queue.isEmpty()){
			int size = queue.size(), levelSum = 0;
			while(size-- > 0){
				NestedInteger cur = queue.pollFirst();
				if(cur.isInteger()){
					levelSum += cur.getInteger();
				}
				for(NestedInteger ni : cur.getList()){
					queue.offerLast(ni);
				}
			}
			prev += levelSum;
			res += prev;
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
