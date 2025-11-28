package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2025.
 * #2107 https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies/
 */
public class NumberOfUniqueFlavorsAfterSharingKCandies {

    // time O(n), space O(1)
    public int shareCandies(int[] candies, int k) {
        int res = 0, n = candies.length, count = 0;
        int[] freq = new int[100_001];
        for (int c : candies) {
            if (freq[c]++ == 0) {
                count++;
            }
        }
        if (k == 0) {
            return count;
        }
        for (int i = 0; i < n; i++) {
            // All candies of type candies[i] are shared
            if (--freq[candies[i]] == 0) {
                count--;
            }
            if (i - k + 1 < 0) {
                continue;
            }
            res = Math.max(res, count);
            // Remove one candy of type candies[i - k + 1] from shared candies
            if (freq[candies[i - k + 1]]++ == 0) {
                count++;
            }
        }
        return res;
    }
}
