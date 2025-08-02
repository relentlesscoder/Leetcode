package org.wshuai.leetcode;

import java.util.ArrayList;

/**
 * Created by Wei on 08/01/2025.
 * #2962 https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 */
public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    // time O(n), space O(1)
    public long countSubarraysSlidingWindow(int[] nums, int k) {
        long res = 0;
        int max = -1, n = nums.length;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int i = 0, j = 0, count = 0; j < n; j++) {
            count += nums[j] == max ? 1 : 0;
            while (count == k) {
                if (nums[i++] == max) {
                    count--;
                }
            }
            res += i;
        }
        return res;
    }

    // time O(n), space O(n)
    public long countSubarraysIndexesOfMax(int[] nums, int k) {
        long res = 0;
        int max = -1, n = nums.length;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == max) {
                indexes.add(i);
            }
            if (indexes.size() >= k) {
                res += indexes.get(indexes.size() - k) + 1;
            }
        }
        return res;
    }
}
