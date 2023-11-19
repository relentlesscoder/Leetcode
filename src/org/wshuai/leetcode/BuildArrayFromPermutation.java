package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #1920 https://leetcode.com/problems/build-array-from-permutation/description/
 */
public class BuildArrayFromPermutation {

    // time O(n), space O(1)
    public int[] buildArray(int[] nums) {
        // encode number a to a = q*b + r
        // explanation - https://leetcode.com/problems/build-array-from-permutation/solutions/1315926/python-o-n-time-o-1-space-w-full-explanation/
        for (int i = 0; i < nums.length; i++) {
            nums[i] += nums.length * (nums[nums[i]] % nums.length);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] /= nums.length;
        }
        return nums;
    }

    // time O(n), space O(1)
    public int[] buildArrayBitManipulation(int[] nums) {
        int mask = 0b1111111111;
        // nums[i] <= 1000 (binary 1111101000), we can use
        // last 20 bits to store nums[nums[i]] and nums[i].
        // this solution does not fit when nums[i] has larger
        // values.
        for (int i = 0; i < nums.length; i++) {
            nums[i] |= (nums[nums[i]] & mask) << 10;
        }
        // retrieve nums[nums[i]]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] >> 10;
        }
        return nums;
    }

    // time O(n), space O(n)
    public int[] buildArrayNaive(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[nums[i]];
        }
        return res;
    }
}
