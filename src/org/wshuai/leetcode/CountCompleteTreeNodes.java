package org.wshuai.leetcode;

/**
 * Created by Wei on 02/20/2017.
 * #0222 https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountCompleteTreeNodes {

    // time O(log(n) * log(n)), space O(1)
    public int countNodes(TreeNode root) {
        int height = 0;
        // 一路向右找到二叉树最右端的节点的高度 h ，两种情况:
        //   1. 二叉树是满的，则总节点的个数为 2^h - 1
        //   2. 二叉树不是满的，则树的高度为 h + 1二节点总数最多可为 2^(h + 1) - 1
        TreeNode curr = root;
        while (curr != null) { // O(log(n))
            height++;
            curr = curr.right;
        }
        // 二分查找二叉树节点个数
        int low = (1 << height) - 1, high = (1 << (height + 1)) - 1;
        while (low < high) { // O(log(n))
            int mid = low + (high - low + 1) / 2;
            if (!exists(mid, root)) { // O(log(n))
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    private boolean exists(int num, TreeNode root) {
        // 根据 num 判断节点到根结点的路径，并用一个整型表示
        int path = 0, level = 0;
        while (num > 1) {
            level++;
            path = (path << 1) + (num % 2);
            num /= 2;
        }
        // 按照路径遍历二叉树，如果路径能走完说明节点存在
        TreeNode node = root;
        for (; node != null && level > 0; level--) {
            if ((path & 1) == 1) {
                node = node.right;
            } else {
                node = node.left;
            }
            path >>= 1;
        }
        return level == 0 && node != null;
    }

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
