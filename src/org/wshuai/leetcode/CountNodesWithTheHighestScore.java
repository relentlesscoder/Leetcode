package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/25/2023.
 * #2049 https://leetcode.com/problems/count-nodes-with-the-highest-score/
 */
public class CountNodesWithTheHighestScore {

	private long max = 0L;
	private int res = 0;

	// time O(n), space O(n)
	public int countHighestScoreNodes(int[] parents) {
		// 删除二叉树的一个节点会把二叉树分成三个部分 - 左子树，右子树和除去以
		// 当前节点为根结点的子树的原树中的其他节点。特殊情况：对于根结点来说，
		// 第三个部分的节点数为 0 。本题我们知道节点总数，对每个节点只需要用 DFS
		// 计算两个子树的节点数即可求的该节点的分数。
		max = 0L;
		res = 0;
		int n = parents.length;
		// 根据父节点数组构造二叉树数组
		int[][] tree = new int[n][2];
		Arrays.setAll(tree, i -> new int[] {-1, -1});
		for (int i = 1; i < n; i++) {
			if (tree[parents[i]][0] == -1) {
				tree[parents[i]][0] = i;
			} else {
				tree[parents[i]][1] = i;
			}
		}
		// DFS 二叉树计算每个节点的分数
		dfs(0, n, tree);
		return res;
	}

	private int dfs(int root, int n, int[][] tree) {
		if (root == -1) {
			return 0;
		}
		int leftTree = dfs(tree[root][0], n, tree);
		int rightTree = dfs(tree[root][1], n, tree);
		int parentTree = n - leftTree - rightTree - 1;
		long p = 1L;
		p *= leftTree == 0 ? 1 : leftTree;
		p *= rightTree == 0 ? 1 : rightTree;
		p *= parentTree == 0 ? 1 : parentTree;
		if (p >= max) {
			res = (p > max ? 1 : res + 1);
			max = p;
		}
		return leftTree + rightTree + 1;
	}
}
