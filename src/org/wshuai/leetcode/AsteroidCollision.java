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
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < asteroids.length; i++){
			if(asteroids[i] < 0){
				// current asteroid moves to the left, it explodes all asteroids
				// moving right that have smaller size
				while(!stack.isEmpty() && stack.peek() > 0 && Math.abs(asteroids[i]) > stack.peek()){
					stack.pop();
				}
				// if no asteroid left or all asteroids left moves to left, add
				// current to the queue
				if(stack.isEmpty() || stack.peek() < 0){
					stack.push(asteroids[i]);
				// if there are asteroids moving to right left with same size,
				// the collision will explode both
				}else if(stack.peek() == Math.abs(asteroids[i])){
					stack.pop();
				}
				// otherwise, the current will explode
			}else{
				stack.push(asteroids[i]);
			}
		}
		int[] res = new int[stack.size()];
		int k = 0;
		for(int s : stack){
			res[k++] = s;
		}
		return res;
	}
}
