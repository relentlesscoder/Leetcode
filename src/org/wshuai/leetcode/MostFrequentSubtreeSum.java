package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 2/26/17.
 * #508 https://leetcode.com/problems/most-frequent-subtree-sum/
 */
public class MostFrequentSubtreeSum {
	public int[] findFrequentTreeSum(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] max = new int[1];
		max[0] = 0;
		findFrequentTreeSumUtil(root, map, max);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max[0]) {
				res.add(entry.getKey());
			}
		}
		int[] arr = new int[res.size()];
		int i = 0;
		for (int v : res) {
			arr[i++] = v;
		}
		return arr;
	}

	private int findFrequentTreeSumUtil(TreeNode root, Map<Integer, Integer> map, int[] max) {
		if (root == null) {
			return 0;
		}
		root.val = findFrequentTreeSumUtil(root.left, map, max)
				+ findFrequentTreeSumUtil(root.right, map, max) + root.val;
		int cnt = map.getOrDefault(root.val, 0);
		map.put(root.val, cnt + 1);
		max[0] = Math.max(max[0], cnt + 1);
		return root.val;
	}
}
