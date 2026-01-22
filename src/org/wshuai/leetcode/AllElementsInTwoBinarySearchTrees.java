package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/30/2019.
 * #1305 https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class AllElementsInTwoBinarySearchTrees {

    // time O(m + n), space O(1)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		// Morris 遍历二叉树 + 合并两个排序好的数列
        List<Integer> res = new ArrayList<>();
        MorrisIterator itr1 = new MorrisIterator(root1), itr2 = new MorrisIterator(root2);
        for (TreeNode node1 = itr1.next(), node2 = itr2.next(); node1 != null || node2 != null; ) {
            if (node2 == null || (node1 != null && node1.val < node2.val)) {
                res.add(node1.val);
                node1 = itr1.next();
            } else {
                res.add(node2.val);
                node2 = itr2.next();
            }
        }
        return res;
    }

    private static class MorrisIterator {

        private TreeNode root;

        public MorrisIterator(TreeNode root) {
            this.root = root;
        }

        public TreeNode next() {
            if (root == null) {
                return null;
            }
            TreeNode curr = null;
            while (root != null) {
                if (root.left == null) {
                    curr = root;
                    root = root.right;
                    break;
                } else {
                    TreeNode pre = root.left;
                    while (pre.right != null && pre.right != root) {
                        pre = pre.right;
                    }
                    if (pre.right == null) {
                        pre.right = root;
                        root = root.left;
                    } else {
                        pre.right = null;
                        curr = root;
                        root = root.right;
                        break;
                    }
                }
            }
            return curr;
        }
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
