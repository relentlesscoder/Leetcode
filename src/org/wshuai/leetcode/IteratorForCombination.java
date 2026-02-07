package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1286 https://leetcode.com/problems/iterator-for-combination/
 */
public class IteratorForCombination {
	class CombinationIterator {

		private char[] s;
		private int n;
		private int k;
		private int mask; // 二进制掩码

		public CombinationIterator(String characters, int combinationLength) {
			// 思路: 用二进制掩码来表示组合
			// 示例1: s = "abcd", k = 3
			// 所有可能的组合: [abc, abd, acd, bcd]
			// 使用二进制表示: [1110, 1101, 1011, 0111]
			// 从字符串首找到长度 k 的子串的二进制掩码，每次找到下一个组合只需要找到下一个比当前掩码
			// 小的含有 k 个 1 的比特位的掩码。
			this.s = characters.toCharArray();
			this.n = s.length;
			this.k = combinationLength;
			this.mask = 0;
			// 预设开始值
			for (int i = 0; i < combinationLength; i++) {
				this.mask |= 1 << (this.n - i - 1);
			}
		}

		public String next() {
			char[] sb = new char[k];
			// 根据当前掩码构造字符串
			for (int i = n - 1, j = 0; i >= 0; i--) {
				if (((1 << i) & mask) > 0) {
					sb[j++] = s[n - 1 - i];
				}
			}
			// 找到下一个掩码
			for (mask = mask - 1; mask > 0 && Integer.bitCount(mask) != k; mask--) {}
			return new String(sb);
		}

		public boolean hasNext() {
			// 当前掩码不为 0
			return mask > 0;
		}
	}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
