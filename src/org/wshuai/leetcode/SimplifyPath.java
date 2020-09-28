package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/25/2016.
 * #0071 https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {

	// time O(n), space O(n)
	public String simplifyPath(String path) {
		String[] strs = path.split("/");
		Stack<String> stack = new Stack<>();
		for(String s : strs){
			if(s.isEmpty() || s.equals(".")){
				continue;
			}
			if(s.equals("..")){
				if(!stack.isEmpty()){
					stack.pop();
				}
			}else{
				stack.push(s);
			}
		}
		StringBuilder res = new StringBuilder();
		for(String s : stack){
			res.append("/" + s);
		}
		return res.length() == 0 ? "/" : res.toString();
	}
}
