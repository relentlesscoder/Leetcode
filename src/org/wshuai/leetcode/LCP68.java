package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2025.
 * #LCP68 https://leetcode.cn/problems/1GxJYY/
 */
public class LCP68 {

    // time O(n), space O(1)
    public int beautifulBouquet(int[] flowers, int cnt) {
        int res = 0, n = flowers.length;
        int[] freq = new int[100_001];
        for (int i = 0, j = 0, overloaded = 0; i < n; i++) {
            if (freq[flowers[i]]++ == cnt) {
                overloaded++;
            }
            while (overloaded > 0) {
                if (--freq[flowers[j++]] == cnt) {
                    overloaded--;
                }
            }
            res += i - j + 1;
        }
        return res;
    }
}
