package org.wshuai.leetcode;

/**
 * Created by Wei on 01/22/2024.
 * #3014 https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/
 */
public class MinimumNumberOfPushesToTypeWordI {

    // time O(n), space O(1)
    public int minimumPushes(String word) {
        int res = 0, n = word.length();
        for (int i = 1, j = 1; i <= n; i++) {
            res += j;
            if (i % 8 == 0) {
                j++;
            }
        }
        return res;
    }
}
