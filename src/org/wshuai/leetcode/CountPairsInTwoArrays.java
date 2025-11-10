package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/18/2023.
 * #1885 https://leetcode.com/problems/count-pairs-in-two-arrays/
 */
public class CountPairsInTwoArrays {

    // time O(n * log(n)), space O(1)
    public long countPairs(int[] nums1, int[] nums2) {
        // Since nums1[i] + nums1[j] > nums2[i] + nums2[j] equals (nums1[i] - nums2[i])
        // + (nums1[j] - nums2[j]) > 0 thus we can build a new array num[i] = nums1[i] - nums2[i].
        // Then we sort the new array and use two pointers to calculate all the pairs
        // with positive sum. Note that for diff array the restriction i < j in the original
        // problem is not important.
        long res = 0;
        int n = nums1.length, left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        Arrays.sort(nums1);
        while (left < right) {
            if (nums1[left] + nums1[right] <= 0) {
                left++;
            } else {
                res += right - left;
                right--;
            }
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public long countPairsBinaryIndexedTree(int[] nums1, int[] nums2) {
        long res = 0;
        int n = nums1.length;
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
            nums.add(nums1[i]);
            nums.add(-nums1[i]);
        }
        int[] sorted = nums.stream().mapToInt(Integer::intValue).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) {
            int index = binarySearch(sorted, nums1[i]) + 1;
            res += bit.query(index - 1);
            bit.update(binarySearch(sorted, -nums1[i]) + 1);
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

    private class BIT {

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

    // time O(n * log(n)), space O(n)
    public long countPairsMergeSort(int[] nums1, int[] nums2) {
        long res = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        return mergeSort(nums1, 0, n - 1);
    }

    private long mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        long l = mergeSort(nums, left, mid);
        long r = mergeSort(nums, mid + 1, right);
        long m = merge(nums, left, right);
        return l + r + m;
    }

    private long merge(int[] nums, int left, int right) {
        long res = 0;
        int mid = left + (right - left) / 2;
        int leftIndex = left;
        int rightIndex = mid + 1;
        int countIndex = right;
        int index = 0;
        int[] temp = new int[right - left + 1];
        while (leftIndex <= mid) {
            // Counting
            while (countIndex > mid && nums[leftIndex] > -nums[countIndex]) {
                countIndex--;
            }
            res += right - countIndex;
            // Sorting
            while (rightIndex <= right && nums[rightIndex] <= nums[leftIndex]) {
                temp[index++] = nums[rightIndex++];
            }
            temp[index++] = nums[leftIndex++];
        }
        while (rightIndex <= right) {
            temp[index++] = nums[rightIndex++];
        }
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i - left];
        }
        return res;
    }
}
