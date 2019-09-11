package org.wshuai.leetcode;

/**
 * Created by Wei on 9/11/2019.
 * #979 https://leetcode.com/problems/distribute-coins-in-binary-tree/
 */
public class DistributeCoinsInBinaryTree {
    int ans;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        // calculate the move that needs to be make between the current node and its children
        ans += Math.abs(left) + Math.abs(right);
        // calculate the remainder of the current node after all moves
        return node.val + left + right - 1;
    }
}
