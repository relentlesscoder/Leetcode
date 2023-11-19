package org.wshuai.leetcode;

/**
 * Created by Wei on 02/04/2021.
 * #1740 https://leetcode.com/problems/find-distance-in-a-binary-tree/
 */
public class FindDistanceInABinaryTree {

    private int res;

    // time O(n)
    public int findDistance(TreeNode root, int p, int q) {
        res = 0;
        if(p == q){
            return res;
        }
        dfs(root, p, q);
        return res;
    }

    private int dfs(TreeNode root, int p, int q){
        if(root == null){
            return 0;
        }
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        if(left > 0 && right > 0){ // LCA
            res = left + right;
            return res;
        }
        if((root.val == p || root.val == q) && (left > 0 || right > 0)){ // one of the node is the LCA
            res = Math.max(left, right);
            return res;
        }
        if(root.val == p || root.val == q){
            return 1;
        }
        if(left > 0 || right > 0){
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
