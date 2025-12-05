package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/01/2025.
 * #2968 https://leetcode.com/problems/apply-operations-to-maximize-frequency-score/
 */
public class ApplyOperationsToMaximizeFrequencyScore {

    // time O(n), space O(1)
    public int maxFrequencyScoreSlidingWindow(int[] nums, long k) {
        // For each sliding window boundary changes (numbers in/out),
        // we are tracking the delta to the operations.
        int res = 0, n = nums.length;
        long operations = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            // 1. Numbers in:
            //    a. Window size is odd: in this case the original median
            //       nums[(l + r) / 2] is still the median, since total
            //       operations for all existing numbers in (l,r) is unchanged
            //       so this increases the operations by
            //       nums[i] - nums[(l + r) / 2];
            //    l          r
            //    0   1  2 | 3
            //    2   4  7 | 9
            //        m
            //    b. Window size is even: in this case the original right
            //       median nums[(l + r' + 1) / 2] becomes the median, the
            //       total operations for existing numbers in (l,r) stays
            //       unchanged by changing to right median so this also
            //       increases the operations by nums[i] - nums[(l + r) / 2].
            //    l          r
            //    0 1 2 3 |  4
            //    2 4 7 9 | 11
            //      m m'
            operations += (long) nums[i] - nums[j + (i - j) / 2];
            while (operations > k) {
                // Numbers out case is the same with only difference is that
                // the median index is nums[(l + r + 1) / 2]
                // 1. Numbers out:
                //    a. Window size is odd:
                //    l             r
                //    0 | 1 2    3  4
                //    2 | 4 7    9 11
                //          m
                //    b. Window size is even:
                //    l             r
                //    0 | 1 2 3  4  5
                //    2 | 4 7 9 11 13
                //          m m'
                operations -= (long) nums[j + (i - j + 1) / 2] - nums[j];
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int maxFrequencyScoreSlidingWindowWithPrefixSum(int[] nums, long k) {
        // Same idea as #1838 but now it is also allowed to decrement
        // the values by 1. We first sort the array first and maintain a
        // sliding window of [left, right]. For each of the time we try
        // to extend the right side of the window, we will need to check
        // if k is good enough for "change" the subarray [left, right],
        // we will need to shorten the window by advancing left end if k
        // is not good enough.
        int res = 0, n = nums.length;
        Arrays.sort(nums); // Sort the array
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) { // Calculate prefix sum
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (calc(nums, prefix, i, j) > k) { // Calculate the cost of changes
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private long calc(int[] nums, long[] prefix, int right, int left) {
        // We need to change subarray [left, right] to the same value to increase
        // the frequency score. The min cost to change is to pick the median value
        // and change all other values to it. We have two cases:
        //   1. Length of the subarray (right - left + 1) is odd, we will simply
        //      change all values to nums[mid] where mid = left + (right - left) / 2.
        //   2. Length is even, we have two options - nums[mid] and nums[mid + 1],
        //      we can choose one of them since the cost is the same (by drawing a
        //      horizontal line we can see the sum of distance from other points to
        //      both are the same).
        // Now we need to calculate the cost. We know the numbers in left half of the
        // subarray are all <= nums[mid] and right half are all >= nums[mid]. So we
        // have:
        //   left cost = left subarray length * nums[mid] - sum(left half)
        //   right cost = sum(right half) - right subarray length * nums[mid]
        // sum(left half) and sum(right half) can be obtained in O(1) time from the
        // prefix sum array.
        int mid = left + (right - left) / 2, ll = mid - left + 1, rl = right - mid;
        long leftCost = (long) nums[mid] * ll - prefix[mid + 1] + prefix[left];
        long rightCost = prefix[right + 1] - prefix[mid + 1] - (long) nums[mid] * rl;
        return leftCost + rightCost;
        /* Not needed
        if (l % 2 == 0) {
            leftCost = (long) nums[mid + 1] * ll - prefix[mid + 1] + prefix[left];
            rightCost =  prefix[right + 1] - prefix[mid + 1] - (long) nums[mid + 1] * rl;
            res = Math.min(res, leftCost + rightCost);
        }
        return res;
         */
    }
}
