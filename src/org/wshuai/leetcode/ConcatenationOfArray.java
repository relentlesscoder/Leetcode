package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #1929 https://leetcode.com/problems/concatenation-of-array/description/
 */
public class ConcatenationOfArray {

    // time O(n), space O(n)
    public int[] getConcatenation(int[] nums) {
        int len = nums.length << 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[i%nums.length];
        }
        return res;
    }
}
