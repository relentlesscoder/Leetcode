package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 */
public class TrappingRainWater {

  //Two pointers
  public int trapOneScan(int[] height) {
    if(height == null || height.length <= 2){
      return 0;
    }

    int len = height.length;
    int sum = 0;
    int left = 0;
    int right = len-1;
    while(left < right){
      int lVal = height[left];
      int rVal = height[right];
      int min = lVal < rVal ? lVal : rVal;
      if(lVal == min){
        left++;
        while(left < right && height[left] < min){
          sum += (min - height[left]);
          left++;
        }
      }else{
        right--;
        while(left < right && height[right] < min){
          sum += (min - height[right]);
          right--;
        }
      }
    }

    return sum;
  }

  public int trapTwoScan(int[] height) {
    if(height == null || height.length <= 2){
      return 0;
    }

    int len = height.length;
    int[] lMax = new int[len];
    int[] rMax = new int[len];
    int max = 0;
    for(int i = 0; i < len; i++){
      lMax[i] = max;
      if(max < height[i]){
        max = height[i];
      }
    }
    max = 0;
    int sum = 0;
    for(int i = len-1; i >= 0; i--){
      rMax[i] = max;
      if(max < height[i]){
        max = height[i];
      }
      int trap = Math.min(lMax[i], rMax[i]) - height[i];
      sum = trap > 0 ? sum + trap : sum;
    }

    return sum;
  }
}
