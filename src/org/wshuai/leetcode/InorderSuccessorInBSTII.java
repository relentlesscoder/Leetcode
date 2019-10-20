package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/20/2019.
 * #510 https://leetcode.com/problems/inorder-successor-in-bst-ii/
 */
public class InorderSuccessorInBSTII {
	public TreeNodeWithParent inorderSuccessor(TreeNodeWithParent x) {
		TreeNodeWithParent y = x;
		while(y.parent != null){
			y = y.parent;
		}
		boolean next = false;
		Stack<TreeNodeWithParent> stack = new Stack<>();
		TreeNodeWithParent curr = y;
		while(!stack.isEmpty() || curr != null){
			if(curr != null){
				stack.push(curr);
				curr = curr.left;
			}else{
				TreeNodeWithParent node = stack.pop();
				if(next){
					return node;
				}
				if(node == x){
					next = true;
				}
				curr = node.right;
			}
		}
		return null;
	}
}
