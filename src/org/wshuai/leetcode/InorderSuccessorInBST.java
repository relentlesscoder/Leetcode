package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #0285 https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorInBST {

	// time O(n), space O(1)
	public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
		TreeNode res = null;
		while (root != null) {
			// 如果当前节点的值大于 p ，当前节点可作为一个候选人。继续去左子树找可能存在的更小的
			// 大于 p 的值。
			if (p.val < root.val) {
				res = root;
				root = root.left;
			} else { // 如果当前节点的值小于 p 则当前节点不符合要求，去右子树找符合要求的值。
				root = root.right;
			}
		}
		// 循环结束就找到了大于 p 的最小值。
		return res;
	}

    // time O(n), space O(n)
    public TreeNode inorderSuccessorRecursive(TreeNode root, TreeNode p) {
		// 上面解法的递归版
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessorRecursive(root.right, p);
        } else {
            TreeNode left = inorderSuccessorRecursive(root.left, p);
            return (left != null) ? left : root;
        }
    }

    private TreeNode last = null;
    private TreeNode res = null;

    // time O(n), space O(n)
    public TreeNode inorderSuccessorDFS(TreeNode root, TreeNode p) {
        last = null;
        res = null;
        dfs(root, p);
        return res;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null || res != null) {
            return;
        }
        dfs(root.left, p);
        if (last == p) {
            res = root;
        }
        last = root;
        dfs(root.right, p);
    }

    // time O(n), space O(1)
    public TreeNode inorderSuccessorMorris(TreeNode root, TreeNode p) {
        TreeNode curr = root, last = null;
        while (curr != null) {
            if (curr.left == null) {
                if (last == p) {
                    return curr;
                }
                last = curr;
                curr = curr.right;
            } else {
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    if (last == p) {
                        return curr;
                    }
                    pre.right = null;
                    last = curr;
                    curr = curr.right;
                }
            }
        }
        return null;
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
