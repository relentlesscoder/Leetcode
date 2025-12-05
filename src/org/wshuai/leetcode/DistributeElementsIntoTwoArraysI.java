package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2025.
 * #3069 https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
 */
public class DistributeElementsIntoTwoArraysI {

    // time O(n), space O(1)
    public int[] resultArray(int[] nums) {
        int n = nums.length, tail = 0;
        List<Integer> arr2 = new ArrayList<>();
        arr2.add(nums[1]);
        // i - current index
        // tail - current tail of arr1
        for (int i = 2; i < n; i++) {
            if (nums[tail] > arr2.get(arr2.size() - 1)) {
                nums[++tail] = nums[i];
            } else {
                arr2.add(nums[i]);
            }
        }
        for (int num : arr2) {
            nums[++tail] = num;
        }
        return nums;
    }
}
