package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/03/2025.
 * #2426 https://leetcode.com/problems/number-of-pairs-satisfying-inequality/
 */
public class NumberOfPairsSatisfyingInequality {

    // time O(n * log(n)), space O(n)
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        // (n1[i] - n2[i]) - (n1[j] - n2[j]) <= diff
        // n[i] - n[j] <= diff
        // n[i] <= n[j] + diff
        long res = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        int[] sorted = Arrays.stream(nums1).distinct().sorted().toArray();
        // +1 since binary index tree starts with index 1
        BIT bit = new BIT(sorted.length + 1);
        for (int num : nums1) {
            // Search in BIT that has "rank" that <= num + diff
            res += bit.query(binarySearch(sorted, num + diff + 1));
            // +1 since binary index tree starts with index 1
            bit.add(binarySearch(sorted, num) + 1);
        }
        return res;
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

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n];
        }

        public void add(int index) {
            while (index < tree.length) {
                tree[index]++;
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
