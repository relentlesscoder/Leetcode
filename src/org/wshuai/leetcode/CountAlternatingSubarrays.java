package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #3101 https://leetcode.com/problems/count-alternating-subarrays/
 */
public class CountAlternatingSubarrays {

    // time O(n), space O(1)
    public long countAlternatingSubarrays(int[] nums) {
        long res = 1;
        int n = nums.length, count = 1, prev = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            // for array [0 1 0 1 1], subarray [0, 1, 0, 1] is alternating
            // index 0: 1 alternating subarray which is [0]
            // index 1: 2 alternating subarrays which are [1], [0, 1]
            // index 2: 3 alternating subarrays which are [0], [1, 0], [0, 1, 0]
            // index 3: 4 alternating subarrays which are [1], [0, 1], [1, 0, 1], [0, 1, 0, 1]
            // then [1] is alternating
            // index 4: 1 alternating subarray which is [1]
            res += count; // add count of alternating subarrays end at i
        }
        return res;
    }
}
