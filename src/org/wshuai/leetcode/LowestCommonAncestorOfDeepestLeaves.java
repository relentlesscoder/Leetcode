package org.wshuai.leetcode;

import java.util.HashMap;

/**
 * Created by Wei on 9/4/19.
 * #1123 https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class LowestCommonAncestorOfDeepestLeaves {
    HashMap<TreeNode, Integer> heights = new HashMap<TreeNode,Integer>();

    // see solution at https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334583/Java-O(n)-Short-and-Simple-Recursion
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null || height(root.right) == height(root.left)){
            return root;
        }
        return lcaDeepestLeaves(height(root.left) > height(root.right) ? root.left : root.right);
    }

    public int height(TreeNode root){
        if(root==null){
            return 0;
        }
        if(heights.containsKey(root)){
            return heights.get(root);
        }
        heights.put(root, 1 + Math.max(height(root.left), height(root.right)));
        return heights.get(root);
    }
}
