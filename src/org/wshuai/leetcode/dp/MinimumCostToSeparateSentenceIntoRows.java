package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 03/29/2026.
 * #2052
 * https://leetcode.com/problems/minimum-cost-to-separate-sentence-into-rows/
 */
public class MinimumCostToSeparateSentenceIntoRows {

	// 两种解法的共同核心思路:
	// 划分型 DP: 将单词序列划分成若干行, 每行总长度 (含空格) <= k
	// 每行的 cost = (k - 行长度)^2 (剩余空间的平方), 最后一行 cost = 0
	// 与 #1105 (Filling Bookcase Shelves) 类似: 书架宽度 → 行宽度 k, 书的高度 → 剩余空间的平方
	//
	// 预处理: 将句子按空格拆分成单词长度数组 nums
	// 枚举当前行放 words[i..j], 行长度 = sum(nums[i..j]) + (j-i) 个空格

	// time O(n * W), space O(n)
	public int minimumCostDP(String sentence, int k) {
		// W = k / min(word_length), 每行最多放 W 个单词
		// 预处理: 提取每个单词的长度
		int l = sentence.length();
		List<Integer> nums = new ArrayList<>();
		for (int i = 0, cnt = 0; i <= l; i++) {
			if (i == l || sentence.charAt(i) == ' ') {
				nums.add(cnt);
				cnt = 0;
				continue;
			}
			cnt++;
		}
		// dp[i] = 从第 i 个单词开始到最后的最小总 cost
		// 从右往左填, 因为 dp[i] 依赖 dp[j+1] (右边的值)
		int n = nums.size();
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[n] = 0; // 所有单词都放完了
		for (int i = n - 1; i >= 0; i--) {
			int res = Integer.MAX_VALUE;
			// 枚举当前行放 words[i..j], len = 当前行的总长度 (含空格)
			for (int j = i, len = 0; j < n; j++) {
				int space = j == i ? 0 : 1; // 第一个单词前无空格, 之后每个单词前加 1 个空格
				if (len + space + nums.get(j) > k) {
					break; // 行宽度超过 k, 放不下了
				}
				len += space + nums.get(j);
				// 最后一行 cost = 0, 其他行 cost = (k - len)^2
				int cost = (j + 1 == n) ? 0 : (k - len) * (k - len);
				res = Math.min(res, cost + dp[j + 1]);
			}
			dp[i] = res;
		}
		return dp[0];
	}

	// time O(n * W), space O(n)
	public int minimumCostDFSWithMemorization(String sentence, int k) {
		// dfs(i) = 从第 i 个单词开始到最后的最小总 cost
		int l = sentence.length();
		List<Integer> nums = new ArrayList<>();
		for (int i = 0, cnt = 0; i <= l; i++) {
			if (i == l || sentence.charAt(i) == ' ') {
				nums.add(cnt);
				cnt = 0;
				continue;
			}
			cnt++;
		}
		int[] memo = new int[nums.size()];
		Arrays.fill(memo, -1);
		return dfs(0, nums, k, memo);
	}

	// dfs(i) = 从第 i 个单词开始到最后的最小总 cost
	private int dfs(int i, List<Integer> nums, int k, int[] memo) {
		int n = nums.size();
		if (i == n) {
			return 0; // 所有单词都放完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = Integer.MAX_VALUE;
		// 枚举当前行放 words[i..j]
		for (int j = i, len = 0; j < n; j++) {
			int space = j == i ? 0 : 1;
			if (len + space + nums.get(j) > k) {
				break; // 放不下了
			}
			len += space + nums.get(j);
			// 最后一行 cost = 0, 其他行 cost = (k - len)^2
			int cost = (j + 1 == n) ? 0 : (k - len) * (k - len);
			res = Math.min(res, cost + dfs(j + 1, nums, k, memo));
		}
		return memo[i] = res;
	}
}
