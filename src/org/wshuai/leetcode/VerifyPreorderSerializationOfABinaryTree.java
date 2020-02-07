package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/24/2016.
 * #0331 https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class VerifyPreorderSerializationOfABinaryTree {
	// time O(n)
	public boolean isValidSerialization(String preorder) {
		if(preorder == null || preorder.isEmpty()){
			return false;
		}
		String[] nodes = preorder.split(",");
		int len = nodes.length;
		Stack<String> stk = new Stack<String>();
		for(int i = 0; i < len; i++){
			stk.push(nodes[i]);
			int size = stk.size();
			while(stk.size() >= 3
					&& stk.get(size - 1).equals("#")
					&& stk.get(size - 2).equals("#")
					&& !stk.get(size - 3).equals("#")){
				stk.pop();
				stk.pop();
				stk.pop();
				stk.push("#");
				size = stk.size();
			}
		}
		return stk.size() == 1 && stk.peek().equals("#");
	}
}
