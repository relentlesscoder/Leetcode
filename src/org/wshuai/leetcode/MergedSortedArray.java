package org.wshuai.leetcode;

/**
 * Created by Wei on 8/26/16.
 */
public class MergedSortedArray {
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    if(nums2 == null || n == 0){
      return;
    }
    if(nums1 == null || m == 0){
      for(int i = 0; i < n; i++){
        nums1[i] = nums2[i];
      }
    }
    for(int i = m - 1; i >= 0; i--){
      nums1[i + n] = nums1[i];
    }

    int x = n;
    int y = 0;
    for(int i = 0; i < m + n; i++){
      if(x == m + n){
        nums1[i] = nums2[y];
        y++;
        continue;
      }
      if(y == n){
        nums1[i] = nums1[x];
        x++;
        continue;
      }
      if(nums1[x] < nums2[y]){
        nums1[i] = nums1[x];
        x++;
      }else{
        nums1[i] = nums2[y];
        y++;
      }
    }
  }
}
