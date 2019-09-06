package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #1120 https://leetcode.com/problems/maximum-average-subtree/
 */
public class MaximumAverageSubtree {
    private double maxAvg;

    public double maximumAverageSubtree(TreeNode root) {
        maxAvg = 1.0;
        maximumAverageSubtreeUtil(root);
        return maxAvg;
    }

    private int[] maximumAverageSubtreeUtil(TreeNode node){
        int[] res = new int[2];
        if(node == null){
            return res;
        }
        int[] left = maximumAverageSubtreeUtil(node.left);
        int[] right = maximumAverageSubtreeUtil(node.right);
        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;
        res[0] = sum;
        res[1] = count;
        maxAvg = Math.max(maxAvg, sum*1.0/count);
        return res;
    }
}
