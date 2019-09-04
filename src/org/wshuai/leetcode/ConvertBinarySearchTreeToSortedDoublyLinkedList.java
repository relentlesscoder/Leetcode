package org.wshuai.leetcode;

/**
 * Created by Wei on 9/4/19.
 * #426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    TreeNode first = null;
    TreeNode last = null;

    // use inorder traversal
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null){
            return null;
        }

        helper(root);

        last.right = first;
        first.left = last;

        return first;
    }

    private void helper(TreeNode node){
        if(node != null){
            //left
            helper(node.left);

            // for parent node, set left to last
            // for right node, set parent to last
            if(last != null){
                last.right = node;
                node.left = last;
            }else{
                // maintain the smallest node
                first = node;
            }
            // maintain the current last node
            last = node;
            //right
            helper(node.right);
        }
    }
}
