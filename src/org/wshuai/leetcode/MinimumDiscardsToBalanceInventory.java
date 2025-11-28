package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2025.
 * #3679 https://leetcode.com/problems/minimum-discards-to-balance-inventory/
 */
public class MinimumDiscardsToBalanceInventory {

    // time O(n), space O(n)
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int res = 0, n = arrivals.length;
        int[] freq = new int[100_001];
        boolean[] discarded = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (freq[arrivals[i]] < m) {
                freq[arrivals[i]]++;
            } else {
                discarded[i] = true;
                res++;
            }
            // Deduct only if window left is valid and hasn't been discarded
            if (i - w + 1 >= 0 && !discarded[i - w + 1]) {
                freq[arrivals[i - w + 1]]--;
            }
        }
        return res;
    }
}
