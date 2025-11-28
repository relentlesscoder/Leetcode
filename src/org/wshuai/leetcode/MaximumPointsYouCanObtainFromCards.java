package org.wshuai.leetcode;

/**
 * Created by Wei on 04/26/2020.
 * #1423 https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 */
public class MaximumPointsYouCanObtainFromCards {

    // time O(n), space O(1)
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length, windowSum = 0;
        // Result is the max of sum of the two subarray [x -> n - 1]
        // and [0 -> y] which has a total length of k
        for (int i = n - k; i < n; i++) {
            windowSum += cardPoints[i];
        }
        int res = windowSum;
        for (int i = 0, j = n - k; i < k; i++) {
            windowSum += cardPoints[i] - cardPoints[j++];
            res = Math.max(res, windowSum);
        }
        return res;
    }

    // time O(n), space O(1)
    public int maxScoreReverseSlidingWindow(int[] cardPoints, int k) {
        int n = cardPoints.length,
                m = n - k, // Size of the sliding window
                minSum = Integer.MAX_VALUE, // Minimum of all sliding window sum
                sum = 0, // Total sum of the array
                windowSum = 0; // Sum of the sliding window
        for (int p : cardPoints) {
            sum += p;
        }
        if (k == n) {
            return sum;
        }
        // Use sliding widow to calculate minimum sum minSum for all subarray with
        // size n - k, the result is sum - minSum.
        for (int i = 0; i < n; i++) {
            windowSum += cardPoints[i];
            if (i - m + 1 < 0) {
                continue;
            }
            minSum = Math.min(minSum, windowSum);
            windowSum -= cardPoints[i - m + 1];
        }
        return sum - minSum;
    }
}
