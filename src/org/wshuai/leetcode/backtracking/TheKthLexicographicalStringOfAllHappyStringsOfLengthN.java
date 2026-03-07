package org.wshuai.leetcode.backtracking;

/**
 * Created by Wei on 04/19/2020.
 * #1415 https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 */
public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {

    private int cnt = 0;
    private String res = "";

    // time O(2^n), space O(n)
    public String getHappyString(int n, int k) {
        cnt = 0;
        res = "";
        dfs(0, new char[n], k);
        return res;
    }

    private void dfs(int i, char[] s, int k) {
        if (i == s.length || cnt == k) { // 优化 如果 cnt == k 提早结束搜素
            if (++cnt == k) { // 第 k 个字符串
                res = new String(s);
            }
            return;
        }
        for (char x = 'a'; x <= 'c'; x++) {
            if (i == 0 || s[i - 1] != x) {
                s[i] = x;
                dfs(i + 1, s, k);
            }
        }
    }
}
