package org.wshuai.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/29/2016.
 * #0437 https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {

	private int res = 0;

	// time O(n), space O(n)
	public int pathSum(TreeNode root, int targetSum) {
		res = 0;
		Map<Long, Integer> prefix = new HashMap<>();
		prefix.put(0L, 1);
		dfs(root, targetSum, 0, prefix);
		return res;
	}

	private void dfs(TreeNode root, int targetSum, long sum, Map<Long, Integer> prefix) {
		// prefix 存从根节点出发的前缀节点和
		if (root == null) {
			return;
		}
		// 计算前缀节点和
		sum += root.val;
		// 用前缀哈希表计算当前路径上是否存在和为目标的子路径
		res += prefix.getOrDefault(sum - targetSum, 0);
		// 将当前前缀节点和加入哈希表
		prefix.merge(sum, 1, Integer::sum);
		// 递归左右子树
		dfs(root.left, targetSum, sum, prefix);
		dfs(root.right, targetSum, sum, prefix);
		// 活干完后恢复现场
		prefix.merge(sum, -1, Integer::sum);
	}

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}