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
		int n = s.length(), cur = 0;
		Deque<Integer> operands = new ArrayDeque<>();
		Deque<Character> operators = new ArrayDeque<>();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c >= '0' && c <= '9'){
				cur = cur * 10 + (c - '0');
				if(i == n - 1 || !Character.isDigit(s.charAt(i + 1))){
					operands.push(cur);
					cur = 0;
				}
			}else if(c == '('){
				operators.push(c);
			}else if(c == ')'){
				// calculate expression within the current parentheses
				while(!operators.isEmpty() && operators.peek() != '('){
					operands.push(calculate(operands, operators));
				}
				operators.pop();
			}else{
				// calculate all previous expression with higher precedence
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
		int b = operands.pop(), a = operands.pop();
		char op = operators.pop();
		if(op == '-'){
			return a - b;
		}else if(op == '*'){
			return a * b;
		}else if(op == '/'){
			return a / b;
		}
		return a + b;
	}

	private int comparePrecedence(char a, char b){
		return PRECEDENCE.get(a) - PRECEDENCE.get(b);
	}
}
