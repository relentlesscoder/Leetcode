package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/26/2016.
 * #0257 https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {

	// time O(n^2 * log(MAX)), space O(n^2 * log(MAX))
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		dfs(root, new StringBuilder(), res);
		return res;
	}

	private void dfs(TreeNode node, StringBuilder sb, List<String> res) {
		if (node == null) {
			return;
		}
		// 记录递归之前路径的长度
		int len = sb.length();
		// 将当前数字加入路径中
		sb.append(node.val);
		// 如果是叶子节点则将当前路径加入答案列表中
		if (node.left == null && node.right == null) {
			res.add(sb.toString());
		} else { // 不是叶子节点加个箭头继续递归
			sb.append("->");
			dfs(node.left, sb, res);
			dfs(node.right, sb, res);
		}
		// 活干完后恢复现场
		sb.setLength(len);
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
