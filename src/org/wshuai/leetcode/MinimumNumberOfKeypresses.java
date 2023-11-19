package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/06/2023.
 * #2268 https://leetcode.com/problems/minimum-number-of-keypresses/
 */
public class MinimumNumberOfKeypresses {

    // time O(n), space O(1)
    public int minimumKeypresses(String s) {
        int res = 0;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        Arrays.sort(count);
        for (int i = 25, j = 0; i >= 0; i--, j++) {
            // greedy, map the most frequent characters to the button which requires the least presses
            res += count[i] * (j / 9 + 1);
        }
        return res;
    }
}
