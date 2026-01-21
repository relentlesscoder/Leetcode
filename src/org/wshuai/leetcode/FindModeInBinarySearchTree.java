package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 04/03/2017.
 * #0501 https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class FindModeInBinarySearchTree {
    private int val = Integer.MIN_VALUE;
    private int maxCount = 0;
    private int count = 0;
    private List<Integer> res = new ArrayList<>();

	// time O(n), space O(n)
    public int[] findModeMorris(TreeNode root) {
		// 对二叉搜索树进行中序遍历，则相同的值会连续出现。统计连续的相同的值出现的次数并
		// 维护当前数量和最大数量以求出所有的众数。题目要求不用多余空间，所以我们这里选用
		// morris 中序遍历二叉树。
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                update(curr.val);
                curr = curr.right;
            } else {
                TreeNode node = curr.left;
                while (node.right != null && node.right != curr) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = curr;
                    curr = curr.left;
                } else {
                    node.right = null;
                    update(curr.val);
                    curr = curr.right;
                }
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

	private void update(int num) {
		if (num == val) {
			count++;
		} else {
			val = num;
			count = 1;
		}
		if (count > maxCount) {
			maxCount = count;
			res.clear();
			res.add(num);
		} else if (count == maxCount) {
			res.add(num);
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
}
