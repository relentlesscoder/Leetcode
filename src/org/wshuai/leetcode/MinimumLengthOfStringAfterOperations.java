package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3223 https://leetcode.com/problems/minimum-length-of-string-after-operations/
 */
public class MinimumLengthOfStringAfterOperations {

    // time O(n), space O(1)
    public int minimumLength(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) {
                continue;
            }
            if (freq[i] % 2 == 1) { // 1 1 1 1 1
                res += 1;
            } else if (freq[i] % 2 == 0) { // 1 1 1 1
                res += 2;
            }
        }
        return res;
    }
}
