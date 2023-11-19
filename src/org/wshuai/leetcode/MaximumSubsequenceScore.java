package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/11/2023.
 * #2542 https://leetcode.com/problems/maximum-subsequence-score/
 */
public class MaximumSubsequenceScore {

    // time O(n*log(n)), space O(n)
    public long maxScore(int[] nums1, int[] nums2, int k) {
        long res = 0, currSum = 0;
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[] {nums1[i], nums2[i]};
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            currSum += pairs[i][0];
            queue.offer(pairs[i][0]);
        }
        res = currSum * pairs[k - 1][1];
        for (int i = k; i < n; i++) {
            currSum += pairs[i][0] - queue.poll();
            queue.offer(pairs[i][0]);
            res = Math.max(res, currSum * pairs[i][1]);
        }
        return res;
    }
}
