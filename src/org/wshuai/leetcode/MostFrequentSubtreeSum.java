package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 02/26/2017.
 * #0508 https://leetcode.com/problems/most-frequent-subtree-sum/
 */
public class MostFrequentSubtreeSum {
	private Map<Integer, Integer> map;
	private int maxFreqCount;

	// time O(n), space O(n)
	public int[] findFrequentTreeSum(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		map = new HashMap<>();
		maxFreqCount = 0;
		dfs(root);
		List<Integer> list = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxFreqCount) {
				list.add(entry.getKey());
			}
		}
		int[] res = new int[list.size()];
		int i = 0;
		for (int sum : list) {
			res[i++] = sum;
		}
		return res;
	}

	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int sum = root.val + dfs(root.left) + dfs(root.right);
		int count = map.getOrDefault(sum, 0) + 1;
		map.put(sum, count);
		if (count > maxFreqCount) {
			maxFreqCount = count;
		}
		return sum;
	}
}
