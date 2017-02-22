package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 2/21/17.
 * #496 https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElementI {
  public int[] nextGreaterElement(int[] findNums, int[] nums) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    Stack<Integer> stack = new Stack<Integer>();
    for(int num: nums){
      while(!stack.isEmpty() && stack.peek() < num){
        map.put(stack.pop(), num);
      }
      stack.push(num);
    }
    int len = findNums.length;
    int[] res = new int[len];
    for(int i = 0; i < len; i++){
      res[i] = map.getOrDefault(findNums[i], -1);
    }
    return res;
  }
}
