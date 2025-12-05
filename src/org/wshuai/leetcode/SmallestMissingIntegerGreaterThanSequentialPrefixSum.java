package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #2996 https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/
 */
public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {

    // time O(n), space O(1)
    public int missingInteger(int[] nums) {
        int res = 0, prefixSum = nums[0], n = nums.length, index = 1;
        int[] map = new int[51];
        map[nums[0]]++;
        for (; index < n; index++) {
            if (nums[index] != nums[index - 1] + 1) {
                break;
            }
            prefixSum += nums[index];
            map[nums[index]]++;
        }
        for (; index < n; index++) {
            map[nums[index]]++;
        }
        for (res = prefixSum; res < 51 && map[res] > 0; res++) {}
        return res;
    }
}
