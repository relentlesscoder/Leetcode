package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2020.
 * #1612 https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/
 */
public class CheckIfTwoExpressionTreesAreEquivalent {

    // time O(n)
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] count = new int[26];
        dfs(root1, count, 1);
        dfs(root2, count, -1);
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int[] count, int val) {
        if (root == null) {
            return;
        }
        if (root.val != '+') {
            count[root.val - 'a'] += val;
        }
        dfs(root.left, count, val);
        dfs(root.right, count, val);
    }


    //Definition for a binary tree node.
    private class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
