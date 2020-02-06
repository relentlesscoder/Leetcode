package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/18/2016.
 * #0339 https://leetcode.com/problems/nested-list-weight-sum/
 */
public class NestedListWeightSum {
	// time O(n)
	public int depthSum(List<NestedInteger> nestedList) {
		return dfs(nestedList, 1);
	}

	private int dfs(List<NestedInteger> nestedList, int depth){
		int res = 0;
		for(NestedInteger cur : nestedList){
			res += cur.isInteger() ? cur.getInteger() * depth : dfs(cur.getList(), depth + 1);
		}
		return res;
	}
}
