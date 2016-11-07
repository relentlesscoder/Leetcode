package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/2016.
 * #189 https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {
  public void rotate(int[] nums, int k) {
    if(nums == null){
      return;
    }

    int n = nums.length;
    if(n <= 1){
      return;
    }

    k = k%n;

    int pv = 0;
    int rv = 0;
    int next = 0;
    for(int i=0; i < gcd(n, k); i++){
      next = i+k;
      pv = nums[i];
      while(true){
        if(next >= n){
          next %= n;
        }

        rv = nums[next];
        nums[next] = pv;
        pv = rv;

        if(next == i){
          break;
        }

        next+=k;
      }
    }
  }

  private int gcd(int l, int n){
    if(l == n){
      return l;
    }
    int d = l < n ? l : n;
    while(d > 1){
      if((l%d == 0) && (n%d == 0)){
        return d;
      }
      d--;
    }
    return 1;
  }

  public void rotateReverse(int[] nums, int k) {
    if(nums == null || nums.length == 0){
      return;
    }
    int len = nums.length;
    k = k%len;
    reverse(nums, 0, len-1);
    reverse(nums, 0, k-1);
    reverse(nums, k, len-1);
  }

  private void reverse(int[] nums, int s, int e){
    int left = s;
    int right = e;
    while(left < right){
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;

      left++;
      right--;
    }
  }
}
