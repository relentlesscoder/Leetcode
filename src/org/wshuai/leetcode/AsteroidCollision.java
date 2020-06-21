package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/17/2019.
 * #0735 https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {
	// time O(n), space O(n)
	public int[] asteroidCollision(int[] asteroids) {
		if(asteroids == null || asteroids.length == 0){
			return new int[0];
		}
		int n = asteroids.length;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			if(asteroids[i] < 0 && !stack.isEmpty() && stack.peek() > 0){
				while(!stack.isEmpty() && stack.peek() > 0 && Math.abs(asteroids[i]) > stack.peek()){
					stack.pop();
				}
				if(stack.isEmpty() || stack.peek() < 0){
					stack.push(asteroids[i]);
				}else if(Math.abs(asteroids[i]) == stack.peek()){
					stack.pop();
				}
			}else{
				stack.push(asteroids[i]);
			}
		}
		int m = stack.size();
		int[] res = new int[m];
		for(int i = m - 1; i >= 0; i--){
			res[i] = stack.pop();
		}
		return res;
	}
}
