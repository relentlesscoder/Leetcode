package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2025.
 * #3105 https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
 */
public class FindIfArrayCanBeSorted {

    // time O(n), space O(1)
    public boolean canSortArray(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] setBitCounts = new int[n];
        for (int i = 0; i < n; i++) {
            setBitCounts[i] = countSetBits(nums[i]);
        }
        for (int i = 0, j = 0; i < n; ) {
            int localMax = nums[i], localMin = nums[i];
            j = i + 1;
            while (j < n && setBitCounts[j] == setBitCounts[i]) {
                localMax = Math.max(localMax, nums[j]);
                localMin = Math.min(localMin, nums[j++]);
            }
            if (localMin < max) { // local min value can't be less than previous max
                return false;
            }
            max = localMax; // set previous max to local max value
            i = j;
        }
        return true;
    }

    private int countSetBits(int num) {
        int mask1 = 0b01010101010101010101010101010101,
                mask2 = 0b00110011001100110011001100110011,
                mask3 = 0b00001111000011110000111100001111,
                mask4 = 0b00000000111111110000000011111111,
                mask5 = 0b00000000000000001111111111111111;
        int res = ((num >> 1) & mask1) + (num & mask1);
        res = ((res >> 2) & mask2) + (res & mask2);
        res = ((res >> 4) & mask3) + (res & mask3);
        res = ((res >> 8) & mask4) + (res & mask4);
        res = ((res >> 16) & mask5) + (res & mask5);
        return res;
    }
}
