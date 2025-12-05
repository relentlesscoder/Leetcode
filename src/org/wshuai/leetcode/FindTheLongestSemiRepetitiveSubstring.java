package org.wshuai.leetcode;

/**
 * Created by Wei on 11/30/2025.
 * #2730 https://leetcode.com/problems/find-the-longest-semi-repetitive-substring/
 */
public class FindTheLongestSemiRepetitiveSubstring {

    // time O(n), space O(1)
    public int longestSemiRepetitiveSubstring(String s) {
        // For substring that has two repetitive adjacent numbers like
        //   i: 0 1 2 3 4 5 6 7 8
        //   c: 2 3 0 0 3 2 3 1 1
        // When the right end of the sliding window hits index 8 we found
        // two pairs which invalidate the window, we can extend the left
        // end up to the index 3 to revalidate the window. Only difference
        // is that we validate the left character (of a pair) when advance
        // the left end of the window, so it can stop at the second
        // character.
        int res = 1, n = s.length();
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            count += i > 0 && s.charAt(i) == s.charAt(i - 1) ? 1 : 0;
            while (count > 1) {
                count -= s.charAt(j) == s.charAt(j + 1) ? 1 : 0;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
