package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/20/2019.
 * #652 https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {
	private Map<String, List<TreeNode>> map;

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		map = new HashMap<>();
		findDuplicateSubtreesUtil(root);
		List<TreeNode> res = new ArrayList<>();
		for(List<TreeNode> lst: map.values()){
			if(lst.size() > 1){
				res.add(lst.get(0));
			}
		}
		return res;
	}

	private String findDuplicateSubtreesUtil(TreeNode node){
		if(node == null){
			return "";
		}
		String left = findDuplicateSubtreesUtil(node.left);
		String right = findDuplicateSubtreesUtil(node.right);
		String key = left.equals("") ? "" : "L" + left;
		key += "M" + node.val;
		key += right.equals("") ? "" : "R" + right;
		if(!map.containsKey(key)){
			map.put(key, new ArrayList<TreeNode>());
		}
		map.get(key).add(node);
		return key;
	}
}
