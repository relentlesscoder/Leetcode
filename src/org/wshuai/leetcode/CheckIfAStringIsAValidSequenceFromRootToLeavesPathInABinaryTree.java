package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 05/01/2020.
 * #1430 https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/
 */
public class CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {
    // time O(n)
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int cur){
        if(root == null || cur == arr.length){
            return false;
        }
        if(root.val != arr[cur]){
            return false;
        }
        if(root.left == null && root.right == null && cur == arr.length - 1){
            return true;
        }
        return dfs(root.left, arr, cur + 1) || dfs(root.right, arr, cur + 1);
    }

    // time O(n), space O(n)
    public boolean isValidSequenceBFS(TreeNode root, int[] arr) {
        if(root == null){
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        int n = arr.length, i = 0;
        while(!queue.isEmpty() && i < n){
            int size = queue.size();
            while(size-- > 0){
                TreeNode cur = queue.pollFirst();
                if(cur.val != arr[i]){
                    continue;
                }
                if(cur.left == null && cur.right == null && i == n - 1){
                    return true;
                }
                if(cur.left != null){
                    queue.offerLast(cur.left);
                }
                if(cur.right != null){
                    queue.offerLast(cur.right);
                }
            }
            i++;
        }
        return false;
    }
}
