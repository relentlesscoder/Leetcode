package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/21/2019.
 * #0653 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSumIVInputIsABST {
	// time O(n), space O(n)
	public boolean findTarget(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return dfs(root, set, k);
	}

	private boolean dfs(TreeNode root, Set<Integer> set, int k){
		if(root == null){
			return false;
		}
		if(set.contains(root.val)){
			return true;
		}
		set.add(k - root.val);
		return dfs(root.left, set, k) || dfs(root.right, set, k);
	}
}
