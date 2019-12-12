package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 12/10/2019.
 * #1106 https://leetcode.com/problems/parsing-a-boolean-expression/
 */
public class ParsingABooleanExpression {
	public boolean parseBoolExpr(String expression) {
		Stack<Character> stack = new Stack<>();
		char[] arr = expression.toCharArray();
		for(int i = 0; i < arr.length; i++){
			char cur = arr[i];
			if(isOperator(cur) || cur == 't' || cur == 'f'){
				stack.push(cur);
			}else if(cur == ')'){
				int[] vals = new int[2];
				while(!isOperator(stack.peek())){
					char v = stack.pop();
					if(v == 't'){
						vals[0]++;
					}else{
						vals[1]++;
					}
				}
				char opr = stack.pop();
				char res = evaluateExp(opr, vals);
				stack.push(res);
			}
		}
		return stack.peek() == 't' ? true : false;
	}

	private char evaluateExp(char opr, int[] vals){
		if(opr == '|'){
			return vals[0] > 0 ? 't' : 'f';
		}
		if(opr == '!'){
			return vals[1] > 0 ? 't' : 'f';
		}
		return vals[1] > 0 ? 'f' : 't';
	}

	private boolean isOperator(char cur){
		return cur == '!' || cur == '&' || cur == '|';
	}
}
