package org.wshuai.leetcode;

/**
 * Created by Wei on 8/22/19.
 * #1150 https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
 */
public class CheckIfANumberIsMajorityElementInASortedArray {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        // find the index of the first occurrence
        int first = search(nums, target);
        int last = search(nums, target + 1);
        return (last - first > len/2);
    }

    private int search(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        while(lo < hi){
            int mid = (hi + lo)/2;
            if(nums[mid] < target){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        // if cannot find any, return the length of the array
        return nums[lo] == target ? lo : nums.length;
    }
}
