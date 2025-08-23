package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 08/23/2025.
 * #2901 https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii
 */
public class LongestUnequalAdjacentGroupsSubsequenceII {

    // time O(n^2 * m), space O(n)
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        LinkedList<String> res = new LinkedList<>();
        int n = words.length, max = 1, index = 0;
        int[] dp = new int[n], root = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            root[i] = -1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (groups[i] != groups[j] && validHammingDistance(words[i], words[j])) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        root[i] = j;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        while (index >= 0) {
            res.offerFirst(words[index]);
            index = root[index];
        }
        return res;
    }

    private boolean validHammingDistance(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i) && ++count == 2) {
                return false;
            }
        }
        return count == 1;
    }
}
