package org.wshuai.leetcode;

/**
 * Created by Wei on 05/22/2021.
 * #1829 https://leetcode.com/problems/maximum-xor-for-each-query/
 */
public class MaximumXORForEachQuery {

    // time O(n)
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int xor = 0, n = nums.length, mask = (1 << maximumBit) - 1;
        int[] res = new int[n];
        for(int num : nums){
            xor ^= num;
        }
        for(int i = n - 1; i >= 0; i--){
            res[n - 1 - i] = (~(xor & mask)) & mask;
            xor ^= nums[i];
        }
        return res;
    }
}
