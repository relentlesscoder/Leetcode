package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/22/2019.
 * #0682 https://leetcode.com/problems/baseball-game/
 */
public class BaseballGame {

	// time O(n), space O(n)
	public int calPoints(String[] ops) {
		int res = 0;
		Stack<Integer> stack = new Stack<>();
		for(String op : ops){
			if(op.equals("+")){
				int last = stack.pop(), cur = last + stack.peek();
				stack.push(last);
				stack.push(cur);
			}else if(op.equals("D")){
				stack.push(2 * stack.peek());
			}else if(op.equals("C")){
				stack.pop();
			}else{
				stack.push(Integer.parseInt(op));
			}
		}
		for(int score : stack){
			res += score;
		}
		return res;
	}
}
