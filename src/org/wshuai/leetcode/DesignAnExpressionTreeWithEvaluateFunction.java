package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/22/2020.
 * #1628 https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/
 */
public class DesignAnExpressionTreeWithEvaluateFunction {

	// time O(n), space O(n)
	public Node buildTree(String[] postfix) {
		Stack<Node> stack = new Stack<>();
		for(String s : postfix){
			if(isOperator(s.charAt(0))){
				TreeNode cur = new TreeNode(s);
				cur.right = stack.pop();
				cur.left = stack.pop();
				stack.push(cur);
			}else{
				stack.push(new TreeNode(s));
			}
		}
		return stack.pop();
	}

	public static boolean isOperator(char c){
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private abstract class Node {

		public abstract int evaluate();
		// define your fields here
		public String val;

		public Node left;

		public Node right;

		public Node(String val){
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}

	private class TreeNode extends Node{

		public TreeNode(String val){
			super(val);
		}

		public int evaluate(){
			char c = this.val.charAt(0);
			if(!isOperator(c)){
				return Integer.parseInt(this.val);
			}
			int leftOperand = this.left.evaluate(), rightOperand = this.right.evaluate();
			if(c == '+'){
				return leftOperand + rightOperand;
			}else if(c == '-'){
				return leftOperand - rightOperand;
			}else if(c == '*'){
				return leftOperand * rightOperand;
			}else{
				return leftOperand / rightOperand;
			}
		}
	}
}
