package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/22/2020.
 * #1626 https://leetcode.com/problems/best-team-with-no-conflicts/
 */
public class BestTeamWithNoConflicts {

	private static final int MAX = 1_000;

	// time O(n * log(m)), space O(n + m)
	public int bestTeamScoreBinaryIndexedTree(int[] scores, int[] ages) {
		// 用 BIT 维护前缀最大值，将内层遍历从 O(m) 优化到 O(log m)
		int res = 0, n = scores.length, m = 0;
		// 间接排序：按分数升序，分数相同按年龄升序
		Integer[] idx = new Integer[n];
		Arrays.setAll(idx, i -> i);
		Arrays.sort(idx, (i, j) -> scores[i] == scores[j] ? ages[i] - ages[j] : scores[i] - scores[j]);
		// 找到最大年龄，作为 BIT 的大小
		for (int x : ages) {
			m = Math.max(m, x);
		}
		// BIT 维护：tree[age] = 年龄 <= age 的球员中，能获得的最大分数和
		BIT bit = new BIT(m);
		for (int i = 0; i < n; i++) {
			// 查询年龄 <= ages[idx[i]] 的最大分数和，加上当前球员的分数
			int val = bit.pre(ages[idx[i]]) + scores[idx[i]];
			// 更新 BIT：在年龄 ages[idx[i]] 处记录新的最大值
			bit.update(ages[idx[i]], val);
			res = Math.max(res, val);
		}
		return bit.pre(m);
	}

	// 树状数组：维护前缀最大值（非传统的前缀和）
	private static class BIT {

		private final int[] tree;

		public BIT(int n) {
			tree = new int[n + 1];
		}

		// 单点更新：将 index 位置的值更新为 val（取 max）
		public void update(int index, int val) {
			while (index < tree.length) {
				tree[index] = Math.max(val, tree[index]);
				index += index & -index; // lowbit 跳到下一个管辖区间
			}
		}

		// 前缀查询：查询 [1, index] 范围内的最大值
		public int pre(int index) {
			int res = 0;
			while (index > 0) {
				res = Math.max(res, tree[index]);
				index -= index & -index; // lowbit 跳到前一个管辖区间
			}
			return res;
		}
	}

	// time O(n * m), space O(n + m)
	public int bestTeamScoreByAge(int[] scores, int[] ages) {
		// dp[age] = 最后选的球员年龄恰好为 age 时，能获得的最大分数和
		int res = 0, n = scores.length, m = 0;
		// 间接排序：按分数升序，分数相同按年龄升序
		Integer[] idx = new Integer[n];
		Arrays.setAll(idx, i -> i);
		Arrays.sort(idx, (i, j) -> scores[i] == scores[j] ? ages[i] - ages[j] : scores[i] - scores[j]);
		for (int x : ages) {
			m = Math.max(m, x);
		}
		// dp[j] = 最后选的球员年龄为 j 时的最大分数和
		int[] dp = new int[m + 1];
		for (int i = 0; i < n; i++) {
			// 遍历所有 <= 当前球员年龄的 dp 值，找最大的作为前驱
			int max = 0;
			for (int j = 1; j <= ages[idx[i]]; j++) {
				max = Math.max(max, dp[j]);
			}
			// 更新当前年龄的 dp 值
			dp[ages[idx[i]]] = max + scores[idx[i]];
			res = Math.max(res, dp[ages[idx[i]]]);
		}
		return res;
	}

	// time O(n^2), space O(n)
	public int bestTeamScoreDP(int[] scores, int[] ages) {
		// dp[i] = 以第 i 个球员结尾时，能获得的最大分数和
		int res = 0, n = scores.length;
		// 间接排序：按分数升序，分数相同按年龄升序
		Integer[] idx = new Integer[n];
		Arrays.setAll(idx, i -> i);
		Arrays.sort(idx, (i, j) -> scores[i] == scores[j] ? ages[i] - ages[j] : scores[i] - scores[j]);
		// dp[idx[i]] = 以排序后第 i 个球员结尾的最大分数和
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			int max = 0;
			// 遍历排序后在 i 之前的所有球员 j
			for (int j = 0; j < i; j++) {
				// 分数已经升序，只需检查年龄也满足非递减
				if (ages[idx[j]] <= ages[idx[i]]) {
					max = Math.max(max, dp[idx[j]]);
				}
			}
			// 当前球员的最大分数和 = 最佳前驱 + 自身分数
			dp[idx[i]] = max + scores[idx[i]];
			res = Math.max(res, dp[idx[i]]);
		}
		return res;
	}
}
