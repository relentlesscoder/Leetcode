package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/25/2025.
 * #2163 https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/
 */
public class MinimumDifferenceInSumsAfterRemovalOfElements {

    // time O(n * log(n)), space O(n)
    public long minimumDifference(int[] nums) {
        // Choose a positive integer k in the interval [n,2n].
        // The first k numbers of the array nums belong to the first part,
        // but only n of them can be retained.
        // The last 3n−k numbers of the array nums belong to the second part,
        // but again, only n of them can be retained.
        // The goal is to minimize the difference between the sum of the first
        // part and the sum of the second part.
        // The reason k ∈ [n,2n] is required is that we must ensure each part
        // has at least n elements.
        int len = nums.length, n = len / 3;
        long[] left = new long[n + 1];
        long sum = 0;

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a),
                minQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxQueue.offer(nums[i]);
        }
        // left[i - (n - 1)] is the minimum sum we can get from elements between [0, i]
        left[0] = sum;
        for (int i = n; i < n * 2; i++) {
            sum += nums[i];
            maxQueue.offer(nums[i]);
            sum -= maxQueue.poll();
            left[i - (n - 1)] = sum;
        }

        sum = 0;
        for (int i = n * 3 - 1; i >= n * 2; i--) {
            sum += nums[i];
            minQueue.offer(nums[i]);
        }
        long res = left[n] - sum;
        // sum is the maximum sum we can get from elements between [i, nums.length - 1]
        for (int i = n * 2 - 1; i >= n; i--) {
            sum += nums[i];
            minQueue.offer(nums[i]);
            sum -= minQueue.poll();
            res = Math.min(res, left[i - n] - sum);
        }
        return res;
    }
}
