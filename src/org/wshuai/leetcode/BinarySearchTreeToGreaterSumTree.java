package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 8/24/19.
 * #1038 https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class BinarySearchTreeToGreaterSumTree {

    int pre = 0;

    // reverse in-order traversal - https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/discuss/286725/JavaC%2B%2BPython-Revered-Inorder-Traversal
    public TreeNode bstToGst(TreeNode root) {

        if(root.right != null){
            bstToGst(root.right);
        }

        root.val = pre + root.val;
        pre = root.val;

        if(root.left != null){
            bstToGst(root.left);
        }

        return root;
    }

    public TreeNode bstToGstInorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(!stack.isEmpty() || current != null){
            if(current != null){
                stack.push(current);
                current = current.left;
            }else{
                TreeNode parent = stack.pop();
                list.add(parent.val);
                current = parent.right;
            }
        }
        for(int i = list.size()-2; i >= 0; i--){
            list.set(i, list.get(i) + list.get(i+1));
        }
        stack = new Stack<>();
        current = root;
        int i = 0;
        while(!stack.isEmpty() || current != null){
            if(current != null){
                stack.push(current);
                current = current.left;
            }else{
                TreeNode parent = stack.pop();
                parent.val = list.get(i++);
                current = parent.right;
            }
        }
        return root;
    }
}
