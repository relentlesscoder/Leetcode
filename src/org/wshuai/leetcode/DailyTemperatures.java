package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/06/2019.
 * #0739 https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {

	// time O(n), space O(n)
	// Monotonic Queue
	public int[] dailyTemperatures(int[] T) {
		if(T == null || T.length == 0){
			return new int[0];
		}
		int n = T.length;
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			while(!stack.isEmpty() && T[stack.peek()] < T[i]){
				res[stack.peek()] = i - stack.pop();
			}
			stack.push(i);
		}
		return res;
	}
}
