package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/09/2019.
 * #0872 https://leetcode.com/problems/leaf-similar-trees/
 */
public class LeafSimilarTrees {

	// time O(n), space O(n)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		// 利用 morris 前序遍历来比较每个叶子节点的值
        List<Integer> vals = new ArrayList<>();
		// 遍历 root1 将叶子节点的值加入 vals
        preOrderTraversal(root1, vals, false);
		// 遍历 root2 将每个叶子节点与 vals 中对应的值比较
        return preOrderTraversal(root2, vals, true);
    }

    private boolean preOrderTraversal(TreeNode root, List<Integer> vals, boolean check) {
        TreeNode curr = root;
        int i = 0;
        while (curr != null) {
            if (curr.left == null) {
                if (curr.right == null) {
                    if (!check) {
                        vals.add(curr.val);
                    } else if (i >= vals.size() || vals.get(i++) != curr.val) {
                        return false;
                    }
                }
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
                    pre.right = null;
                    if (pre.left == null) {
                        if (!check) {
                            vals.add(pre.val);
                        } else if (i >= vals.size() || vals.get(i++) != pre.val) {
                            return false;
                        }
                    }
                    curr = curr.right;
                }
            }
        }
        return !check || i == vals.size();
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
