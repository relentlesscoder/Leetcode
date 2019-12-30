package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2019.
 * #1302 https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {
	private Map<Integer, Integer> map = new HashMap<>();
	private int maxLevel = 0;

	public int deepestLeavesSum(TreeNode root) {
		dfs(root, 0);
		return map.get(maxLevel);
	}

	private void dfs(TreeNode cur, int level){
		if(cur == null){
			return;
		}
		maxLevel = Math.max(level, maxLevel);
		map.put(level, map.getOrDefault(level, 0) + cur.val);
		dfs(cur.left, level + 1);
		dfs(cur.right, level + 1);
	}
}
