package org.wshuai.leetcode;

/**
 * Created by Wei on 08/25/2019.
 * #1100 https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {

    // time O(n), space O(1)
    public int numKLenSubstrNoRepeats(String s, int k) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
            if (i - k + 1 < 0) {
                continue;
            }
            boolean repeated = false;
            for (int f : freq) {
                if (f > 1) {
                    repeated = true;
                    break;
                }
            }
            if (!repeated) {
                res++;
            }
            freq[s.charAt(i - k + 1) - 'a']--;
        }
        return res;
    }
}
