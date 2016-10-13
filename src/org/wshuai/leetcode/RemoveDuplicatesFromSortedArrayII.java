package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/16.
 * #80 https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayII {
  public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }

    int i = 0;
    int j = 0;
    int count = 0;
    int val = nums[0];
    int len = nums.length;
    for(;j < len; j++){
      int curr = nums[j];
      if(curr == val){
        if(count < 2){
          nums[i] = curr;
          i++;
        }
        count++;
      }else{
        val = curr;
        nums[i] = curr;
        count = 1;
        i++;
      }
    }

    return i;
  }
}
