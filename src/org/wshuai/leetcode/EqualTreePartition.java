package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/6/19.
 * #663 https://leetcode.com/problems/equal-tree-partition/
 */
public class EqualTreePartition {
	Map<Integer, List<TreeNode>> map;

	public boolean checkEqualTree(TreeNode root) {
		map = new HashMap<>();
		int sum = dfs(root);
		if(sum % 2 == 0 && map.containsKey(sum / 2)
				&& (map.get(sum / 2).size() > 1 || map.get(sum / 2).get(0) != root)){
			return true;
		}
		return false;
	}

	private int dfs(TreeNode node){
		if(node == null){
			return 0;
		}
		int left = dfs(node.left);
		int right = dfs(node.right);
		int sum = node.val + left + right;
		if(!map.containsKey(sum)){
			map.put(sum, new ArrayList<>());
		}
		map.get(sum).add(node);
		return sum;
	}
}
