package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #26 https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    if(nums.length == 1){
      return 1;
    }
    int i = -1;
    int current = nums[0] - 1;
    for(int j = 0; j < nums.length; j++){
      if(nums[j] > current){
        i++;
        nums[i] = nums[j];
        current = nums[j];
      }
    }
    return i+1;
  }
}
