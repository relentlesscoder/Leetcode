package org.wshuai.leetcode;

import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 03/20/2020.
 * #0636 https://leetcode.com/problems/exclusive-time-of-functions/
 */
public class ExclusiveTimeOfFunctions {

	// time O(n), space O(n)
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] res = new int[n];
		Stack<int[]> stack = new Stack<>();
		for(String s : logs){
			String[] vals = s.split(":");
			int id = Integer.parseInt(vals[0]), ts = Integer.parseInt(vals[2]);
			if(vals[1].equals("start")){
				stack.push(new int[]{id, ts});
			}else{
				int[] start = stack.pop();
				int timespan = ts - start[1] + 1;
				res[id] += timespan;
				if(!stack.isEmpty()){ // deduct the timespan from parent task
					res[stack.peek()[0]] -= timespan;
				}
			}
		}
		return res;
	}
}
