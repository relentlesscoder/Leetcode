package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0366 https://leetcode.com/problems/find-leaves-of-binary-tree/
 */
public class FindLeavesOfBinaryTree {

	private List<List<Integer>> res = new ArrayList<>();

	// time O(n), space O(h)
	public List<List<Integer>> findLeaves(TreeNode root) {
		res = new ArrayList<>();
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root) {
		if (root == null) { // 节点为空则返回 0
			return 0;
		}
		// 节点在答案列表中的位置由左右节点更大的返回值决定
		int left = dfs(root.left), right = dfs(root.right), idx = Math.max(left, right);
		// 如果这个位置还没有加入到答案列表中则新建一个列表并将其加入
		if (idx == res.size()) {
			res.add(new ArrayList<>());
		}
		// 加入当前值到答案列表的 idx 子列表中
		res.get(idx).add(root.val);
		return idx + 1;
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
