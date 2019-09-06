package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #1026 https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    private int maxDiff;
    private int max;
    private int min;

    public int maxAncestorDiff(TreeNode root) {
        maxDiff = 0;
        max = root.val;
        min = root.val;
        maxAncestorDiffUtil(root);
        return maxDiff;
    }

    private void maxAncestorDiffUtil(TreeNode node){
        if(node == null){
            return;
        }
        // max diff can be the diff between max/min and current node
        int curr1 = Math.abs(max - node.val);
        int curr2 = Math.abs(node.val - min);
        int curr = Math.max(curr1, curr2);
        maxDiff = Math.max(curr, maxDiff);
        int temp1 = max;
        int temp2 = min;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        maxAncestorDiffUtil(node.left);
        maxAncestorDiffUtil(node.right);
        //reset max/min after the current node
        max = temp1;
        min = temp2;
    }
}
