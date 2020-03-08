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
}
