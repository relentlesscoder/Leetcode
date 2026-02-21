package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 01/16/2020.
 * #0120 https://leetcode.com/problems/triangle/
 */
public class Triangle {

	// time O(n^2), space O(n)
	public int minimumTotalDPWithArray(List<List<Integer>> triangle) {
		// 空间优化版 DP
		int m = triangle.size(), n = triangle.get(m - 1).size();
		int[] pre = new int[n + 1];
		Arrays.fill(pre, Integer.MAX_VALUE);
		pre[0] = 0;
		for (int i = 0; i < m; i++) {
			int[] dp = new int[n + 1];
			Arrays.fill(dp, Integer.MAX_VALUE);
			for (int j = 0; j < triangle.get(i).size(); j++) {
				dp[j + 1] = Math.min(pre[j], pre[j + 1]) + triangle.get(i).get(j);
			}
			pre = dp;
		}
		int res = Integer.MAX_VALUE;
		for (int x : pre) {
			res = Math.min(res, x);
		}
		return res;
	}

    // time O(n^2), space O(n^2)
    public int minimumTotalDPWithGrid(List<List<Integer>> triangle) {
		// 网格图 DP
        int m = triangle.size(), n = triangle.get(m - 1).size();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i][j + 1]) + triangle.get(i).get(j);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int x : dp[m]) {
            res = Math.min(res, x);
        }
        return res;
    }

    // time O(n^2), space O(n^2)
    public int minimumTotalDFSWithMemorization(List<List<Integer>> triangle) {
        int m = triangle.size(), n = triangle.get(m - 1).size();
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, triangle, memo);
    }

    private int dfs(int i, int j, List<List<Integer>> triangle, int[][] memo) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
		// 选择到下一层节点的两个路径中较小的那个以分割成子问题
        return memo[i][j] = Math.min(dfs(i + 1, j, triangle, memo),
                dfs(i + 1, j + 1, triangle, memo)) + triangle.get(i).get(j);
    }

	private int res = Integer.MAX_VALUE;

	public int minimumTotalBinaryTree(List<List<Integer>> triangle) {
		// 可以当成二叉树来处理则变成根到叶节点的最小路径和
		res = Integer.MAX_VALUE;
		dfs(0, 0, 0, triangle);
		return res;
	}

	private void dfs(int i, int j, int sum, List<List<Integer>> triangle) {
		sum += triangle.get(i).get(j);
		if (i == triangle.size() - 1) {
			res = Math.min(res, sum);
			return;
		}
		dfs(i + 1, j, sum, triangle);
		dfs(i + 1, j + 1, sum, triangle);
	}
}
