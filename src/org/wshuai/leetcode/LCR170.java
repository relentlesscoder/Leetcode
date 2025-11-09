package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/09/2025.
 * #LCR170 https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class LCR170 {

    // time O(n * log(n)), space O(n)
    public int reversePairsMergeSort(int[] record) {
        int n = record.length;
        return mergeSort(record, 0, n - 1);
    }

    private int mergeSort(int[] record, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int left = mergeSort(record, low, mid);
        int right = mergeSort(record, mid + 1, high);
        int middle = merge(record, low, high);
        return left + right + middle;
    }

    private int merge(int[] record, int low, int high) {
        int res = 0;
        int mid = low + (high - low) / 2;
        int leftIndex = low;
        int rightIndex = mid + 1;
        int nextIndex = mid + 1;
        int idx = 0;
        int[] temp = new int[high - low + 1];
        while (leftIndex <= mid) {
            while (nextIndex <= high && record[leftIndex] > record[nextIndex]) {
                nextIndex++;
            }
            res += nextIndex - mid - 1;
            // sorting
            while (rightIndex <= high && record[rightIndex] < record[leftIndex]) {
                temp[idx++] = record[rightIndex++];
            }
            temp[idx++] = record[leftIndex++];
        }
        while (rightIndex <= high) {
            // if right part still has elements left
            temp[idx++] = record[rightIndex++];
        }
        for (int i = low; i <= high; i++) {
            record[i] = temp[i - low];
        }
        return res;
    }

    // time O(n * log(m)), space O(m)
    public int reversePairsBinaryIndexedTree(int[] record) {
        int res = 0, n = record.length;
        int[] sorted = Arrays.stream(record).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) {
            int index = binarySearch(sorted, record[i]) + 1;
            res += i - bit.query(index);
            bit.update(index);
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
            tree = new int[n + 1];
        }

        public void update(int index) {
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
