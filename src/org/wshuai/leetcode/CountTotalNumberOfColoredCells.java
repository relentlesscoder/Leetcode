package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2025.
 * #2579 https://leetcode.com/problems/count-total-number-of-colored-cells/
 */
public class CountTotalNumberOfColoredCells {

    // time O(1), space O(1)
    public long coloredCellsMath(int n) {
        // 1 + (4 * 1) + ... + (4 * (n - 1))
        // 1 + 4 * (n * (n - 1) / 2)
        // 1 + 2 * n * (n - 1)
        return 1 + (long) n * (n - 1) * 2;
    }

    // time O(n), space O(1)
    public long coloredCells(int n) {
        // 1 + (4 * 1) + ... + (4 * (n - 1))
        long res = 1;
        int added = 4;
        for (int i = 2; i <= n; i++) {
            res += added;
            added += 4;
        }
        return res;
    }
}
