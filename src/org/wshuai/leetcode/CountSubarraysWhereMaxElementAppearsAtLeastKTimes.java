package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2025.
 * #2962 https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 */
public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    // time O(n), space O(1)
    public long countSubarrays(int[] nums, int k) {
        long res = 0;
        int n = nums.length, max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // Iterate the right end of the sliding window i when the
        // count of max >= k we move the left side j until count < k.
        // Now all index k in [0, j - 1] can form a valid substring
        // with i as the right end, so we add j to the answer.
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            count += nums[i] == max ? 1 : 0;
            while (count >= k) {
                count -= nums[j++] == max ? 1 : 0;
            }
            res += j;
        }
        return res;
    }
}
