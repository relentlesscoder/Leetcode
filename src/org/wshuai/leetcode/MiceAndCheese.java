package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/05/2025.
 * #2611 https://leetcode.com/problems/mice-and-cheese/
 */
public class MiceAndCheese {

    // time O(n * log(n)), space O(1)
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        // res = sum(reward2) + sum(k largest reward1[i]-reward2[i])
        int sum = 0, n = reward1.length;
        for (int i = 0; i < n; i++) {
            sum += reward2[i];
            reward1[i] -= reward2[i];
        }
        Arrays.sort(reward1);
        for (int i = 0; i < k; i++) {
            sum += reward1[n - i - 1];
        }
        return sum;
    }
}
