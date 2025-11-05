package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2025.
 * #3187 https://leetcode.com/problems/peaks-in-array/
 */
public class PeaksInArray {

    // time O(n * log(n) + m * log(n)), space O(n)
    public List<Integer> countOfPeaksConcise(int[] nums, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        BIT bit = new BIT(n);
        for (int i = 1; i <= n - 2; i++) {
            checkAndUpdate(bit, nums, i, 1);
        }
        for (int[] query : queries) {
            if (query[0] == 1) {
                int left = query[1] + 1, right = query[2] - 1;
                res.add(left > right ? 0 : bit.query(right + 1) - bit.query(left));
                continue;
            }
            int index = query[1], val = query[2];
            for (int i = Math.max(1, index - 1); i <= Math.min(n - 2, index + 1); i++) {
                checkAndUpdate(bit, nums, i, -1);
            }
            nums[index] = val;
            for (int i = Math.max(1, index - 1); i <= Math.min(n - 2, index + 1); i++) {
                checkAndUpdate(bit, nums, i, 1);
            }
        }
        return res;
    }

    private void checkAndUpdate(BIT bit, int[] nums, int index, int val) {
        if (nums[index] > nums[index - 1] && nums[index] > nums[index + 1]) {
            bit.update(index + 1, val);
        }
    }

    // time O(n + m * log(n)), space O(n)
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 1; i < n - 1; i++) { // O(n)
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                arr[i] = 1;
            }
        }
        BIT bit = new BIT(arr); // O(n)
        for (int[] query : queries) { // O(m)
            if (query[0] == 1) {
                int left = query[1] + 1, right = query[2] - 1; // Exclude the boundary elements
                if (left > right) {
                    res.add(0);
                } else {
                    res.add(bit.query(right + 1) - bit.query(left)); // O(log(n))
                }
            } else {
                int index = query[1], val = query[2];
                // Check peak state change and update (if needed) for nums[index]
                checkAndUpdate(index, val, nums, arr, bit); // O(log(n))
                // Check peak state change and update (if needed) for nums[index + 1]
                if (index + 1 < n) {
                    checkAndUpdate(index + 1, nums[index + 1], nums, arr, bit); // O(log(n))
                }
                // Check peak state change and update (if needed) for nums[index - 1]
                if (index - 1 >= 0) {
                    checkAndUpdate(index - 1, nums[index - 1], nums, arr, bit); // O(log(n))
                }
            }
        }
        return res;
    }

    private void checkAndUpdate(int index, int val, int[] nums, int[] arr, BIT bit) {
        if (nums[index] != val) {
            nums[index] = val;
        }
        if (index == 0 || index == nums.length - 1) {
            return;
        }
        if (nums[index] > nums[index - 1] && nums[index] > nums[index + 1] && arr[index] == 0) {
            arr[index] = 1;
            bit.update(index + 1, 1);
        } else if ((nums[index] <= nums[index - 1] || nums[index] <= nums[index + 1]) && arr[index] == 1) {
            arr[index] = 0;
            bit.update(index + 1, -1);
        }
    }

    private static class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public BIT(int[] nums) {
            int n = nums.length;
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) { // Linear initialization
                tree[i] += nums[i - 1];
                int index = i + (i & -i);
                if (index <= n) {
                    tree[index] += tree[i];
                }
            }
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
