package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/12/2023.
 * #2707 https://leetcode.com/problems/extra-characters-in-a-string/
 */
public class ExtraCharactersInAString {

    // time O(n^2 + m * l), space O(n + m * l)
    public int minExtraCharBottomUpDPTrie(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1];
        TrieNode root = new TrieNode();
        for (String word : dictionary) {
            insertWord(word, root);
        }
        for (int start = n - 1; start >= 0; start--) {
            dp[start] = dp[start + 1] + 1;
            TrieNode curr = root;
            for (int end = start; end < n; end++) {
                char c = s.charAt(end);
                if (!curr.containsKey(c)) {
                    break;
                }
                curr = curr.get(c);
                if (curr.isEnd()) {
                    dp[start] = Math.min(dp[start], dp[end + 1]);
                }
            }
        }
        return dp[0];
    }

    // time O(n^2 + m * l), space O(n + m * l)
    public int minExtraCharTopDownDPTrie(String s, String[] dictionary) {
        int n = s.length();
        Integer[] dp = new Integer[n];
        TrieNode root = new TrieNode();
        for (String word : dictionary) {
            insertWord(word, root);
        }
        return dfsTrie(s, 0, n, dp, root);
    }

    private int dfsTrie(String s, int start, int n, Integer[] dp, TrieNode root) {
        if (start == n) {
            return 0;
        }
        if (dp[start] != null) {
            return dp[start];
        }
        int res = dfsTrie(s, start + 1, n, dp, root) + 1;
        TrieNode curr = root;
        for (int end = start; end < n; end++) {
            char c = s.charAt(end);
            if (!curr.containsKey(c)) {
                break;
            }
            curr = curr.get(c);
            if (curr.isEnd()) {
                res = Math.min(res, dfsTrie(s, end + 1, n, dp, root));
            }
        }
        dp[start] = res;
        return res;
    }

    private void insertWord(String word, TrieNode root) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    // time O(n^3), space O(n + m * l)
    public int minExtraCharBottomUpDP(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        for (int end = 1; end <= n; end++) {
            dp[end] = dp[end - 1] + 1;
            for (int start = 0; start < end; start++) {
                if (dict.contains(s.substring(start, end))) {
                    dp[end] = Math.min(dp[end], dp[start]);
                }
            }
        }
        return dp[n];
    }

    // time O(n^3), space O(n + m * l)
    public int minExtraCharTopDownDP(String s, String[] dictionary) {
        int n = s.length();
        Integer[] dp = new Integer[n];
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        return dfs(s, 0, n, dp, dict);
    }

    private int dfs(String s, int start, int n, Integer[] dp, Set<String> dict) {
        if (start == n) {
            return 0;
        }
        if (dp[start] != null) {
            return dp[start];
        }
        int res = dfs(s, start + 1, n, dp, dict) + 1;
        for (int end = start + 1; end <= n; end++) {
            String curr = s.substring(start, end);
            if (dict.contains(curr)) {
                res = Math.min(res, dfs(s, end, n, dp, dict));
            }
        }
        dp[start] = res;
        return res;
    }

    private class TrieNode{

        private static final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char key) {
            return links[key - 'a'] != null;
        }

        public TrieNode get(char key) {
            return links[key - 'a'];
        }

        public void put(char key, TrieNode node) {
            links[key - 'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }
    }
}

