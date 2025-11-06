package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/06/2025.
 * #2659 https://leetcode.com/problems/make-array-empty/
 */
public class MakeArrayEmpty {

    // time O(n * log(n)), space O(n)
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length, pre = 1; // pre is the last index
        long res = n; // Initialize to n since all elements need to be deleted
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        // Sort indexes by value to decide order of deleting
        Arrays.sort(ids, (i, j) -> nums[i] - nums[j]);
        BIT bit = new BIT(n); // Use BIT to store number of deleted elements
        for (int k = 0; k < n; k++) {
            int index = ids[k] + 1; // Next index to delete
            if (index >= pre) { // pre is on left side of index
                // Total moves need is index - pre
                // Number of elements that are already deleted is bit.query(pre, index)
                res += index - pre - bit.query(pre, index);
            } else { // pre is on right side of index
                // 1. Move from pre to last elements (index n): n - pre - bit.query(pre, n)
                // 2. Move from first element (index 1) to index: index - bit.query(1, index),
                //    note that here index does not need to reduce 1 since we directly move
                //    from tail (n) to head (1) element of the array.
                res += n - pre - bit.query(pre, n) + index - bit.query(1, index);
            }
            bit.update(index);
            pre = index;
        }
        return res;
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public int query(int left, int right) {
            return sum(right) - sum(left - 1);
        }
    }
}
