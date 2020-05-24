package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1457 https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 */
public class PseudoPalindromicPathsInABinaryTree {

	// time O(n)
	public int pseudoPalindromicPaths (TreeNode root) {
		return dfs(root, new int[10]);
	}

	private int dfs(TreeNode root, int[] count){
		if(root == null){
			return 0;
		}
		count[root.val]++;
		int res = 0;
		if(root.left == null && root.right == null){
			res = isPalindrome(count) ? 1 : 0;
		}else{
			res = dfs(root.left, count) + dfs(root.right, count);
		}
		count[root.val]--;
		return res;
	}

	private boolean isPalindrome(int[] count){
		int odd = 0;
		for(int i = 1; i < 10; i++){
			if(count[i] % 2 == 1){
				odd++;
			}
			if(odd > 1){
				return false;
			}
		}
		return true;
	}
}
