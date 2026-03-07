package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/15/2019.
 * #0450 https://leetcode.com/problems/delete-node-in-a-bst/
 */
public class DeleteNodeInABST {

    // time O(log(n)), space O(1)
    public TreeNode deleteNode(TreeNode root, int key) {
        // CLRS, P297
        TreeNode dummy = new TreeNode(0), // dummy 根结点
                parent = dummy, // 当前节点的父节点
                cur = root; // 当前节点
        dummy.left = root;
        // 在 BST 中搜索值为 key 的节点
        while (cur != null) {
            if (cur.val == key) {
                break;
            }
            parent = cur;
            if (cur.val > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        // 如果能找到值为 key 的节点则删除节点
        if (cur != null) {
            // 如果左子节点为空，则用右子节点替代当前节点
            if (cur.left == null) {
                replace(cur, cur.right, parent);
            } else if (cur.right == null) { // 如果右子节点为空，则用左子节点替代当前节点
                replace(cur, cur.left, parent);
            } else { //左右子节点都不为空
                // 寻找后继节点
                TreeNode prev = null, successor = cur.right;
                while (successor.left != null) {
                    prev = successor;
                    successor = successor.left;
                }
                // 将节点的值设为后继节点的值
                cur.val = successor.val;
                // 如果后继节点是当前节点的右子节点，将右节点设为后继节点的右子节点
                if (successor == cur.right) {
                    cur.right = successor.right;
                } else { // 否者用后继节点的右子节点替代后继节点
                    replace(successor, successor.right, prev);
                }
            }
        }
        return dummy.left;
    }

    private void replace(TreeNode original, TreeNode replace, TreeNode parent) {
        if (parent.left == original) {
            parent.left = replace;
        } else {
            parent.right = replace;
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
