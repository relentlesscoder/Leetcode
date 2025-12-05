package org.wshuai.leetcode;

/**
 * Created by Wei on 01/03/2024.
 * #2527 https://leetcode.com/problems/find-xor-beauty-of-array/
 */
public class FindXORBeautyOfArray {

    // time O(n), space O(1)
    public int xorBeauty(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
