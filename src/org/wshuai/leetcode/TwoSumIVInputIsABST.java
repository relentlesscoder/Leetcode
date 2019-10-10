package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/21/19.
 * #653 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSumIVInputIsABST {
	public boolean findTarget(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return dfs(root, set, k);
	}

	private boolean dfs(TreeNode node, Set<Integer> set, int k) {
		if (node == null) {
			return false;
		}
		if (set.contains(node.val)) {
			return true;
		}
		set.add(k - node.val);
		return dfs(node.left, set, k) || dfs(node.right, set, k);
	}
}
