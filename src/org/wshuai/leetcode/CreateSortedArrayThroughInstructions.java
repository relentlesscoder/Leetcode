package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/21/2024.
 * #1649 https://leetcode.com/problems/create-sorted-array-through-instructions/
 */
public class CreateSortedArrayThroughInstructions {

    // time O(m * log(n)), space O(n)
    private static final int MOD = (int)1e9 + 7;

    // time O(n * log(m)), space O(m)
    public int createSortedArray(int[] instructions) {
        long res = 0;
        int[] sorted = Arrays.stream(instructions).distinct().sorted().toArray(); // O(m * log(m))
        int n = instructions.length, m = sorted.length;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) { // O(n)
            int i1 = binarySearch(sorted, instructions[i] + 1),
                    i2 = binarySearch(sorted, instructions[i]); // O(log(m))
            res += Math.min(bit.query(i2), i - bit.query(i1)); // O(log(m))
            bit.update(i2 + 1, 1); // O(log(m))
        }
        return (int) (res % MOD);
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
