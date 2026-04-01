package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 03/29/2026.
 * #2464
 * https://leetcode.com/problems/minimum-subarrays-in-a-valid-split/
 */
public class MinimumSubarraysInAValidSplit {

	// 三种解法的共同核心思路:
	// 划分型 DP: 将数组划分成最少的子数组, 每个子数组的首尾元素 gcd > 1
	// dp[i+1] = min over j in [0, i] where gcd(nums[i], nums[j]) > 1 { 1 + dp[j] }
	// j 是段的起点, i 是段的终点, dp[j] 是 nums[0..j-1] 的最少段数
	//
	// O(n²) 解法: 枚举所有 (i, j) 对, 逐个算 gcd
	// O(n * sqrt(max)) 解法: 按质因数分组, 用 HashMap 存每个质因数对应的最优 dp[j]
	// gcd(nums[i], nums[j]) > 1 等价于它们共享至少一个质因数
	// best[p] = min{ dp[j] | p 是 nums[j] 的质因数, j in [0, i] }
	// 用 best[p] 替代内层 for 循环, O(1) 查询

	// time O(n * sqrt(max)), space O(n)
	public int validSubarraySplitDPWithPrimeFactorization(int[] nums) {
		int n = nums.length;
		// dp[i+1] = nums[0..i] 的最少段数
		int[] dp = new int[n + 1];
		// map[p] = 所有含质因数 p 的位置 j 中, dp[j] 的最小值
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int res = 10_000;
			// O(sqrt(nums[i])) 分解质因数
			List<Integer> factors = primeFactor(nums[i]);
			// ① 存入: 位置 i 作为未来某段的起点, 代价 dp[i]
			for (int f : factors) {
				map.merge(f, dp[i], Math::min);
			}
			// ② 查询: 位置 i 作为当前段的终点, 找最优起点
			for (int f : factors) {
				res = Math.min(res, 1 + map.getOrDefault(f, 0));
			}
			dp[i + 1] = res;
		}
		return dp[n] > n ? -1 : dp[n];
	}

	// O(sqrt(x)) 分解质因数, 每个质因数只保留一个
	private List<Integer> primeFactor(int x) {
		List<Integer> res = new ArrayList<>();
		for (int i = 2; i * i <= x; i++) {
			if (x % i == 0) {
				res.add(i);
				while (x % i == 0) {
					x /= i; // 去掉所有 i 的倍数, 只保留一个
				}
			}
		}
		if (x > 1) {
			res.add(x); // 剩余的大质因数
		}
		return res;
	}

	// time O(n^2 * log(max)), space O(n)
	public int validSubarraySplitDP(int[] nums) {
		// dp[i+1] = nums[0..i] 的最少段数
		int n = nums.length;
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = 10_000;
			// 枚举段起点 j, 从 i 往左扫
			for (int j = i; j >= 0; j--) {
				// gcd(nums[i], nums[j]) > 1 → 段 [j, i] 合法
				if (gcd(nums[i], nums[j]) > 1) {
					res = Math.min(res, 1 + dp[j]);
				}
			}
			dp[i + 1] = res;
		}
		return dp[n] > n ? -1 : dp[n];
	}

	// time O(n^2 * log(max)), space O(n)
	public int validSubarraySplitDFSWithMemorization(int[] nums) {
		// dfs(i) = nums[0..i] 的最少段数
		int n = nums.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		int res = dfs(n - 1, nums, memo);
		return res > n ? -1 : res;
	}

	// dfs(i) = nums[0..i] 的最少段数
	private int dfs(int i, int[] nums, int[] memo) {
		if (i == -1) {
			return 0; // 所有元素都处理完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = 10_000;
		// 枚举段起点 j
		for (int j = i; j >= 0; j--) {
			if (gcd(nums[i], nums[j]) > 1) {
				res = Math.min(res, 1 + dfs(j - 1, nums, memo));
			}
		}
		return memo[i] = res;
	}

	private int gcd(int a, int b) {
		while (a != 0) {
			int temp = a;
			a = b % a;
			b = temp;
		}
		return b;
	}
}
