package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #33 https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target){
    if(nums == null || nums.length == 0){
      return -1;
    }

    return searchUtil(nums, target, 0, nums.length-1);
  }

  private int searchUtil(int[] nums, int target, int i, int j){
    int left = nums[i];
    int right = nums[j];

    if(i == j){
      return left == target ? i : -1;
    }
    if(i == j-1){
      if(left == target){
        return i;
      }else if(right == target){
        return j;
      }else{
        return -1;
      }
    }
    int m = i + (j-i)/2;
    int middle = nums[m];
    if(middle > left){
      if(middle >= target && target >= left){
        return searchUtil(nums, target, i, m);
      }else{
        return searchUtil(nums, target, m, j);
      }
    }else{
      if(middle <= target && target <= right){
        return searchUtil(nums, target, m, j);
      }else{
        return searchUtil(nums, target, i, m);
      }
    }
  }
}
