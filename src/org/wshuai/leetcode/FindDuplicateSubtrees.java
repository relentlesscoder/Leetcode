package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/20/2019.
 * #0652 https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {
	// time O(n), space O(n)
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new ArrayList<>();
		Map<String, List<TreeNode>> map = new HashMap<>();
		dfs(root, map);
		for(String key : map.keySet()){
			List<TreeNode> cur = map.get(key);
			if(cur.size() > 1){
				res.add(cur.get(0));
			}
		}
		return res;
	}

	private String dfs(TreeNode root, Map<String, List<TreeNode>> map){
		if(root == null){
			return "#";
		}
		String res = "L" + dfs(root.left, map);
		res += "M" + root.val;
		res += "R" + dfs(root.right, map);
		map.putIfAbsent(res, new ArrayList<>());
		map.get(res).add(root);
		return res;
	}
}
