package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 1/7/2017.
 * #445 https://leetcode.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {
  //O(n)
  public LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
    if(l1 == null){
      return l2;
    }
    if(l2 == null){
      return l1;
    }
    LinkedListNode root = null;
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();
    LinkedListNode r1 = l1;
    while(r1 != null){
      s1.push(r1.val);
      r1 = r1.next;
    }
    LinkedListNode r2 = l2;
    while(r2 != null){
      s2.push(r2.val);
      r2 = r2.next;
    }
    int carry = 0;
    while(!s1.isEmpty() || !s2.isEmpty() || carry > 0){
      int o1 = s1.isEmpty() ? 0 : s1.pop();
      int o2 = s2.isEmpty() ? 0 : s2.pop();
      int sum = o1 + o2 + carry;
      LinkedListNode curr = new LinkedListNode(sum%10);
      curr.next = root;
      root = curr;
      carry = sum/10;
    }

    return root;
  }
}
