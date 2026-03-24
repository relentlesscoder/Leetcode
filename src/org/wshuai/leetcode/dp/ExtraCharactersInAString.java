package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/12/2023.
 * #2707 https://leetcode.com/problems/extra-characters-in-a-string/
 */
public class ExtraCharactersInAString {

    // time O(n * L), space O(n + m * L)
    public int minExtraCharDP(String s, String[] dictionary) {
        // 核心思路：dp[i+1] = s[0..i] 中最少的多余字符数
        // 记忆化搜索的 DP 翻译版，用反向 Trie 加速匹配
        int n = s.length(), max = 0;
        // 构建反向 Trie：单词逆序插入，方便从位置 i 往左扫匹配
        TrieNode root = new TrieNode();
        for (String w : dictionary) {
            max = Math.max(max, w.length()); // 记录最长单词长度，用于剪枝
            insert(root, w);
        }
        // dp[i+1] = s[0..i] 中最少的多余字符数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 空串，0 个多余字符
        for (int i = 0; i < n; i++) {
            TrieNode node = root;
            // 选择一：s[i] 作为多余字符，多余数 = dp[i] + 1
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            // 选择二：从位置 i 往左扫，尝试匹配以 i 结尾的字典单词
            for (int j = i; j >= Math.max(i - max + 1, 0); j--) {
                char c = s.charAt(j);
                if (!node.containsKey(c)) {
                    break; // Trie 中无此路径，更长的也不可能匹配
                }
                node = node.get(c);
                if (node.isEnd()) {
                    // s[j..i] 是字典单词，这些字符不是多余的，多余数 = dp[j]
                    dp[i + 1] = Math.min(dp[i + 1], dp[j]);
                }
            }
        }
        return dp[n];
    }

    // time O(n * L), space O(n + m * L)
    public int minExtraCharDFSWithMemorization(String s, String[] dictionary) {
        // 核心思路：记忆化搜索 + 反向 Trie
        // dfs(i) = s[0..i] 中最少的"多余字符"数
        // 每个位置有两种选择：
        // 1. s[i] 作为多余字符，多余数 +1，递归 dfs(i-1)
        // 2. s[j..i] 匹配字典中的某个单词，多余数 +0，递归 dfs(j-1)
        // 与 #0139 WordBreak 类似，区别是 WordBreak 只判断能否拼接，这题要最小化剩余字符
        int n = s.length(), max = 0;
        // 构建反向 Trie：单词逆序插入，方便从位置 i 往左扫匹配
        TrieNode root = new TrieNode();
        for (String w : dictionary) {
            max = Math.max(max, w.length()); // 记录最长单词长度，用于剪枝
            insert(root, w);
        }
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(n - 1, max, s, root, memo);
    }

    private int dfs(int i, int max, String s, TrieNode root, int[] memo) {
        if (i == -1) {
            return 0; // 所有字符都处理完了
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        // 选择：把 s[i] 当作多余字符，多余数 +1，递归处理 s[0..i-1]
        int res = 1 + dfs(i - 1, max, s, root, memo);
        // 从位置 i 往左扫，在反向 Trie 中匹配
        // 最远到 i - max + 1（超过最长单词长度就不可能匹配了）
        TrieNode node = root;
        for (int j = i; j >= Math.max(i - max + 1, 0); j--) {
            char c = s.charAt(j);
            if (!node.containsKey(c)) {
                break; // Trie 中无此路径，更长的也不可能匹配
            }
            node = node.get(c);
            if (node.isEnd()) {
                // s[j..i] 是字典中的单词，这些字符不是多余的，递归处理 s[0..j-1]
                res = Math.min(res, dfs(j - 1, max, s, root, memo));
            }
        }
        return memo[i] = res;
    }

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
