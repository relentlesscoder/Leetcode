package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/21/2019.
 * #0653 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSumIVInputIsABST {

	// time O(n), space O(1)
	public boolean findTargetMorris(TreeNode root, int k) {
		TreeNode curr = root;
		Set<Integer> set = new HashSet<>();
		while (curr != null) {
			if (curr.left == null) {
				if (set.contains(k - curr.val)) {
					return true;
				}
				set.add(curr.val);
				curr = curr.right;
			} else {
				TreeNode pre = curr.left;
				while (pre.right != null && pre.right != curr) {
					pre = pre.right;
				}
				if (pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					pre.right = null;
					if (set.contains(k - curr.val)) {
						return true;
					}
					set.add(curr.val);
					curr = curr.right;
				}
			}
		}
		return false;
	}

	// time O(n), space O(n)
	public boolean findTargetTwoSum(TreeNode root, int k) {
		Deque<TreeNode> left = new ArrayDeque<>(), right = new ArrayDeque<>();
		TreeNode node = root;
		while (node != null) {
			left.push(node);
			node = node.left;
		}
		node = root;
		while (node != null) {
			right.push(node);
			node = node.right;
		}
		for (TreeNode l = left.peek(), r = right.peek(); l.val < r.val; ) {
			int sum = l.val + r.val;
			if (sum == k) {
				return true;
			} else if (sum < k) {
				l = getNext(left, true);
			} else {
				r = getNext(right, false);
			}
		}
		return false;
	}

	private TreeNode getNext(Deque<TreeNode> stack, boolean left) {
		TreeNode curr = left ? stack.pop().right : stack.pop().left;
		while (curr != null) {
			stack.push(curr);
			curr = left ? curr.left : curr.right;
		}
		return stack.peek();
	}

	// time O(n), space O(n)
	public boolean findTargetRecursive(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return dfs(root, k, set);
	}

	private boolean dfs(TreeNode root, int k, Set<Integer> set) {
		if (root == null) {
			return false;
		}
		if (set.contains(k - root.val)) {
			return true;
		}
		set.add(root.val);
		return dfs(root.left, k, set) || dfs(root.right, k, set);
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
