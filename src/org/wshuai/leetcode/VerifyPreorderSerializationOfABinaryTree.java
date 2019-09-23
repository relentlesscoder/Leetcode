package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/24/2016.
 */
public class VerifyPreorderSerializationOfABinaryTree {
	public boolean isValidSerialization(String preorder) {
		if (preorder == null || preorder.isEmpty()) {
			return false;
		}
		String[] nodes = preorder.split(",");
		int len = nodes.length;
		Stack<String> stk = new Stack<String>();
		for (int i = 0; i < len; i++) {
			stk.push(nodes[i]);
			int size = stk.size();
			while (size >= 3 && stk.get(size - 1).equals("#") && stk.get(size - 2).equals("#")
					&& !stk.get(size - 3).equals("#")) {
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
