package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #81 https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class SearchInRotatedSortedArrayII {
  //O(lg(n)), worst case O(n)
  public boolean search(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return false;
    }

    int i = 0;
    int j = nums.length-1;
    while(i <= j){
      int left = nums[i];
      int right = nums[j];
      int m = i + (j-i)/2;
      int middle = nums[m];
      if(middle == target){
        return true;
      }
      if(left < middle){
        if(middle > target && target >= left){
          j = m-1;
        }else{
          i = m+1;
        }
      }else if(left > middle){
        if(middle < target && target <= right){
          i = m+1;
        }else{
          j = m-1;
        }
      }else{
        i++;
      }
    }
    return false;
  }
}
