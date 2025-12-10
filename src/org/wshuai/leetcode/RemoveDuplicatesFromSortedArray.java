package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0026 https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {

    // time O(n), space O(1)
    public int removeDuplicates(int[] nums) {
        int n = nums.length, j = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
