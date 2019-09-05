package org.wshuai.leetcode;

import org.wshuai.algorithm.sorting.QuickSort;

/**
 * Created by Wei on 9/5/19.
 * #912 https://leetcode.com/problems/sort-an-array/
 */
public class SortAnArray {
    public int[] sortArray(int[] nums) {
        QuickSort.quickSort(nums, 0, nums.length-1);
        return nums;
    }
}
