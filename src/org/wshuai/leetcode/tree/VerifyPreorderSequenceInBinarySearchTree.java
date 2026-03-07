package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/19/2016.
 * #0255 https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 */
public class VerifyPreorderSequenceInBinarySearchTree {

	// time O(n), space O(n)
	public boolean verifyPreorder(int[] preorder) {
		// 讲解: https://leetcode.cn/problems/verify-preorder-sequence-in-binary-search-tree/solutions/2012008/javati-jie-by-xiao-hui-he-xiao-bai-i4fo/
		// 二叉搜索树的先序遍历是先遍历左子树递减 -> 再遍历右子树递增的循环。可以维护一个单调递减的栈入栈时的顺序是
		// 根结点 -> 左子树 -> 右子树。遍历数组，对每个当前值弹出栈顶比他小的索引。入栈保证了根结点大于左子树而出栈
		// 时保证右子树大于根结点和左子树。出栈时更新 min 值表示当前根结点的值后续的值(在右子树中)都不能小于这个值。
		// 示例1:
		//   [5,2,1,3,9,7,6,8,10]
		int n = preorder.length, min = -1;
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (preorder[i] < min) {
				return false;
			}
			while (!stack.isEmpty() && preorder[i] > stack.peek()) {
				min = stack.pop();
			}
			stack.push(preorder[i]);
		}
		return true;
	}

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
