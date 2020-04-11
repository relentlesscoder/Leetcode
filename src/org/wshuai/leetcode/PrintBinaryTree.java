package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/27/2019.
 * #0655 https://leetcode.com/problems/print-binary-tree/
 */
public class PrintBinaryTree {
	// time O(n)
	public List<List<String>> printTree(TreeNode root) {
		List<List<String>> res = new ArrayList<>();
		int row = getHeight(root), col = (int) Math.pow(2, row) - 1;
		List<String> ans = new ArrayList<>();
		for(int i = 0; i < col; i++){
			ans.add("");
		}
		for(int i = 0; i < row; i++){
			res.add(new ArrayList<>(ans));
		}
		populateResult(root, res, 0, row, 0, col - 1);
		return res;
	}

	private void populateResult(TreeNode root, List<List<String>> res, int curRow, int totalRow, int i, int j){
		if(root == null || curRow == totalRow){
			return;
		}
		res.get(curRow).set((i + j) / 2, String.valueOf(root.val));
		populateResult(root.left, res, curRow + 1, totalRow, i, (i + j) / 2 - 1);
		populateResult(root.right, res, curRow + 1, totalRow, (i + j) / 2 + 1, j);
	}

	private int getHeight(TreeNode root){
		if(root == null){
			return 0;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}
}
