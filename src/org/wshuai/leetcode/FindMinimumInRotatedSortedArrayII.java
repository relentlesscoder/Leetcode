package org.wshuai.leetcode;

/**
 * Created by Wei on 6/7/2017.
 * #154 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMinimumInRotatedSortedArrayII {

  //worst case O(n)
  public int findMin(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int l = 0;
    int r = nums.length-1;
    int min = Integer.MAX_VALUE;
    while(l < r-1){
      int m = l+(r-l)/2;
      if(nums[m] > nums[l]){
        min = Math.min(nums[l], min);
        l = m;
      }else if(nums[m] < nums[l]){
        min = Math.min(nums[m], min);
        r = m;
      }else{
        l++;
      }
    }
    min = Math.min(min, nums[l]);
    min = Math.min(min, nums[r]);
    return min;
  }

  public int findMinRecursive(int[] nums) {
    int len = nums.length;
    if(len == 1){
      return nums[0];
    }
    return findMinUtil(nums, 0, len-1);
  }

  private int findMinUtil(int[] nums, int i, int j){
    int left = nums[i];
    int right = nums[j];

    if(i == j){
      return left;
    }
    if(i == j-1){
      return Math.min(left, right);
    }

    if(left < right){
      return left;
    }

    int k = i+(j-i)/2;
    int mid = nums[k];
    if(mid > left){
      return findMinUtil(nums, k, j);
    }else if(mid < left){
      return findMinUtil(nums, i, k);
    }else{
      i++;
      return findMinUtil(nums, i, j);
    }
  }
}
