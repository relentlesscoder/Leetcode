package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/25/2016.
 * #0071 https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {
	public String simplifyPath(String path) {
		String[] strs = path.split("/");
		Stack<String> stack = new Stack<>();
		for(String v : strs){
			if(v.length() == 0 || v.equals(".")){
				continue;
			}
			if(v.equals("..")){
				if(!stack.isEmpty()){
					stack.pop();
				}
			}else{
				stack.push(v);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(String v : stack){
			sb.append("/").append(v);
		}
		return sb.length() == 0 ? "/" : sb.toString();
	}
}
