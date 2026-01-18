package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2023.
 * #2265 https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 */
public class CountNodesEqualToAverageOfSubtree {

    private static final int MASK = (1 << 10) - 1;
    private int res;

    // time O(n), space O(h)
    public int averageOfSubtree(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // 递归二叉树，需要对每个节点求出以它为根结点的子树的平均值。则我们需要知道子树的和和
        // 元素个数，这可以用递归求出。根据题目描述，节点个数不超过 1000 而节点值也不超过
        // 1000 因此我们可以把这两个状态压缩到一个整型数中. 用最低的 10 个比特位存储元素个数
        // 而其他高比特位存储节点和。
        if (root == null) {
            return 0;
        }
        // 递归左右子树
        int left = dfs(root.left), right = dfs(root.right);
        // 用掩码分别提取左右子树的元素个数
        int leftCount = left & MASK, rightCount = right & MASK;
        // 右移 10 位分别提取左右子树的元素和
        int leftSum = left >> 10, rightSum = right >> 10;
        // 计算平均数并判断当前节点是否符合要求
        int sum = root.val + leftSum + rightSum;
        int count = 1 + leftCount + rightCount;
        if (root.val == sum / count) {
            res++;
        }
        return (sum << 10) + count;
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
