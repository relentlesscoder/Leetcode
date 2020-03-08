package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/09/2019.
 * #0536 https://leetcode.com/problems/construct-binary-tree-from-string/
 */
public class ConstructBinaryTreeFromString {
	// time O(n), space O(n)
	public TreeNode str2tree(String s) {
		if(s == null || s.isEmpty()){
			return null;
		}
		TreeNode cur = null, parent = null;
		char[] arr = s.toCharArray();
		Stack<TreeNode> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++){
			char c = arr[i];
			if(c == '(' || c == ')'){
				if(sb.length() > 0){
					cur = new TreeNode(Integer.parseInt(sb.toString()));
					sb = new StringBuilder();
					if(!stack.isEmpty()){
						parent = stack.peek();
						if(parent.left == null){
							parent.left = cur;
						}else{
							parent.right = cur;
						}
					}
					stack.push(cur);
				}
				if(c == ')'){
					stack.pop();
				}
			}
			else{
				sb.append(c);
			}
		}
		// corner case
		if(sb.length() > 0){
			cur = new TreeNode(Integer.parseInt(sb.toString()));
			stack.push(cur);
		}
		return stack.peek();
	}
}
