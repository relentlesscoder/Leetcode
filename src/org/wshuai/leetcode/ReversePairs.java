package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/23/2017.
 * #0493 https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {

    // time O(n * log(n)), space O(n)
    public int reversePairsMergeSort(int[] nums) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1);
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int left = mergeSort(nums, low, mid);
        int right = mergeSort(nums, mid + 1, high);
        int middle = merge(nums, low, high);
        return left + right + middle;
    }

    private int merge(int[] nums, int low, int high) {
        int res = 0;
        int mid = low + (high - low) / 2;
        int leftIndex = low;
        int rightIndex = mid + 1;
        int nextIndex = mid + 1;
        int idx = 0;
        int[] temp = new int[high - low + 1];
        // Since both left and right part are sorted, the reverse pair
        // can be carried over when we iterate the left part.
        // For example, we have left: [10, 12, 15], right[3, 4, 5]
        // for 10, it can combine with 3 and 4 to form a reverse pair
        // for 12, since it is greater than or equal to 10, it can also
        // combine with 3 and 4 to form a reverse pair. and plus the 5
        // there will be total 3 reverse pairs. Then for the same reason,
        // 15 will also have 3 reverse pairs.
        while (leftIndex <= mid) {
            while (nextIndex <= high && nums[leftIndex] > 2L * nums[nextIndex]) {
                nextIndex++;
            }
            res += nextIndex - mid - 1;
            // sorting
            while (rightIndex <= high && nums[rightIndex] < nums[leftIndex]) {
                temp[idx++] = nums[rightIndex++];
            }
            temp[idx++] = nums[leftIndex++];
        }
        while (rightIndex <= high) {
            // if right part still has elements left
            temp[idx++] = nums[rightIndex++];
        }
        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int reversePairs(int[] nums) {
        int res = 0, n = nums.length;
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
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
            tree = new int[n + 1];
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