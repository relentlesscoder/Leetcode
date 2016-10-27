package org.wshuai.leetcode;

import org.wshuai.algorithm.sorting.QuickSort;

/**
 * Created by Wei on 8/18/2016.
 * #169 https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {

  public static int majorityElementQuickSort(int[] nums) {
    int len = nums.length;
    QuickSort.quickSort(nums, 0, len-1);
    return nums[len/2];
  }
}
