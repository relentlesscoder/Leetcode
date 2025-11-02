package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/23/2017.
 * #0493 https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {

    // time O(n * log(n)), space O(n * log(n))
    public int reversePairsMergeSort(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int m = l + (r - l) / 2; // The middle index
        int i = l; // Pointer for left subarray
        int j = m + 1; // Pointer for right subarray
        int k = 0; // Pointer for array merge
        int p = m + 1; // Pointer to extend positions satisfying nums[i] > 2 * nums[p]
        int[] merge = new int[r - l + 1];
        int res = mergeSort(nums, l, m) + mergeSort(nums, m + 1, r);
        // Since both left and right part are sorted, the reverse pair
        // can be carried over when we iterate the left part.
        // For example, we have left: [10, 12, 15], right[3, 4, 5]
        // for 10, it can combine with 3 and 4 to form a reverse pair
        // for 12, since it is greater than or equal to 10, it can also
        // combine with 3 and 4 to form a reverse pair. and plus the 5
        // there will be total 3 reverse pairs. Then for the same reason,
        // 15 will also have 3 reverse pairs.
        while (i <= m) {
            while (p <= r && nums[i] > 2L * nums[p]) {
                p++;
            }
            res += p - m - 1;
            // sorting
            while (j <= r && nums[j] < nums[i]) {
                merge[k++] = nums[j++];
            }
            merge[k++] = nums[i++];
        }
        while (j <= r) {
            // if right part still has elements left
            merge[k++] = nums[j++];
        }
        System.arraycopy(merge, 0, nums, l, r - l + 1);
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int reversePairs(int[] nums) {
        int res = 0, n = nums.length;
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        // +1 since binary index tree starts with index 1
        BIT bit = new BIT(n + 1);
        for (int i = 0; i < n; i++) {
            // Search in BIT that has "rank" that <= 2L * nums[i]
            res += i - bit.query(binarySearch(sorted, 2L * nums[i] + 1));
            // +1 since binary index tree starts with index 1
            bit.add(binarySearch(sorted, nums[i]) + 1);
        }
        return res;
    }

    private int binarySearch(int[] nums, long t) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < t) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private class BIT {

        private int[] tree;

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