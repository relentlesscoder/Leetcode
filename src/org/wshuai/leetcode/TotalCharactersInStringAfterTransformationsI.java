package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2025.
 * #3335 https://leetcode.com/problems/total-characters-in-string-after-transformations-i/
 */
public class TotalCharactersInStringAfterTransformationsI {

    private static final int MOD = (int)1e9 + 7;

    // time O(t), space O(1)
    public int lengthAfterTransformationsSpaceOptimized(String s, int t) {
        int res = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 1; i <= t; i++) {
            int[] next = new int[26];
            for (int j = 0; j < 25; j++) {
                next[j + 1] = freq[j];
            }
            res = (res + freq[25]) % MOD;
            next[0] = (next[0] + freq[25]) % MOD;
            next[1] = (next[1] + freq[25]) % MOD;
            freq = next;
        }
        return res;
    }

    // time O(t), space O(t)
    public int lengthAfterTransformations(String s, int t) {
        int[] freq = new int[26];
        // count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int[] dp = new int[t + 1];
        dp[0] = s.length();
        for (int i = 1; i <= t; i++) {
            int[] next = new int[26];
            // transform character from 'a' to 'y' to next
            // the length of total characters is unchanged
            for (int j = 0; j < 25; j++) {
                next[j + 1] = freq[j];
            }
            // since 'z' will transform to 'a' and 'b', the
            // length is increased by freq['z']
            dp[i] = (dp[i - 1] + freq[25]) % MOD;
            // increase frequency of 'a' by freq['z']
            next[0] = (next[0] + freq[25]) % MOD;
            // increase frequency of 'b' by freq['z']
            next[1] = (next[1] + freq[25]) % MOD;
            freq = next;
        }
        return dp[t];
    }
}
