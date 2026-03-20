package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/15/2026.
 * #3628
 * https://leetcode.com/problems/maximum-number-of-subsequences-after-one-inserting/
 */
public class MaximumNumberOfSubsequencesAfterOneInserting {

	// time O(n), space O(1)
	public long numOfSubsequences(String s) {
		// 第一步：正向遍历，统计原始字符串中 "LCT" 子序列的数量
		long cntL = 0, cntLC = 0, cntLCT = 0;
		for (char c : s.toCharArray()) {
			if (c == 'L') {
				cntL++; // 'L' 的个数
			} else if (c == 'C') {
				cntLC += cntL; // "LC" 子序列的个数 = 每遇到一个 'C'，可以和前面所有的 'L' 配对
			} else if (c == 'T') {
				cntLCT += cntLC; // "LCT" 子序列的个数 = 每遇到一个 'T'，可以和前面所有的 "LC" 配对
			}
		}
		// 第二步：考虑插入一个字符后能额外增加多少 "LCT" 子序列
		// 插入 'L'：放在某个位置，能和后面所有的 "CT" 配对 → 最大增量 = max(cntCT)
		// 插入 'T'：放在某个位置，能和前面所有的 "LC" 配对 → 最大增量 = max(cntLC)（已经算过了）
		// 插入 'C'：放在某个位置 i，能和前面的 'L' 数量 × 后面的 'T' 数量配对 → 最大增量 = max(preL * cntT)
		long cntT = 0, cntCT = 0, maxC = 0, preL = cntL;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == 'T') {
				cntT++; // 当前位置右边（含自身）的 'T' 个数
			} else if (c == 'C') {
				cntCT += cntT; // 当前位置右边（含自身）的 "CT" 子序列个数
			} else if (c == 'L') {
				preL--; // 当前位置右边的 'L' 个数（不含自身）
			}
			// 在位置 i 处插入 'C'，能额外增加 preL * cntT 个 "LCT" 子序列
			maxC = Math.max(maxC, cntT * preL);
		}
		// 原始 "LCT" 数量 + 插入一个字符后的最大额外增量
		return cntLCT + Math.max(maxC, Math.max(cntLC, cntCT));
	}
}
