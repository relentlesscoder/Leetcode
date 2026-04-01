package org.wshuai.leetcode.dp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 03/24/2026.
 * #3578
 * https://leetcode.com/problems/count-partitions-with-max-min-difference-at-most-k/
 */
public class CountPartitionsWithMaxMinDifferenceAtMostK {

	// 三种解法的共同核心思路:
	// 划分型 DP: 将数组划分成若干子数组, 每个子数组内 max - min <= k
	// 统计合法划分的方案数 (不是最少段数)
	// dp[i+1] = sum over j where max(nums[j..i]) - min(nums[j..i]) <= k { dp[j] }
	// j 是段的起点, i 是段的终点
	//
	// O(n²) 解法: 枚举 j, 边扫边维护 max/min, 超过 k 就 break
	// O(n) 解法: 单调队列维护合法的 j 范围 [left, i], 前缀和 O(1) 求 sum(dp[left..i])
	// 与 #1438 (最长连续子数组 max-min <= limit) 用的是相同的单调队列技巧

	private static final int MOD = (int) 1e9 + 7;

	// time O(n), space O(n)
	// 单调队列 + 前缀和: 两个优化叠加
	// 1. 单调队列: O(1) 维护窗口 [j, i] 的 max 和 min, 找到最左合法起点 j
	// 2. 前缀和: O(1) 求 dp[j] + dp[j+1] + ... + dp[i] (所有合法起点的 dp 值之和)
	public int countPartitionsDPWithMonotonicQueueAndPrefixSum(int[] nums, int k) {
		int n = nums.length;
		// maxQueue: 单调递减队列, 队首是窗口最大值的下标
		Deque<Integer> maxQueue = new ArrayDeque<>();
		// minQueue: 单调递增队列, 队首是窗口最小值的下标
		Deque<Integer> minQueue = new ArrayDeque<>();
		// prefix[i+1] = dp[0] + dp[1] + ... + dp[i]
		long[] dp = new long[n + 1], prefix = new long[n + 2];
		dp[0] = 1L; // 空数组有 1 种划分方式
		prefix[1] = 1L;
		for (int i = 0, j = 0; i < n; i++) {
			// 维护 maxQueue 单调递减
			while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
				maxQueue.pollLast();
			}
			maxQueue.offer(i);
			// 维护 minQueue 单调递增
			while (!minQueue.isEmpty() && nums[minQueue.peekLast()] >= nums[i]) {
				minQueue.pollLast();
			}
			minQueue.offer(i);
			// 收缩左边界 j, 直到窗口 [j, i] 满足 max - min <= k
			while (nums[maxQueue.peek()] - nums[minQueue.peek()] > k) {
				if (maxQueue.peek() == j) {
					maxQueue.poll();
				}
				if (minQueue.peek() == j) {
					minQueue.poll();
				}
				j++;
			}
			// dp[i+1] = sum(dp[j..i]) = prefix[i+1] - prefix[j]
			// 所有合法起点 j 到 i 的 dp 值之和
			/**
			 * nums 0 1 2 3 4
			 * dp 0 1 2 3 4 5
			 * prefix 0 1 2 3 4 5 6
			 * prefix[0] = -
			 * prefix[1] = dp[0]
			 * prefix[2] = dp[0] + dp[1]
			 * prefix[3] = dp[0] + dp[1] + dp[2]
			 */
			dp[i + 1] = (prefix[i + 1] - prefix[j] + MOD) % MOD;
			prefix[i + 2] = (prefix[i + 1] + dp[i + 1]) % MOD;
		}
		return (int) dp[n];
	}

	// time O(n^2), space O(n)
	public int countPartitionsDP(int[] nums, int k) {
		// dp[i+1] = nums[0..i] 的合法划分方案数
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[0] = 1; // 空数组有 1 种划分
		for (int i = 0; i < n; i++) {
			int res = 0;
			// 从 i 往左扫, 枚举最后一段起点 j, 边扫边维护 max/min
			for (int j = i, max = nums[i], min = nums[i]; j >= 0; j--) {
				max = Math.max(max, nums[j]);
				min = Math.min(min, nums[j]);
				if (max - min > k) {
					break; // 段内 max - min > k, 更长的段也不合法
				}
				// 段 [j, i] 合法, 累加 dp[j] (j 之前的方案数)
				res = (res + dp[j]) % MOD;
			}
			dp[i + 1] = res;
		}
		return dp[n];
	}

	// time O(n^2), space O(n)
	public int countPartitionsDFSWithMemorization(int[] nums, int k) {
		// dfs(i) = nums[0..i] 的合法划分方案数
		int n = nums.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, nums, k, memo);
	}

	// dfs(i) = nums[0..i] 的合法划分方案数
	private int dfs(int i, int[] nums, int k, int[] memo) {
		if (i == -1) {
			return 1; // 所有元素都处理完了, 算 1 种方案
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = 0;
		// 枚举最后一段起点 j, 边扫边维护 max/min
		for (int j = i, max = nums[i], min = nums[i]; j >= 0; j--) {
			max = Math.max(max, nums[j]);
			min = Math.min(min, nums[j]);
			if (max - min > k) {
				break;
			}
			// 段 [j, i] 合法, 递归处理 nums[0..j-1]
			res = (res + dfs(j - 1, nums, k, memo)) % MOD;
		}
		return memo[i] = res;
	}
}
