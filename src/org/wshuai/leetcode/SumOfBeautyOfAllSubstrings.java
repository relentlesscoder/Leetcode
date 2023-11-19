package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #1781 https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
 */
public class SumOfBeautyOfAllSubstrings {

    // time O(n^2), space O(n)
    public int beautySum(String s) {
        int res = 0, n = s.length();
        // pre-calculate prefix sum for character frequency
        int[][] prefixSum = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                prefixSum[i + 1][j] += prefixSum[i][j];
            }
            prefixSum[i + 1][s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                // for each substring (length > 2), calculate the max and min frequency
                int min = 501, max = 0;
                for (int k = 0; k < 26; k++) {
                    int count = prefixSum[j + 1][k] - prefixSum[i][k];
                    if (count > 0) {
                        min = Math.min(min, count);
                        max = Math.max(max, count);
                    }
                }
                if (max > 0) {
                    res += max - min;
                }
            }
        }
        return res;
    }
}
