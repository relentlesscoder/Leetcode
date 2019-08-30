package org.wshuai.leetcode;

/**
 * Created by Wei on 8/30/2019.
 * #1008 https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = buildTree(preorder, 0, preorder.length-1);
        return root;
    }

    private TreeNode buildTree(int[] nums, int left, int right){
        if(left < 0 || right > nums.length || left > right){
            return null;
        }
        int rval = nums[left];
        TreeNode node = new TreeNode(rval);
        int idx = left + 1;
        // find the pivot value to divide left and right subtree
        while(idx <= right && nums[idx] < rval){
            idx++;
        }
        node.left = buildTree(nums, left+1, idx-1);
        node.right = buildTree(nums, idx, right);
        return node;
    }
}
