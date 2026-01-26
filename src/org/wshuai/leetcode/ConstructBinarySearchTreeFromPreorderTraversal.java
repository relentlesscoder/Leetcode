package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/30/2019.
 * #1008 https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

	// time O(n), space O(n)
	public TreeNode bstFromPreorderMonotonicStack(int[] preorder) {
		// 利用单调栈计算每个索引的下一个比它大的值的索引，这个索引即为右子树的根结点。然后
		// 根据这个性质递归构造二叉树。
		int n = preorder.length;
		int[] right = new int[n];
		Arrays.fill(right, n);
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
				right[stack.pop()] = i;
			}
			stack.push(i);
		}
		return dfs(0, n - 1, preorder, right);
	}

	private TreeNode dfs(int left, int right, int[] preorder, int[] next) {
		// 非法区间则返回空
		if (left > right) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[left]);
		// 区间内只有一个节点直接返回
		if (left == right) {
			return root;
		}
		// 递归构造左子树
		root.left = dfs(left + 1, next[left] - 1, preorder, next);
		// 递归构造右子树
		root.right = dfs(next[left], right, preorder, next);
		return root;
	}

	// time O(n * log(n)), space O(n)
	public TreeNode bstFromPreorderBinarySearch(int[] preorder) {
		return dfs1(0, preorder.length - 1, preorder);
	}

	private TreeNode dfs1(int left, int right, int[] preorder) {
		if (left > right) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[left]);
		if (left == right) {
			return root;
		}
		int low = left + 1, high = right + 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (preorder[mid] < preorder[left]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		root.left = dfs1(left + 1, low - 1, preorder);
		root.right = dfs1(low, right, preorder);
		return root;
	}

	// time O(n * log(n)), space O(n)
	public TreeNode bstFromPreorderInOrder(int[] preorder) {
		int n = preorder.length;
		// 给原数组排序得到中序遍历结果
		int[] sorted = Arrays.stream(preorder).sorted().toArray();
		Map<Integer, Integer> inorder = new HashMap<>();
		for (int i = 0; i < n; i++) {
			inorder.put(sorted[i], i);
		}
		// #0105 利用前序 + 中序递归构造二叉树
		return dfs(0, n - 1, 0, n - 1, preorder, inorder);
	}

	private TreeNode dfs(int preLeft, int preRight, int inLeft, int inRight,
						 int[] preorder, Map<Integer, Integer> inorder) {
		if (preLeft > preRight || inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preLeft]);
		int index = inorder.get(preorder[preLeft]);
		root.left = dfs(preLeft + 1, preLeft + index - inLeft,
				inLeft, index - 1, preorder, inorder);
		root.right = dfs(preLeft + index - inLeft + 1, preRight,
				index + 1, inRight, preorder, inorder);
		return root;
	}

	// time O(n^2), space O(n)
	public TreeNode bstFromPreorder(int[] preorder) {
		// 平均时间复杂度是 n * log(n), 而当二叉树是一条链的时候最坏时间复杂度可以达到 n^2
		int n = preorder.length;
		return dfs(0, n - 1, preorder);
	}

	private TreeNode dfs(int left, int right, int[] preorder) {
		if (left > right) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[left]);
		if (left == right) {
			return root;
		}
		int idx = left + 1;
		for (; idx <= right && preorder[idx] < preorder[left]; idx++) {}
		root.left = dfs(left + 1, idx - 1, preorder);
		root.right = dfs(idx, right, preorder);
		return root;
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
