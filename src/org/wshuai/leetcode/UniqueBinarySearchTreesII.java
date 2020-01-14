package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/2016.
 * #0095 https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class UniqueBinarySearchTreesII {
	// time O(n^2)
	public List<TreeNode> generateTrees(int n) {
		if(n < 1){
			return new ArrayList<>();
		}
		return dfs(1, n);
	}

	private List<TreeNode> dfs(int start, int end){
		List<TreeNode> res = new ArrayList<>();
		if(start > end){
			res.add(null);
			return res;
		}
		for(int k = start; k <= end; k++){
			List<TreeNode> left = dfs(start, k - 1);
			List<TreeNode> right = dfs(k + 1, end);
			for(int i = 0; i < left.size(); i++){
				for(int j = 0; j < right.size(); j++){
					TreeNode root = new TreeNode(k);
					root.left = left.get(i);
					root.right = right.get(j);
					res.add(root);
				}
			}
		}
		return res;
	}
}
