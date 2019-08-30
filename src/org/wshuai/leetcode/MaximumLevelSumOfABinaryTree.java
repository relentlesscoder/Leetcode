package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/30/2019.
 * #1161 https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 */
public class MaximumLevelSumOfABinaryTree {
    public int maxLevelSum(TreeNode root) {
        List<TreeNode> curr = new ArrayList<>();
        List<TreeNode> prev = new ArrayList<>();
        int max = root.val;
        int res = 1;
        int level = 1;
        prev.add(root);
        while(!prev.isEmpty()){
            level++;
            int lsum = 0;
            curr = new ArrayList<>();
            for(TreeNode node: prev){
                if(node.left != null){
                    curr.add(node.left);
                    lsum += node.left.val;
                }
                if(node.right != null){
                    curr.add(node.right);
                    lsum += node.right.val;
                }
            }
            if(lsum > max){
                max = lsum;
                res = level;
            }
            prev = curr;
        }
        return res;
    }
}
