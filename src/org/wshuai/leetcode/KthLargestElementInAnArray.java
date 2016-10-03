package org.wshuai.leetcode;

/**
 * Created by Wei on 9/12/16.
 */
public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
    int len = nums.length;
    return getKthLargestUtil(nums, k, 0, len - 1);
  }

  private int getKthLargestUtil(int[] nums, int k, int l, int r){
    if(l == r){
      return nums[l];
    }
    if(l < r){
      int p = partition(nums, l, r);
      if(p == k - 1){
        return nums[p];
      }else if(p > k - 1){
        return getKthLargestUtil(nums, k, l, p - 1);
      }else{
        return getKthLargestUtil(nums, k, p + 1, r);
      }
    }
    return -1;
  }

  private int partition(int[] nums, int l, int r){
    int pivot = nums[r];
    int i = l;
    for(int j = l; j < r; j++){
      int val = nums[j];
      if(val > pivot){
        nums[j] = nums[i];
        nums[i] = val;
        i++;
      }
    }
    nums[r] = nums[i];
    nums[i] = pivot;
    return i;
  }
}
