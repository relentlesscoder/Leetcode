package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/22/2019.
 * #0682 https://leetcode.com/problems/baseball-game/
 */
public class BaseballGame {

	// time O(n), space O(n)
	public int calPoints(String[] ops) {
		int res = 0, cur = 0, last = 0;
		Stack<Integer> stack = new Stack<>();
		for(String op : ops){
			if(op.equals("+")){
				last = stack.pop();
				cur = last + stack.peek();
				stack.push(last);
			}else if(op.equals("D")){
				cur = (stack.peek() << 1);
			}else if(op.equals("C")){
				res -= stack.pop();
			}else{
				cur = Integer.parseInt(op);
			}
			if(!op.equals("C")){
				stack.push(cur);
				res += cur;
			}
		}
		return res;
	}
}
