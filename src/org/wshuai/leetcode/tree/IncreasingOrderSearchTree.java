package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/09/2019.
 * #0897 https://leetcode.com/problems/increasing-order-search-tree/
 */
public class IncreasingOrderSearchTree {

	// time O(n), space O(1)
	public TreeNode increasingBST(TreeNode root) {
		// Morris 遍历
		MorrisIterator itr = new MorrisIterator(root);
		TreeNode dummy = new TreeNode(0), pre = dummy;
		while (itr.hasNext()) {
			pre.right = itr.next();
			pre = pre.right;
			pre.left = null;
		}
		return dummy.right;
	}

	private static class MorrisIterator {

		private TreeNode root;

		public MorrisIterator(TreeNode root) {
			this.root = root;
		}

		public boolean hasNext() {
			return root != null;
		}

		public TreeNode next() {
			TreeNode curr = null;
			while (root != null) {
				if (root.left == null) {
					curr = root;
					root = root.right;
					break;
				} else {
					TreeNode pre = root.left;
					while (pre.right != null && pre.right != root) {
						pre = pre.right;
					}
					if (pre.right == null) {
						pre.right = root;
						root = root.left;
					} else {
						pre.right = null;
						curr = root;
						root = root.right;
						break;
					}
				}
			}
			return curr;
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
