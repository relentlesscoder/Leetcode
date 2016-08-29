package org.wshuai.leetcode;

/**
 * Created by Wei on 8/18/2016.
 */
public class MajorityElement {



  public static int majorityElementQuickSort(int[] nums) {
    int len = nums.length;
    quickSort(nums, 0, len-1);
    return nums[len/2];
  }

  public static void quickSort(int[] nums, int p, int q){
    if(p < q){
      int r = partition(nums, p, q);
      quickSort(nums, p, r-1);
      quickSort(nums, r+1, q);
    }
  }

  public static int partition(int[] nums, int p, int q){
    int pivot = nums[q];
    int i = p;
    for(int j = p; j < q; j++){
      if(nums[j] < pivot){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
      }
    }
    nums[q] = nums[i];
    nums[i] = pivot;
    return i;
  }
}
