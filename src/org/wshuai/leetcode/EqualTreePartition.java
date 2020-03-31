package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/06/2019.
 * #0663 https://leetcode.com/problems/equal-tree-partition/
 */
public class EqualTreePartition {
	// time O(n), space O(n)
	public boolean checkEqualTree(TreeNode root) {
		Map<Integer, List<TreeNode>> map = new HashMap<>();
		int sum = dfs(root, map);
		if(sum % 2 != 0){
			return false;
		}
		sum >>= 1;
		return map.containsKey(sum)
				&& (map.get(sum).size() > 1 || map.get(sum).get(0) != root);
	}

	private int dfs(TreeNode root, Map<Integer, List<TreeNode>> map){
		if(root == null){
			return 0;
		}
		int sum = root.val + dfs(root.left, map) + dfs(root.right, map);
		map.putIfAbsent(sum, new ArrayList<>());
		map.get(sum).add(root);
		return sum;
	}
}
