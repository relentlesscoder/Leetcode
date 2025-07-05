package org.wshuai.leetcode;

/**
 * Created by Wei on 07/05/2025.
 * #2743 https://leetcode.com/problems/count-substrings-without-repeating-character/
 */
public class CountSubstringsWithoutRepeatingCharacter {

    // time O(n), space O(1)
    public int numberOfSpecialSubstrings(String s) {
        int res = 0, bitMask = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            while ((bitMask & (1 << (s.charAt(j) - 'a'))) > 0) {
                bitMask -= (1 << (s.charAt(i++) - 'a'));
            }
            bitMask += (1 << (s.charAt(j) - 'a'));
            res += j - i + 1; // adds substrings ends with s[j] and starts with s[i ... j]
        }
        return res;
    }
}
