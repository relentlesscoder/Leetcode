package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2019.
 * #0567 https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {

    // time O(n), space O(1)
    public boolean checkInclusionSlidingWindow(String s1, String s2) {
        // Same as #0438
        int m = s1.length(), n = s2.length();
        int[] freq = new int[26];
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0, j = 0; j < n; j++) {
            int index = s2.charAt(j) - 'a';
            freq[index]--;
            while (freq[index] < 0) {
                ++freq[s2.charAt(i++) - 'a'];
            }
            if (j - i + 1 == m) {
                return true;
            }
        }
        return false;
    }

    // time O(n), space O(1)
    public boolean checkInclusionFixedLengthSlidingWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] freq = new int[26];
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            freq[s2.charAt(i) - 'a']--;
            if (i - m + 1 < 0) {
                continue;
            }
            boolean match = true;
            for (int f : freq) {
                if (f != 0) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
            freq[s2.charAt(i - m + 1) - 'a']++;
        }
        return false;
    }
}
