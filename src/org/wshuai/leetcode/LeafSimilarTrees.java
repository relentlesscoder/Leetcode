package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 8/9/19.
 * #872 https://leetcode.com/problems/leaf-similar-trees/
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> lst1 = getLeafNodes(root1);
        List<Integer> lst2 = getLeafNodes(root2);
        if(lst1.size() != lst2.size()){
            return false;
        }
        for(int i = 0; i < lst1.size();i++){
            if(lst1.get(i) != lst2.get(i)){
                return false;
            }
        }
        return true;
    }

    private List<Integer> getLeafNodes(TreeNode root){
        List<Integer> res= new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.empty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            }
            else{
                TreeNode parent = stack.pop();
                if(parent.left == null && parent.right == null){
                    res.add(parent.val);
                }
                current = parent.right;
            }
        }
        return res;
    }
}
