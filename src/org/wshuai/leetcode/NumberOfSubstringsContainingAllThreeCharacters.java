package org.wshuai.leetcode;

/**
 * Created by Wei on 02/22/2020.
 * #1358 https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 */
public class NumberOfSubstringsContainingAllThreeCharacters {

    // time O(n), space O(1)
    public int numberOfSubstrings(String s) {
        int res = 0, n = s.length();
        int[] freq = new int[3];
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            if (freq[s.charAt(i) - 'a']++ == 0) {
                count++;
            }
            while (count >= 3) {
                if (--freq[s.charAt(j++) - 'a'] == 0) {
                    count--;
                }
            }
            res += j;
        }
        return res;
    }
}
