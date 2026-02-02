package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/10/2019.
 * #0666 https://leetcode.com/problems/path-sum-iv/
 */
public class PathSumIV {

    // time O(n), space O(n)
    public int pathSum(int[] nums) {
        int n = nums.length;
		// 用数组表示二叉树
        int[] tree = new int[16];
        Arrays.fill(tree, -1);
        for (int i = 0; i < n; i++) {
            int r = nums[i] / 100, c = (nums[i] / 10) % 10, v = nums[i] % 10;
			// 计算节点位置
            int node = (1 << (r - 1)) - 1 + c;
            tree[node] = v;
        }
		// DFS 计算所有根到叶路径和的总和
        return dfs(1, 0, tree);
    }

    private int dfs(int node, int sum, int[] tree) {
		// 利用 dfs 将路径上的节点和从根结点向叶子节点传递，当到达每个叶子节点时即可
		// 得到路径上的节点和。
        if (node >= tree.length || tree[node] == -1) {
            return 0;
        }
        int left = node * 2, right = node * 2 + 1, pathSum = sum + tree[node];
		// 判断是否是叶子节点
        if ((left >= tree.length || tree[left] == -1)
				&& (right >= tree.length || tree[right] == -1)) {
            return pathSum;
        }
		// 继续递归左右子树
        int leftSum = dfs(left, pathSum, tree), rightSum = dfs(right, pathSum, tree);
        return leftSum + rightSum;
    }
}
