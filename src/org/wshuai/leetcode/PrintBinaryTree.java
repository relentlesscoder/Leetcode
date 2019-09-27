package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/27/19.
 * #655 https://leetcode.com/problems/print-binary-tree/
 */
public class PrintBinaryTree {
	private int height;
	private List<List<String>> res;

	public List<List<String>> printTree(TreeNode root) {
		height = 0;
		getHeight(root, 0);
		int N = getLength(height);

		res = new ArrayList<>();
		for(int i = 0; i <= height; i++){
			List<String> lst = new ArrayList<>();
			for(int k = 0; k < N; k++){
				lst.add("");
			}
			res.add(lst);
		}
		dfs(root, 0, N - 1, 0);
		return res;
	}

	private void dfs(TreeNode root, int left, int right, int level){
		if(root == null){
			return;
		}
		List<String> curr = res.get(level);
		int mid = (left + right) / 2;
		curr.set(mid, "" + root.val);
		dfs(root.left, left, mid, level + 1);
		dfs(root.right, mid + 1, right, level + 1);
	}

	private void getHeight(TreeNode root, int curr){
		if(root == null){
			height = Math.max(curr - 1, height);
			return;
		}
		getHeight(root.left, curr + 1);
		getHeight(root.right, curr + 1);
	}

	private int getLength(int h){
		int res = 0;
		int num = 1;
		int count = 0;
		while(count <= h){
			res += num;
			num <<= 1;
			count++;
		}
		return res;
	}
}
