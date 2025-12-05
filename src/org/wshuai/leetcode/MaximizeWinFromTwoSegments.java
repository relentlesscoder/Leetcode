package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2025.
 * #2555 https://leetcode.com/problems/maximize-win-from-two-segments/
 */
public class MaximizeWinFromTwoSegments {

    // time O(n), space O(n)
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        // [0,1,    2,  3,4,  5] k = 2
        //  x ... x+k y-k ... y
        // x + k >= y - k - 1
        // 2k + 1 >= y - x
        // y - x <= 2k + 1
        if (prizePositions[n - 1] - prizePositions[0] <= 2 * k + 1) {
            return n;
        }
        int res = 0;
        int[] max = new int[n + 1];
        for (int right = 0, left = 0; right < n; right++) {
            // Sliding window to ensure size <= k
            while (prizePositions[right] - prizePositions[left] > k) {
                left++;
            }
            res = Math.max(res, right - left + 1 + max[left]);
            // Maintain max segment prize in [0, right]
            max[right + 1] = Math.max(max[right], right - left + 1);
        }
        return res;
    }
}
