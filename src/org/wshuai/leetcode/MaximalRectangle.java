package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 10/5/16.
 */
public class MaximalRectangle {
  public int maximalRectangle(char[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
      return 0;
    }

    int rLen = matrix.length;
    int cLen = matrix[0].length;
    int[][] aux = new int[rLen][cLen];
    for(int i = 0; i < rLen; i++){
      for(int j = 0; j < cLen; j++){
        if(matrix[i][j] == '0'){
          aux[i][j] = 0;
        }else{
          aux[i][j] = i == 0 ? 1 : aux[i-1][j] + 1;
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for(int i = 0; i < rLen; i++){
      max = Math.max(max, largestRectangleArea(aux[i]));
    }

    return max;
  }

  private int largestRectangleArea(int[] heights){
    if(heights == null || heights.length == 0){
      return 0;
    }
    int len = heights.length + 1;
    int[] hs = new int[len];
    hs = Arrays.copyOf(heights, len);
    hs[len - 1] = -1;
    int max = 0;
    int i = 0;
    Stack<Integer> stack = new Stack<Integer>();
    while(i < len){
      if(stack.isEmpty() || hs[stack.peek()] <= hs[i]){
        stack.push(i);
        i++;
      }else{
        int idx = stack.pop();
        int h = hs[idx];
        int w = stack.isEmpty() ? i : i - stack.peek() - 1;
        int area = h*w;
        max = area > max ? area : max;
      }
    }
    return max;
  }
}
