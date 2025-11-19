package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 05/11/2020.
 * #1441 https://leetcode.com/problems/build-an-array-with-stack-operations/
 */
public class BuildAnArrayWithStackOperations {

	// time O(min(n, m)), space O(1)
	public List<String> buildArray(int[] target, int n) {
		int m = target.length;
		List<String> res = new ArrayList<>();
		for (int i = 1, j = 0; i <= n && j < m; i++) {
			res.add("Push");
			if (target[j] == i) {
				j++;
			} else {
				res.add("Pop");
			}
		}
		return res;
	}
}
