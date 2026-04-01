package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/01/2019.
 * #1105 https://leetcode.com/problems/filling-bookcase-shelves/
 */
public class FillingBookcaseShelves {

	// 两种解法的共同核心思路:
	// 划分型 DP: 将书按顺序划分成若干层, 每层总宽度 <= shelfWidth
	// 每层的高度 = 该层最高的书, 求所有层高度之和的最小值
	// 枚举最后一层的起点 j, 最后一层为 books[j..i]
	// 该层的代价 = max(books[j..i] 的高度), 无法用数据结构加速 (必须逐个枚举)
	// 内循环自然剪枝: 总宽度 > shelfWidth 时 break

	// time O(n * W), space O(n)
	public int minHeightShelvesDP(int[][] books, int shelfWidth) {
		// dp[i+1] = books[0..i] 的最小总高度
		int n = books.length;
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = Integer.MAX_VALUE;
			// 从 i 往左扫, 枚举最后一层的起点 j
			// w = books[j..i] 的总宽度, mh = books[j..i] 的最大高度
			for (int j = i, w = 0, mh = 0; j >= 0; j--) {
				w += books[j][0]; // 累加宽度
				if (w > shelfWidth) {
					break; // 总宽度超过书架宽度, 放不下了
				}
				mh = Math.max(mh, books[j][1]); // 更新该层最大高度
				// 该层高度 mh + 前面 books[0..j-1] 的最优高度 dp[j]
				res = Math.min(res, mh + dp[j]);
			}
			dp[i + 1] = res;
		}
		return dp[n];
	}

	// time O(n * W), space O(n)
	public int minHeightShelvesDFSWithMemorization(int[][] books, int shelfWidth) {
		// dfs(i) = books[0..i] 的最小总高度
		int n = books.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, books, shelfWidth, memo);
	}

	// dfs(i) = books[0..i] 的最小总高度
	private int dfs(int i, int[][] books, int shelfWidth, int[] memo) {
		if (i == -1) {
			return 0; // 所有书都放完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = Integer.MAX_VALUE;
		// 枚举最后一层的起点 j
		for (int j = i, w = 0, mh = 0; j >= 0; j--) {
			w += books[j][0];
			if (w > shelfWidth) {
				break; // 宽度超限
			}
			mh = Math.max(mh, books[j][1]); // 该层最大高度
			// 该层高度 mh, 递归处理 books[0..j-1]
			res = Math.min(res, mh + dfs(j - 1, books, shelfWidth, memo));
		}
		return memo[i] = res;
	}
}
