package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2020.
 * #1644 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class LowestCommonAncestorOfABinaryTreeII {

    private TreeNode res;

    // time O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        dfs(root, p, q);
        return res;
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return null;
        }
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        if((left == p && right == q) || (left == q && right == p)){
            res = root;
        }else if(root == p && (left == q || right == q)){
            res = root;
        }else if(root == q && (left == p || right == p)){
            res = root;
        }else if(root == p || root == q){
            return root;
        }else if(left == p || left == q){
            return left;
        }else if(right == p || right == q){
            return right;
        }
        return null;
    }
}
