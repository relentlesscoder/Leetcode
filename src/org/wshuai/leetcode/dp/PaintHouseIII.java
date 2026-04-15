package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 06/08/2020.
 * #1473 https://leetcode.com/problems/paint-house-iii/
 */
public class PaintHouseIII {

	private static final int MAX = (int) 1e7;

	// time O(m * n * target), space O(m * n)
	// 核心思路: 从右往左DP, pre[h][c]表示上一轮(少一个街区边界)的结果, dp[h][c]表示当前轮的结果
	// c表示房子h右边邻居的颜色(c=0表示无右邻居), t表示已经产生的街区边界数
	// 优化: 维护 cost[h][i-1] + pre[h][i] 的最小值/次小值, 将内层循环从O(n)降到O(1)
	public int minCostWithRolling2DGridOptimized(int[] houses, int[][] cost, int m, int n, int target) {
		if (target > m) {
			// 街区数不可能超过房子数
			return -1;
		}
		// pre[h][c]: 前h个房子全部同色(1个街区)时, 右邻居颜色为c的最小花费
		int[][] pre = new int[m + 1][n + 1];
		for (int h = 0; h < m; h++) {
			for (int c = 1; c <= n; c++) {
				if (houses[h] == c || houses[h] == 0) {
					// 房子h已涂色c或未涂色(可涂c), 累加涂色花费
					pre[h + 1][c] = (houses[h] == c ? 0 : cost[h][c - 1]) + pre[h][c];
				} else {
					// 房子h已涂其他色, 无法全部同色c
					pre[h + 1][c] = MAX;
				}
			}
		}
		// 每轮迭代增加一个街区边界, 共需target-1个边界(target个街区)
		for (int t = 0; t < target; t++) {
			int[][] dp = new int[m + 1][n + 1];
			// 0个房子不可能有街区边界
			Arrays.fill(dp[0], MAX);
			for (int h = 0; h < m; h++) {
				// 预计算 cost[h][i-1] + pre[h][i] 的最小值和次小值
				// 用于O(1)查询: 枚举房子h涂色i, 与右邻居不同色时查pre
				int min1 = MAX, min2 = MAX, minIdx = -1;
				for (int i = 1; i <= n; i++) {
					int val = (pre[h][i] >= MAX ? MAX : cost[h][i - 1] + pre[h][i]);
					if (val < min1) {
						min2 = min1;
						min1 = val;
						minIdx = i;
					} else if (val < min2) {
						min2 = val;
					}
				}
				for (int c = n; c >= 0; c--) {
					if (c > 0 && houses[h] == c) {
						// 房子h已涂色c, 与右邻居同色, 不产生新边界, 继承上一个房子的结果
						dp[h + 1][c] = dp[h][c];
						continue;
					}
					if (houses[h] > 0) {
						// 房子h已涂色且与右邻居不同色, 产生一个边界, 从pre获取子问题
						dp[h + 1][c] = pre[h][houses[h]];
						continue;
					}
					// 房子h未涂色, 枚举涂色i:
					// c == 0(无右邻居): 所有i都与右边不同色, 全部查pre, 直接取最小值
					// c > 0: i != c 查pre(取排除c后的最小值), i == c 查dp(同色不产生边界)
					int best = (minIdx == c ? min2 : min1);
					if (c > 0) {
						// i == c 的特殊项: 涂色c与右邻居同色, 不产生边界, 查当前轮dp
						best = Math.min(best, cost[h][c - 1] + dp[h][c]);
					}
					dp[h + 1][c] = best;
				}
			}
			// 滚动: 当前轮结果作为下一轮的pre
			pre = dp;
		}
		// pre[m][0]: 所有房子处理完, 无右邻居(c=0), 恰好target个街区边界
		return pre[m][0] >= MAX ? -1 : pre[m][0];
	}

	// time O(m * n^2 * target), space O(m * n)
	// 核心思路: 与优化版相同, 但内层枚举涂色i用O(n)暴力而非O(1)最小/次小值
	public int minCostWithRolling2DGrid(int[] houses, int[][] cost, int m, int n, int target) {
		if (target > m) {
			return -1;
		}
		// pre[h][c]: 前h个房子全部同色(1个街区), 右邻居颜色为c的最小花费
		int[][] pre = new int[m + 1][n + 1];
		for (int h = 0; h < m; h++) {
			for (int c = 1; c <= n; c++) {
				if (houses[h] == c || houses[h] == 0) {
					pre[h + 1][c] = (houses[h] == c ? 0 : cost[h][c - 1]) + pre[h][c];
				} else {
					pre[h + 1][c] = MAX;
				}
			}
		}
		// 每轮增加一个街区边界
		for (int t = 0; t < target; t++) {
			int[][] dp = new int[m + 1][n + 1];
			Arrays.fill(dp[0], MAX);
			for (int h = 0; h < m; h++) {
				for (int c = n; c >= 0; c--) {
					if (c > 0 && houses[h] == c) {
						// 已涂色c, 与右邻居同色, 不产生新边界
						dp[h + 1][c] = dp[h][c];
						continue;
					}
					if (houses[h] > 0) {
						// 已涂色且与右邻居不同色, 产生边界, 从pre获取子问题
						dp[h + 1][c] = pre[h][houses[h]];
						continue;
					}
					// 未涂色, 枚举涂色i: i == c 同色不产生边界查dp, i != c 不同色产生边界查pre
					int res = MAX;
					for (int i = 1; i <= n; i++) {
						res = Math.min(res, cost[h][i - 1] + (i == c ? dp[h][i] : pre[h][i]));
					}
					dp[h + 1][c] = res;
				}
			}
			pre = dp;
		}
		return pre[m][0] >= MAX ? -1 : pre[m][0];
	}

