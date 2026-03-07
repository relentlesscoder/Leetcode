package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 09/05/2019.
 * #1110 https://leetcode.com/problems/delete-nodes-and-return-forest/
 */
public class DeleteNodesAndReturnForest {

    // time O(n + m), space O(h + m)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // 元气森林🌳 :)
        List<TreeNode> forest = new ArrayList<>();
        // 将要删除的值加入哈希表中
        Set<Integer> toDelete = new HashSet<>();
        for (int x : to_delete) { // O(m)
            toDelete.add(x);
        }
        TreeNode r = dfs(root, toDelete, forest);
        // 注意如果根结点没被删掉的，需要把根结点加入到森林中
        if (r != null) {
            forest.add(r);
        }
        return forest;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> toDelete, List<TreeNode> forest) {
        if (root == null) {
            return null;
        }
        // 递归左右子树
        root.left = dfs(root.left, toDelete, forest);
        root.right = dfs(root.right, toDelete, forest);
        // 如果当前节点的值在哈希表中
        if (toDelete.contains(root.val)) {
            // 将左子树加入到森林中
            if (root.left != null) {
                forest.add(root.left);
            }
            // 将右子树加入到森林中
            if (root.right != null) {
                forest.add(root.right);
            }
            // 删除当前节点
            return null;
        }
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
