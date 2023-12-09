package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2023.
 * #2936 https://leetcode.com/problems/number-of-equal-numbers-blocks/
 */
public class NumberOfEqualNumbersBlocks {

    // time O(log(n)), space O(log(n))
    public int countBlocksDivideAndConquer(BigArray nums) {
        return count(nums, 0L, nums.size() - 1);
    }

    private int count(BigArray nums, long left, long right) {
        if (nums.at(left) == nums.at(right)) {
            return 1;
        }
        if (left + 1 == right) {
            return 2;
        }
        long mid = left + (right - left) / 2;
        return count(nums, left, mid) + count(nums, mid, right) - 1;
    }

    /**
     * Definition for BigArray.
     */
    interface BigArray {
        // public BigArray(int[] elements);
        public int at(long index);

        public long size();
    }
}
