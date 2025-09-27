package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2025.
 * #3503 https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-i/
 */
public class LongestPalindromeAfterSubstringConcatenationI {

    // time O(m * n + m^2 + n^2), space O(m * n)
    public int longestPalindrome(String s, String t) {
        String revS = new StringBuilder(s).reverse().toString();
        String revT = new StringBuilder(t).reverse().toString();
        return Math.max(calc(s.toCharArray(), t.toCharArray()), calc(revT.toCharArray(), revS.toCharArray()));
    }

    private int calc(char[] s, char[] t) {
        int res = 0, n = s.length, m = t.length;
        int[] mx = new int[n + 1];
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i] == t[j]) {
                    dp[i + 1][j] = dp[i][j + 1] + 1;
                    mx[i + 1] = Math.max(mx[i + 1], dp[i + 1][j]);
                }
            }
            res = Math.max(res, mx[i + 1] * 2);
        }

        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2, r = (i + 1) / 2;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            if (l + 1 <= r - 1) {
                res = Math.max(res, r - l - 1 + mx[l + 1] * 2);
            }
        }

        return res;
    }
}
