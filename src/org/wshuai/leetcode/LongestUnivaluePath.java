package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #687 https://leetcode.com/problems/longest-univalue-path/
 */
public class LongestUnivaluePath {
    int max;

    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        longestUnivaluePathUtil(root);
        return max;
    }

    private int longestUnivaluePathUtil(TreeNode root){
        if(root == null){
            return 0;
        }
        int l = longestUnivaluePathUtil(root.left);
        int r = longestUnivaluePathUtil(root.right);
        int cl = 0;
        int cr = 0;
        if(root.left != null && root.val == root.left.val){
            cl = l + 1;
        }
        if(root.right != null && root.val == root.right.val){
            cr = r + 1;
        }
        // cl+cr represents the arrow that pass through current node
        max = Math.max(max, cl+cr);
        return Math.max(cl, cr);
    }
}
