package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/23/2026.
 * #2767
 * https://leetcode.com/problems/partition-string-into-minimum-beautiful-substrings/
 */
public class PartitionStringIntoMinimumBeautifulSubstrings {

	private static final int MAX = 1_000;

	// 两种解法的共同核心思路：
	// "beautiful" 子串 = 二进制表示的 5 的幂（1, 5, 25, 125, ...）且无前导零
	// 划分型 DP：将字符串划分成最少的 beautiful 子串
	// 用反向二进制 Trie 存所有 5 的幂的二进制表示，从位置 i 往左扫匹配
	// 与 #0139 WordBreak、#2707 ExtraCharacters 同一模式

	// time O(n * L), space O(n + L)
	public int minimumBeautifulSubstringsDPWithArray(String s) {
		// dp[i+1] = s[0..i] 的最少 beautiful 分割段数
		int n = s.length(), num = 1;
		// 构建反向二进制 Trie：将 5 的幂按二进制从低位到高位插入
		// 这样从字符串位置 i 往左扫时，可以逐 bit 匹配
		TrieNode root = new TrieNode();
		// 枚举所有二进制长度 <= n 的 5 的幂：1, 5, 25, 125, 625, ...
		while ((32 - Integer.numberOfLeadingZeros(num)) <= n) {
			insert(root, num);
			num *= 5;
		}
		// dp[i+1] = s[0..i] 最少分成几个 beautiful 子串
		// dp[0] = 0（空串，0 段）
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = MAX;
			TrieNode curr = root;
			// 从位置 i 往左扫，在反向 Trie 中匹配
			for (int j = i; j >= 0; j--) {
				char c = s.charAt(j);
				if (!curr.containsKey(c)) {
					break; // Trie 中无此路径，更长的也不可能匹配
				}
				curr = curr.get(c);
				if (curr.getEnd()) {
					// s[j..i] 的二进制值是 5 的幂，分割段数 = dp[j] + 1
					res = Math.min(res, 1 + dp[j]);
				}
			}
			dp[i + 1] = res;
		}
		// dp[n] > n 说明无法完成划分
		return dp[n] > n ? -1 : dp[n];
	}

	// time O(n * L), space O(n + L)
	public int minimumBeautifulSubstringsDFSWithMemorization(String s) {
		// dfs(i) = s[0..i] 的最少 beautiful 分割段数
		int n = s.length(), num = 1;
		// 构建反向二进制 Trie
		TrieNode root = new TrieNode();
		while ((32 - Integer.numberOfLeadingZeros(num)) <= n) {
			insert(root, num);
			num *= 5;
		}
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		int cnt = dfs(n - 1, s, root, memo);
		return cnt > n ? -1 : cnt;
	}

	private int dfs(int i, String s, TrieNode root, int[] memo) {
		// dfs(i) = s[0..i] 的最少 beautiful 分割段数
		if (i == -1) {
			return 0; // 所有字符都处理完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = MAX;
		TrieNode curr = root;
		// 从位置 i 往左扫，在反向 Trie 中匹配
		for (int j = i; j >= 0; j--) {
			char c = s.charAt(j);
			if (!curr.containsKey(c)) {
				break;
			}
			curr = curr.get(c);
			if (curr.getEnd()) {
				// s[j..i] 是 5 的幂的二进制，递归处理 s[0..j-1]
				res = Math.min(res, 1 + dfs(j - 1, s, root, memo));
			}
		}
		return memo[i] = res;
	}

	// 将 num 的二进制表示按从低位到高位的顺序插入 Trie（反向插入）
	// 例如 5 = 101，插入顺序为 1 → 0 → 1
	// 这样从字符串位置 i 往左扫（即从低位到高位）时可以直接匹配
	private void insert(TrieNode root, int num) {
		TrieNode curr = root;
		while (num > 0) {
			char c = (char) ('0' + (num & 1)); // 取最低位：0 或 1
			if (!curr.containsKey(c)) {
				curr.put(c, new TrieNode());
			}
			curr = curr.get(c);
			num >>= 1; // 右移，处理下一位
		}
		curr.setEnd(); // 标记完整的 5 的幂
	}

	// 二进制 Trie：每个节点只有 '0' 和 '1' 两个子节点
	private static class TrieNode {

		private final TrieNode[] nodes; // nodes[0] = '0' 子节点, nodes[1] = '1' 子节点
		private boolean isEnd;

		public TrieNode() {
			nodes = new TrieNode[2];
			isEnd = false;
		}

		public boolean containsKey(char key) {
			return nodes[key - '0'] != null;
		}

		public TrieNode get(char key) {
			return nodes[key - '0'];
		}

		public void put(char key, TrieNode node) {
			nodes[key - '0'] = node;
		}

		public boolean getEnd() {
			return isEnd;
		}

		public void setEnd() {
			isEnd = true;
		}
	}
}
