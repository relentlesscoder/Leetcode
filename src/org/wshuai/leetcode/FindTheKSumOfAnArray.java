package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/22/2024.
 * #2386 https://leetcode.com/problems/find-the-k-sum-of-an-array/
 */
public class FindTheKSumOfAnArray {

    // time O(n * log(n) + k * log(n)), space O(n)
    public long kSum(int[] nums, int k) {
        PriorityQueue<long[]> minQueue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long maxSum = 0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                maxSum += nums[i];
            } else {
                nums[i] = Math.abs(nums[i]);
            }
        }
        if (k == 1) {
            return maxSum;
        }
        k--;
        Arrays.sort(nums);
        minQueue.offer(new long[]{nums[0], 0});
        while (!minQueue.isEmpty()) {
            long[] curr = minQueue.poll();
            long val = curr[0];
            int index = (int) curr[1];
            if (--k == 0) {
                return maxSum - val;
            }
            if (index < nums.length - 1) {
                minQueue.offer(new long[]{val + nums[index + 1], index + 1});
                minQueue.offer(new long[]{val - nums[index] + nums[index + 1], index + 1});
            }
        }
        return -1;
    }
}
