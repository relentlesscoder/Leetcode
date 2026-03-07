package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/21/2019.
 * #1261 https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
 */
public class FindElementsInAContaminatedBinaryTree {

    private static class FindElements {

        private final TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
        }

		// time O(log(t)), space O(log(t))
		public boolean find(int target) {
			// 因为二叉树最大高度是 20 所以可以用一个整型来存路径
			int mask = 0;
			int t = target, i = 0;
			while (t > 0) {
				mask = (mask << 1) + (t % 2);
				t -= t % 2 == 0 ? 2 : 1;
				t /= 2;
				i++;
			}
			TreeNode curr = root;
			for (; i > 0 && curr != null; i--) {
				if ((mask & 1) == 1) {
					curr = curr.left;
				} else {
					curr = curr.right;
				}
				mask >>= 1;
			}
			return i == 0 && curr != null;
		}

        // time O(log(t)), space O(log(t))
        public boolean findArray(int target) {
            // 这个二叉树就是一个节点值等于按照层序排列的序号的二叉树。如果是完美二叉树的
            // 话应该是:
            //        0
            //      1   2
            //     3 4 5 6
            //       ...
            // 所以我们可以通过要找的目标值计算节点所在位置然后判断在节点是否存在。
            List<Integer> stack = new ArrayList<>();
            int t = target;
            // 计算根结点到目标节点的路径
            while (t > 0) {
                stack.add(t % 2); // t % 2 决定是左子节点(1)还是右子节点(0)
                t -= t % 2 == 0 ? 2 : 1; // 计算父节点值，等于 0 的话就回到根结点了
                t /= 2;
            }
            // 反向遍历数组 - 从根结点到目标节点
            TreeNode curr = root;
            int i = stack.size() - 1;
            for (; i >= 0 && curr != null; i--) {
                if (stack.get(i) == 1) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            // 如果刚好走完且节点存在则目标值存在
            return i == -1 && curr != null;
        }
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
/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
}
