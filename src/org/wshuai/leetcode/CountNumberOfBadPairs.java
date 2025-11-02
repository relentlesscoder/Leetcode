package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/03/2025.
 * #2364 https://leetcode.com/problems/count-number-of-bad-pairs/
 */
public class CountNumberOfBadPairs {

    // time O(n), space O(n)
    public long countBadPairsConcise(int[] nums) {
        // j - i != nums[j] - nums[i]
        // nums[j] - j != nums[i] - i
        // Convert original array to new array new_nums[i] = nums[i] - i
        long res = 0;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums[i] -= i;
            // Bad pairs = total number of pairs - good pairs
            res += i - map.getOrDefault(nums[i], 0);
            map.merge(nums[i], 1, Integer::sum);
        }
        return res;
    }

    // time O(n), space O(n)
    public long countBadPairsTwoPasses(int[] nums) {
        long res = 0;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        // j - i != nums[j] - nums[i]
        // nums[j] - j != nums[i] - i
        // Convert original array to new array new_nums[i] = nums[i] - i
        for (int i = 0; i < n; i++) {
            nums[i] -= i;
        }
        for (int i = 0; i < n; i++) {
            res += i; // Calculate total number of pairs
            res -= map.getOrDefault(nums[i], 0); // Reduce good pairs
            map.merge(nums[i], 1, Integer::sum);
        }
        return res;
    }
}
