package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 11/19/2016.
 * #0960 https://leetcode.com/problems/delete-columns-to-make-sorted-iii/
 */
public class DeleteColumnsToMakeSortedIII {

	// time O(n^2 * m), space O(n)
	public int minDeletionSizeDP(String[] strs) {
		// 核心思路：最少删除列数 = 总列数 - 最多能保留的列数
		// 保留的列必须满足：对每一行，保留列的字符都是非递减的
		// 等价于在列序列中找最长子序列，使得子序列中的列按位置顺序两两满足"每行都非递减"
		// 这就是 LIS 的变体，只是"递增"条件变为"所有行都 >= "
		int n = strs[0].length(), res = n;
		// dp[i] = 以第 i 列结尾时，最多能保留的列数
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			int max = 0;
			// 遍历 i 之前的所有列 j，检查能否把第 i 列接在第 j 列后面
			for (int j = 0; j < i; j++) {
				// 如果第 i 列在所有行上都 >= 第 j 列，可以接在后面
				if (ge(i, j, strs)) {
					max = Math.max(max, dp[j]);
				}
			}
			// 以第 i 列结尾的最长合法子序列 = 最佳前驱 + 1（自身）
			dp[i] = max + 1;
			// 最少删除数 = 总列数 - 最多保留列数
			res = Math.min(res, n - dp[i]);
		}
		return res;
	}

	private boolean ge(int i, int j, String[] strs) {
		for (String s : strs) {
			if (s.charAt(i) < s.charAt(j)) {
				return false; // 某行第 i 列 < 第 j 列，不满足非递减
			}
		}
		return true;
	}
}
