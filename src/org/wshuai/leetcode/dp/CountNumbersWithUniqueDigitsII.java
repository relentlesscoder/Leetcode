package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 12/30/2025.
 * #3032 https://leetcode.com/problems/count-numbers-with-unique-digits-ii/
 */
public class CountNumbersWithUniqueDigitsII {

    // time O((b - a + 1) * log(b)), space O(1)
    public int numberCount(int a, int b) {
        int res = 0;
        for (int i = a; i <= b; i++) {
            boolean valid = true;
            int[] cnt = new int[10];
            for (int v = i; v > 0; v /= 10) {
                if (cnt[v % 10]++ == 1) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                res++;
            }
        }
        return res;
    }
}
