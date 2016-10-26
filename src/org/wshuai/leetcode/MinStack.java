package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #155 https://leetcode.com/problems/min-stack/
 */
public class MinStack {
  private int min = Integer.MAX_VALUE;;
  private Stack<Integer> s1;
  //Auxiliary stack saves the current min
  private Stack<Integer> s2;

  /** initialize your data structure here. */
  public MinStack() {
    s1 = new Stack<Integer>();
    s2 = new Stack<Integer>();
  }

  public void push(int x) {
    if(x < min){
      min = x;
    }
    s1.push(x);
    s2.push(min);
  }

  public void pop() {
    s1.pop();
    s2.pop();
    if(!s1.empty()){
      min = s2.peek();
    }else{
      min = Integer.MAX_VALUE;
    }
  }

  public int top() {
    return s1.peek();
  }

  public int getMin() {
    return s2.peek();
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
