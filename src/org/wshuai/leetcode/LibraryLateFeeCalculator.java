package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2025.
 * #3687 https://leetcode.com/problems/library-late-fee-calculator/
 */
public class LibraryLateFeeCalculator {

    // time O(n), space O(1)
    public int lateFee(int[] daysLate) {
        int res = 0;
        for (int days : daysLate) {
            if (days == 1) {
                res += 1;
            } else if (days > 5) {
                res += 3 * days;
            } else {
                res += 2 * days;
            }
        }
        return res;
    }
}
