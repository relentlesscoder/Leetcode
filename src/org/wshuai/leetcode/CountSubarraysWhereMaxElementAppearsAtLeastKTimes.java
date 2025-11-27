package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2025.
 * #2962 https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 */
public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    // time O(n), space O(1)
    public long countSubarrays(int[] nums, int k) {
        long res = 0;
        int max = -1, n = nums.length;
        // Find the max
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // Use sliding window, we iterate the right side of the
        // window j when the count of max == k we advance the
        // left side of window i to make count < k. Now we know
        // all index k in [0,i-1] can be used as the start with j
        // as the end to form a valid subarray.
        for (int i = 0, j = 0, count = 0; j < n; j++) {
            count += nums[j] == max ? 1 : 0;
            while (count == k) {
                count += nums[i++] == max ? -1 : 0;
            }
            res += i;
        }
        return res;
    }
}
