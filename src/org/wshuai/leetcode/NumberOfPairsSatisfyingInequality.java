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
        int m = sorted.length;
        BIT bit = new BIT(m);
        for (int num : nums1) {
            // If num + diff is not added to the sorted array (in the discretization process)
            // then search for num + diff + 1, otherwise we can search for num + diff
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

    // time O(n * log(n)), space O(n)
    public long numberOfPairsMergeSort(int[] nums1, int[] nums2, int diff) {
        // (n1[i] - n2[i]) - (n1[j] - n2[j]) <= diff
        // n[i] - n[j] <= diff
        // n[i] <= n[j] + diff
        long res = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        return mergeSort(nums1, 0, n - 1, diff);
    }

    private long mergeSort(int[] nums, int left, int right, int diff) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        long leftCount = mergeSort(nums, left, mid, diff);
        long rightCount = mergeSort(nums, mid + 1, right, diff);
        long middleCount = merge(nums, left, right, diff);
        return leftCount + rightCount + middleCount;
    }

    private long merge(int[] nums, int left, int right, int diff) {
        long res = 0;
        int mid = left + (right - left) / 2;
        int leftIndex = left;
        int rightIndex = mid + 1;
        int countIndex = mid + 1;
        int index = 0;
        int[] temp = new int[right - left + 1];
        while (leftIndex <= mid) {
            // Counting
            // Since both left and right side are sorted, if nums[l_i] > nums[r_i] + diff
            // then nums[l_j] also > nums[r_i] + diff for all l_j > l_i so the previous count
            // for l_i also holds for the current and future left indexes l_j.
            while (countIndex <= right && nums[leftIndex] > nums[countIndex] + diff) {
                countIndex++;
            }
            res += (right - countIndex + 1);
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
