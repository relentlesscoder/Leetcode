package org.wshuai.leetcode;

/**
 * Created by Wei on 07/26/2020.
 * #1530 https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
 */
public class NumberOfGoodLeafNodesPairs {

	private int res;

	// time O(n*100), space O(n*10)
	public int countPairs(TreeNode root, int distance) {
		res = 0;
		dfs(root, distance);
		return res;
	}

	private int[] dfs(TreeNode root, int distance){
		int[] count = new int[11];
		if(root == null){
			return count;
		}
		if(root.left == null && root.right == null){
			count[1] = 1;
			return count;
		}
		int[] left = dfs(root.left, distance);
		int[] right = dfs(root.right, distance);
		for(int i = 1; i <= 10; i++){
			for(int j = 1; j <= 10; j++){
				if((i + j) <= distance){
					res += left[i] * right[j];
				}
			}
		}
		for(int i = 0; i < 10; i++){
			if(left[i] > 0){
				count[i + 1] += left[i];
			}
			if(right[i] > 0){
				count[i + 1] += right[i];
			}
		}
		return count;
	}
}
