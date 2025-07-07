package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3085 https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/
 */
public class MinimumDeletionsToMakeStringKSpecial {

    // time O(n), space O(1)
    public int minimumDeletions(String word, int k) {
        int res = Integer.MAX_VALUE;
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            int curr = 0;
            for (int j = 0; j < 26; j++) {
                curr += freq[j] < freq[i] ? freq[j] : Math.max(0, freq[j] - (freq[i] + k));
            }
            res = Math.min(res, curr);
        }
        return res;
    }
}
