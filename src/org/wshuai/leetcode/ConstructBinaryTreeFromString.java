package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/09/2019.
 * #0536 https://leetcode.com/problems/construct-binary-tree-from-string/
 */
public class ConstructBinaryTreeFromString {

	// time O(n), space O(n)
	public TreeNode str2tree(String s) {
		Stack<TreeNode> stack = new Stack<>();
		for(int i = 0, j = i; i < s.length(); i++, j = i){
			char c = s.charAt(i);
			if(c == ')'){
				stack.pop();
			}else if((c >= '0' && c <= '9') || c == '-'){
				while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9'){
					i++;
				}
				TreeNode node = new TreeNode(Integer.parseInt(s.substring(j, i + 1)));
				if(!stack.isEmpty()){
					TreeNode parent = stack.peek();
					if(parent.left == null){
						parent.left = node;
					}else{
						parent.right = node;
					}
				}
				stack.push(node);
			}
		}
		return stack.isEmpty() ? null : stack.peek();
	}
}
