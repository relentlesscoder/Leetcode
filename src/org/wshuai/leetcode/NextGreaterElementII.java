package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 2/26/17.
 * #503 https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementII {
  //O(n), extend the array to 2*len array
  public int[] nextGreaterElements(int[] nums) {
    if(nums == null || nums.length == 0){
      return new int[0];
    }
    int len = nums.length;
    int[] res = new int[len];
    int nLen = len*2;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    Stack<Integer> stack = new Stack<Integer>();
    for(int i = 0; i < nLen; i++){
      int num = nums[i%len];
      while(!stack.isEmpty() && nums[stack.peek()%len] < num){
        map.put(stack.pop(), num);
      }
      stack.push(i);
    }
    for(int i = 0; i < len; i++){
      int val = map.getOrDefault(i, -1);
      res[i] = val == -1 ? -1 : val;
    }
    return res;
  }
}
