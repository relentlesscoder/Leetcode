package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2020.
 * #1612 https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/
 */
public class CheckIfTwoExpressionTreesAreEquivalent {

    // time O(n), space O(h)
    public boolean checkEquivalence(Node root1, Node root2) {
        // 分别递归统计字符出现次数，然后比较两树中所有字符次数是否都相同
        int[] freq1 = new int[26], freq2 = new int[26];
        dfs(root1, freq1);
        dfs(root2, freq2);
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int[] freq) {
        if (root.val >= 'a' && root.val <= 'z') {
            freq[root.val - 'a']++;
            return;
        }
        dfs(root.left, freq);
        dfs(root.right, freq);
    }

    /**
     * Definition for a binary tree node.
     */
    private static class Node {
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
