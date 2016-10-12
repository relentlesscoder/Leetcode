package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 */
public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return -1;
    }
    return searchIn(nums, target, 0, nums.length - 1);
  }

  private int searchIn(int[] nums, int t, int i, int j){
    int x = nums[i];
    int y = nums[j];

    if(i == j){
      return x == t ? i : -1;
    }
    if(i == j - 1){
      if(x == t){
        return i;
      }else if(y == t){
        return j;
      }else{
        return -1;
      }
    }
    int m = (i+j)/2;
    int z = nums[m];

    if(z > x){
      if(z >= t && t >= x){
        return searchIn(nums, t, i, m);
      }else{
        return searchIn(nums, t, m, j);
      }
    }else{
      if(z <= t && t <= y){
        return searchIn(nums, t, m, j);
      }else{
        return searchIn(nums, t, i, m);
      }
    }
  }
}
