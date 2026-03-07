package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2016.
 * #0113 https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumII {
	private List<List<Integer>> res = new ArrayList<>();

	// time O(n^2), space O(n^2)
	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		res = new ArrayList<>();
		dfs(root, targetSum, new ArrayList<>());
		return res;
	}

	private void dfs(TreeNode root, int targetSum, List<Integer> nums) {
		if (root == null) {
			return;
		}
		// 将当前数字加入路径中
		nums.add(root.val);
		targetSum -= root.val;
		// 如果是叶子节点并且路径和与目标和一致则将当前路径加入答案列表中
		if (root.left == null && root.right == null) {
			if (targetSum == 0) {
				res.add(new ArrayList(nums));
			}
		} else { // 不是叶子节点继续递归
			dfs(root.left, targetSum, nums);
			dfs(root.right, targetSum, nums);
		}
		// 活干完后恢复现场
		nums.remove(nums.size() - 1);
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
