package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/11/2023.
 * #2009 https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/
 */
public class MinimumNumberOfOperationsToMakeArrayContinuous {

    // time O(n * log(n)), space O(n)
    public int minOperations(int[] nums) {
        int n = nums.length, res = n, index = 0;
        // the target array is contiguous like [4, 5, 6, 7, 8]
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            unique.add(num);
        }
        int[] arr = new int[unique.size()];
        for (int num : unique) {
            arr[index++] = num;
        }
        Arrays.sort(arr);
        // we test each num as the left and check numbers of elements (count)
        // are already in the array, then minimum operation to covert the
        // array is n - count
        for (int i = 0, j = 0; i < arr.length; i++) {
            while (j < arr.length && arr[j] < arr[i] + n) {
                j++;
            }
            // int right = arr[i] + n - 1, j = binarySearch(arr, right);
            res = Math.min(res, n - (j - i));
        }
        return res;
    }

    private int binarySearch(int[] arr, int num) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
