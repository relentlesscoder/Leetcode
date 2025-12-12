package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/22/2020.
 * #1385 https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 */
public class FindTheDistanceValueBetweenTwoArrays {

    // time O((n + m) * log(m)), space O(log(m))
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		// 根据公式 |arr1[i]-arr2[j]| <= d 得出两种情况
		//   1. arr1[i] >= arr2[j]: |arr1[i] - arr2[j]| <= d -> arr1[i] - arr2[j] <= d
		//   		-> arr2[j] >= arr1[i] - d
		//   2. arr1[i] < arr2[j]: |arr1[i] - arr2[j]| <= d -> arr2[j] - arr1[i] <= d
		//   		-> arr2[j] <= arr1[i] + d
		// 所以题目是要对于每一个arr1[i]，判断arr2是否不含有任何在闭区间[arr1[i] - d, arr1[i] + d]
		// 内的元素。对于每一个arr1[i]，可以使用二分搜索来判断在arr2中大于等于arr1[i] - d的数的数量是
		// 否和大于等于arr1[i] + d + 1
		// 的数的数量相同。
        int res = 0;
        Arrays.sort(arr2); // O(m * log(m))
        for (int num : arr1) { // O(n)
            if (binarySearch(arr2, num - d)
                    == binarySearch(arr2, num + d + 1)) { // O(log(m))
                res++;
            }
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
}
