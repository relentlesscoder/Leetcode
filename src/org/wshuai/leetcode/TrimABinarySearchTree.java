package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #669 https://leetcode.com/problems/trim-a-binary-search-tree/
 */
public class TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null){
            return null;
        }
        int val = root.val;
        if(val < L){
            return trimBST(root.right, L, R);
        }else if(val > R){
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
