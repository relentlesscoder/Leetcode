package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #3325 https://leetcode.com/problems/count-substrings-with-k-frequency-characters-i/
 */
public class CountSubstringsWithKFrequencyCharactersI {

    // time O(n), space O(1)
    public int numberOfSubstrings(String s, int k) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            if (++freq[s.charAt(i) - 'a'] == k) {
                count++;
            }
            while (count > 0) {
                if (freq[s.charAt(j++) - 'a']-- == k) {
                    count--;
                }
            }
            res += j;
        }
        return res;
    }
}
