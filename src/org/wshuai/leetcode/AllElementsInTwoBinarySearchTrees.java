package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 12/30/2019.
 * #1305 https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class AllElementsInTwoBinarySearchTrees {

	// time O(m+n), space O(m+n)
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack1 = new Stack<>(), stack2 = new Stack<>();
		pushLeft(stack1, root1);
		pushLeft(stack2, root2);
		while(!stack1.isEmpty() || !stack2.isEmpty()){
			Stack<TreeNode> cur;
			if(stack1.isEmpty()){
				cur = stack2;
			}else if(stack2.isEmpty()){
				cur = stack1;
			}else{
				cur = stack1.peek().val < stack2.peek().val ? stack1 : stack2;
			}
			res.add(cur.peek().val);
			pushLeft(cur, cur.pop().right);
		}
		return res;
	}

	private void pushLeft(Stack<TreeNode> stack, TreeNode node){
		while(node != null){
			stack.push(node);
			node = node.left;
		}
	}
}
