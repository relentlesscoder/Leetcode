package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/02/2023.
 * #2689 https://leetcode.com/problems/extract-kth-character-from-the-rope-tree/
 */
public class ExtractKthCharacterFromTheRopeTree {

    // time O(n), space O(h)
    public char getKthCharacter(RopeTreeNode root, int k) {
        // 暴力法是把所有叶节点的字符串连起来然后判断第 k 个字符是什么。优化的解法是利用
        // 节点的值更新 k 的值并且决定遍历左边还是右边节点。
        if (root.len == 0) {
            return root.val.charAt(k - 1);
        }
        // 计算左边子树的字符串长度
        int cnt = root.left == null ? 0 :
                root.left.len == 0 ? root.left.val.length() : root.left.len;
        // 如果 k 值小于等于左边字符串长度，遍历左子树
        if (k <= cnt) {
            return getKthCharacter(root.left, k);
        } else { // 如果 k 值大于左边字符串长度，更新 k 的值为 k - cnt 然后遍历右子树
            return getKthCharacter(root.right, k - cnt);
        }
    }

    /**
     * Definition for a rope tree node.
     */
    private static class RopeTreeNode {
        int len;
        String val;
        RopeTreeNode left;
        RopeTreeNode right;

        RopeTreeNode() {
        }

        RopeTreeNode(String val) {
            this.len = 0;
            this.val = val;
        }

        RopeTreeNode(int len) {
            this.len = len;
            this.val = "";
        }

        RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
            this.len = len;
            this.val = "";
            this.left = left;
            this.right = right;
        }
    }
}
