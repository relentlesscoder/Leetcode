package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #1930 https://leetcode.com/problems/unique-length-3-palindromic-subsequences/
 */
public class UniqueLength3PalindromicSubsequences {

    // time O(n), space O(1)
    public int countPalindromicSubsequence(String s) {
        int res = 0, n = s.length();
        int[][] unique = new int[26][2];
        for (int i = 0; i < 26; i++) {
            unique[i] = new int[]{n, -1};
        }
        // for each unique character, find min and max index
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int[] curr = unique[c - 'a'];
            curr[0] = Math.min(curr[0], i);
            curr[1] = Math.max(curr[1], i);
        }
        for (int i = 0; i < 26; i++) {
            if (unique[i][1] == -1) {
                continue;
            }
            // for each min and max pair, count number of 3 letter palindromes
            int[] counter = new int[26];
            for (int k = unique[i][0] + 1; k < unique[i][1]; k++) {
                if (counter[s.charAt(k) - 'a']++ == 0) {
                    res++;
                }
            }
        }
        return res;
    }
}
