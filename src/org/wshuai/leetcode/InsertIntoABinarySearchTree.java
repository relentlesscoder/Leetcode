package org.wshuai.leetcode;

/**
 * Created by Wei on 8/30/2019.
 * #701 https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoABinarySearchTree {
    // CLRS, page 294
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode node = root;
        while(node != null){
            parent = node;
            if(val < node.val){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        TreeNode add = new TreeNode(val);
        if(parent == null){
            return add;
        }
        if(val < parent.val){
            parent.left = add;
        }else{
            parent.right = add;
        }
        return root;
    }
}
