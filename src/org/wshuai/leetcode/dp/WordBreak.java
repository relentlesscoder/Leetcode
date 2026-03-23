package org.wshuai.leetcode.dp;

import java.util.List;

/**
 * Created by Wei on 08/21/2016.
 * #0139 https://leetcode.com/problems/word-break/
 */
public class WordBreak {

	// time O(n * L), space O(n + m * L)
	public boolean wordBreakDP(String s, List<String> wordDict) {
		// 核心思路：dp[i+1] = s[0..i] 能否被字典中的单词拼接而成
		// 用反向 Trie 加速匹配：从位置 i 往左扫，在 Trie 中同步走，匹配到完整单词就检查 dp[j]
		int n = s.length(), max = 0;
		// 构建反向 Trie：单词按逆序插入 ("abc" 插入为 c→b→a)
		// 这样从位置 i 往左扫时，可以直接在 Trie 中匹配
		TrieNode root = new TrieNode();
		for (String w : wordDict) {
			max = Math.max(max, w.length()); // 记录最长单词长度，用于剪枝
			insert(root, w);
		}
		// dp[i+1] = true 表示 s[0..i] 能被字典单词拼接
		boolean[] dp = new boolean[n + 1];
		dp[0] = true; // 空串是合法拼接
		for (int i = 0; i < n; i++) {
			TrieNode node = root;
			// 从位置 i 往左扫，尝试匹配以 i 结尾的所有可能单词
			// 最远到 i - max + 1（超过最长单词长度就不可能匹配了)
			for (int j = i; j >= Math.max(i - max + 1, 0); j--) {
				char c = s.charAt(j);
				if (!node.containsKey(c)) {
					break; // Trie 中无此路径，后续更长的也不可能匹配
				}
				node = node.get(c);
				// 走到一个完整单词的末尾 → s[j..i] 是字典中的单词
				// 再检查 dp[j] → s[0..j-1] 是否也能合法拼接
				if (node.isEnd() && dp[j]) {
					dp[i + 1] = true;
					break; // 已找到合法拼接，无需继续
				}
			}
		}
		return dp[n];
	}

	// time O(n * L), space O(n + m * L)
	public boolean wordBreakDFSWithMemorization(String s, List<String> wordDict) {
		// dfs(i) = s[0..i] 能否被字典单词拼接 (从右往左递归)
		int n = s.length(), max = 0;
		TrieNode root = new TrieNode();
		for (String w : wordDict) {
			max = Math.max(max, w.length());
			insert(root, w);
		}
		// Boolean 包装类区分 null (未计算) 和 false (计算结果)
		Boolean[] memo = new Boolean[n];
		return dfs(n - 1, s, max, root, memo);
	}

	// dfs(i) = s[0..i] 能否被字典单词拼接
	private boolean dfs(int i, String s, int max, TrieNode root, Boolean[] memo) {
		if (i == -1) {
			return true; // 所有字符都已匹配完
		}
		if (memo[i] != null) {
			return memo[i];
		}
		// 从位置 i 往左扫，在反向 Trie 中匹配
		TrieNode node = root;
		for (int j = i; j >= Math.max(i - max + 1, 0); j--) {
			char c = s.charAt(j);
			if (!node.containsKey(c)) {
				break;
			}
			node = node.get(c);
			// s[j..i] 是字典单词，递归检查 s[0..j-1]
			if (node.isEnd() && dfs(j - 1, s, max, root, memo)) {
				return memo[i] = true;
			}
		}
		// 所有切分方式都不行
		return memo[i] = false;
	}

	// 反向插入单词到 Trie：单词 "abc" 按 c→b→a 顺序插入
	// 这样从字符串某个位置往左扫时，可以逐字符在 Trie 中匹配
	private void insert(TrieNode root, String word) {
		TrieNode node = root;
		for (int i = word.length() - 1; i >= 0; i--) {
			char c = word.charAt(i);
			if (!node.containsKey(c)) {
				node.put(c, new TrieNode());
			}
			node = node.get(c);
		}
		node.setEnd(); // 标记单词结尾
	}

	private static class TrieNode {

		private final TrieNode[] nodes; // 26 个字母的子节点
		private boolean isEnd; // 是否是某个单词的结尾

		public TrieNode() {
			nodes = new TrieNode[26];
			isEnd = false;
		}

		public TrieNode get(char key) {
			return nodes[key - 'a'];
		}

		public boolean containsKey(char key) {
			return nodes[key - 'a'] != null;
		}

		public void put(char key, TrieNode node) {
			nodes[key - 'a'] = node;
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd() {
			isEnd = true;
		}
	}
}
