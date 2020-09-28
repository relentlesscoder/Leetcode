package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/17/2019.
 * #0772 https://leetcode.com/problems/basic-calculator-iii/
 */
public class BasicCalculatorIII {

	private static final Map<Character, Integer> PRECEDENCE;

	static{
		PRECEDENCE = new HashMap<>();
		PRECEDENCE.put('(', -1);
		PRECEDENCE.put('+', 0);
		PRECEDENCE.put('-', 0);
		PRECEDENCE.put('*', 1);
		PRECEDENCE.put('/', 1);
	}

	// time O(n), space O(n)
	public int calculate(String s) {
		Deque<Integer> operands = new ArrayDeque<>();
		Deque<Character> operators = new ArrayDeque<>();
		int n = s.length();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c >= '0' && c <= '9'){
				int val = c - '0';
				while(i + 1 < n && Character.isDigit(s.charAt(i + 1))){
					val = val * 10 + (s.charAt(i + 1) - '0');
					i++;
				}
				operands.push(val);
			}else if(c == '('){
				operators.push(c);
			}else if(c == ')'){
				while(operators.peek() != '('){
					operands.push(calculate(operands, operators));
				}
				operators.pop();
			}else{
				while(!operators.isEmpty() && comparePrecedence(c, operators.peek()) <= 0){
					operands.push(calculate(operands, operators));
				}
				operators.push(c);
			}
		}
		while(!operators.isEmpty()){
			operands.push(calculate(operands, operators));
		}
		return operands.pop();
	}

	private int calculate(Deque<Integer> operands, Deque<Character> operators){
		int a = operands.pop(), b = operands.isEmpty() ? 0 : operands.pop();
		char c = operators.pop();

		if(c == '-'){
			return b - a;
		}else if(c == '*'){
			return b * a;
		}else if(c == '/'){
			return b / a;
		}
		return b + a;
	}

	private int comparePrecedence(char a, char b){
		return PRECEDENCE.get(a) - PRECEDENCE.get(b);
	}
}
