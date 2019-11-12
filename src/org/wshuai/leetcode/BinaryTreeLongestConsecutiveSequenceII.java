package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/31/2019.
 * #549 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
 */
public class BinaryTreeLongestConsecutiveSequenceII {
	private Map<TreeNode, int[]> map;
	int res = 0;

	public int longestConsecutive(TreeNode root) {
		map = new HashMap<>();
		dfs(root, -1);
		dfs(root, 1);
		return res;
	}

	private int dfs(TreeNode root, int dir){
		if(root == null){
			return 0;
		}
		int l = dfs(root.left, dir);
		int left = root.left == null || (root.left != null && root.val - root.left.val == dir) ? l : 0;
		int r = dfs(root.right, dir);
		int right = root.right == null || (root.right != null && root.val - root.right.val == dir) ? r : 0;
		if(dir == 1){
			int rightAsc = map.get(root)[1];
			int leftAsc = map.get(root)[0];
			if(left > 0 && rightAsc > 0){
				res = Math.max(res, left + rightAsc + 1);
			}
			if(right > 0 && leftAsc > 0){
				res = Math.max(res, right + leftAsc + 1);
			}
		}else{
			map.put(root, new int[2]);
			map.get(root)[0] = left;
			map.get(root)[1] = right;
		}
		int curr = 1 + Math.max(left, right);
		res = Math.max(res, curr);
		return curr;
	}
}
