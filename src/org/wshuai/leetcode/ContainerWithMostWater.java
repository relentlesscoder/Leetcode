package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 */
public class ContainerWithMostWater {

  // O(n) time
  public static int maxArea(int[] height) {
    int len = height.length;
    if(len <= 0){
      throw new IllegalArgumentException("Input is empty.");
    }
    if(len == 1){
      return 0;
    }

    int max = 0;
    int lindex = 0;
    int rindex = len - 1;
    while(lindex < rindex){
      int leftLen = height[lindex];
      int rightLen = height[rindex];
      int lineLen = leftLen < rightLen ? leftLen : rightLen;
      int area = (rindex - lindex)*lineLen;
      max = area > max ? area : max;
      if(leftLen < rightLen){
        while(lindex < len && height[lindex] <= leftLen){
          lindex++;
        }
      }else{
        while(rindex >= 0 && height[rindex] <= rightLen){
          rindex--;
        }
      }
    }

    return max;
  }

  // Time complexity n^2
  public static int maxAreaBrutalForce(int[] height) {
    int len = height.length;
    if(len <= 0){
      throw new IllegalArgumentException("Input is empty.");
    }
    if(len == 1){
      return 0;
    }

    int max = 0;
    for(int i = 0; i < len; i++){
      if(i == len - 1){
        break;
      }
      for(int j= i+1; j < len; j++){
        int area = (j - i)*(height[i] < height[j] ? height[i] : height[j]);
        max = area > max ? area : max;
      }
    }
    return max;
  }
}