	// time O(m * n^2 * target), space O(m * n * target)
	// 核心思路: 与滚动数组版相同, 但保留完整的三维数组, 不做空间压缩
	// dp[t][h][c]: 前h个房子形成t个街区边界, 右邻居颜色为c的最小花费
	// c=0表示无右邻居(或右邻居颜色未知, 必定产生边界)
	public int minCostDPWith3DGrid(int[] houses, int[][] cost, int m, int n, int target) {
		if (target > m) {
			return -1;
		}
		int[][][] dp = new int[target + 1][m + 1][n + 1];
		// base case: dp[0] = 0个边界 = 1个街区, 所有房子必须同色
		for (int h = 0; h < m; h++) {
			for (int c = 1; c <= n; c++) {
				if (houses[h] == c || houses[h] == 0) {
					// 房子h可以是颜色c, 累加涂色花费
					dp[0][h + 1][c] = (houses[h] == c ? 0 : cost[h][c - 1]) + dp[0][h][c];
				} else {
					// 房子h已涂其他色, 无法全部同色c
					dp[0][h + 1][c] = MAX;
				}
			}
		}
		// 每轮增加一个街区边界
		for (int t = 0; t < target; t++) {
			// 0个房子不可能有边界
			Arrays.fill(dp[t + 1][0], MAX);
			for (int h = 0; h < m; h++) {
				for (int c = n; c >= 0; c--) {
					if (c > 0 && houses[h] == c) {
						// 已涂色c, 与右邻居同色, 不产生新边界
						dp[t + 1][h + 1][c] = dp[t + 1][h][c];
						continue;
					}
					if (houses[h] > 0) {
						// 已涂色且与右邻居不同色, 产生边界, 从上一轮获取子问题
						dp[t + 1][h + 1][c] = dp[t][h][houses[h]];
						continue;
					}
					// 未涂色, 枚举涂色i:
					// i == c: 同色不产生边界, 查当前轮dp[t+1]
					// i != c: 不同色产生边界, 查上一轮dp[t]
					int res = MAX;
					for (int i = 1; i <= n; i++) {
						res = Math.min(res, cost[h][i - 1] + (i == c ? dp[t + 1][h][i] : dp[t][h][i]));
					}
					dp[t + 1][h + 1][c] = res;
				}
			}
		}
		// dp[target][m][0]: 所有房子处理完, 无右邻居, 恰好target个边界
		return dp[target][m][0] >= MAX ? -1 : dp[target][m][0];
	}

	// time O(m * n^2 * target), space O(m * n * target)
	// 核心思路: 记忆化DFS, 从右往左处理每个房子, t为剩余可用的街区边界数, c为右邻居颜色
	public int minCostDFSWithMemorization(int[] houses, int[][] cost, int m, int n, int target) {
		if (target > m) {
			return -1;
		}
		// memo[t][h][c]: 剩余t个边界预算, 当前处理房子h, 右邻居颜色c
		int[][][] memo = new int[target][m][n + 1];
		for (int[][] matrix : memo) {
			for (int[] row : matrix) {
				Arrays.fill(row, -1);
			}
		}
		// 初始: target-1个边界预算, 从最右边房子开始, 无右邻居(c=0)
		int res = dfs(target - 1, m - 1, 0, n, houses, cost, memo);
		return res >= MAX ? -1 : res;
	}

	// t: 剩余街区边界预算, h: 当前房子索引(从右往左), c: 右邻居颜色(0表示无右邻居)
	private int dfs(int t, int h, int c, int n, int[] houses, int[][] cost, int[][][] memo) {
		if (t == -1) {
			// 边界预算用完, 剩余房子必须与右邻居同色(合并到同一街区)
			if (h == -1) {
				// 所有房子处理完, 合法
				return 0;
			}
			// 房子h必须是颜色c: 已涂色c则免费, 未涂色则花cost涂成c, 否则不可行
			return c > 0 && (houses[h] == c || houses[h] == 0)
					? (houses[h] == c ? 0 : cost[h][c - 1]) + dfs(t, h - 1, c, n, houses, cost, memo)
					: MAX;
		}
		if (h == -1) {
			// 房子处理完但还有剩余边界预算, 说明街区数不够target
			return MAX;
		}
		if (memo[t][h][c] != -1) {
			return memo[t][h][c];
		}
		if (c > 0 && houses[h] == c) {
			// 房子h已涂色c, 与右邻居同色, 不消耗边界预算
			return memo[t][h][c] = dfs(t, h - 1, c, n, houses, cost, memo);
		}
		if (houses[h] > 0) {
			// 房子h已涂色且与右邻居不同色, 产生一个边界, 消耗一个预算
			return memo[t][h][c] = dfs(t - 1, h - 1, houses[h], n, houses, cost, memo);
		}
		// 房子h未涂色, 枚举涂色i
		int res = MAX;
		for (int i = 1; i <= n; i++) {
			// i == c: 与右邻居同色, 不消耗预算; i != c: 不同色, 消耗一个预算
			res = Math.min(res, cost[h][i - 1] + dfs(i == c ? t : t - 1, h - 1, i, n, houses, cost, memo));
		}
		return memo[t][h][c] = res;
	}
}
