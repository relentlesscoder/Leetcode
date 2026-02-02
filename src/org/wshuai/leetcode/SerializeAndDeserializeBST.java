package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/25/2017.
 * #0449 https://leetcode.com/problems/serialize-and-deserialize-bst/
 */
public class SerializeAndDeserializeBST {

	// time O(n * log(n)), space O(n)
	private static class Codec {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			if (root == null) {
				return "";
			}
			StringBuilder preorder = new StringBuilder();
			TreeNode curr = root;
			while (curr != null) {
				if (curr.left == null) {
					preorder.append(curr.val + ",");
					curr = curr.right;
				} else {
					TreeNode pre = curr.left;
					while (pre.right != null && pre.right != curr) {
						pre = pre.right;
					}
					if (pre.right == null) {
						pre.right = curr;
						preorder.append(curr.val + ",");
						curr = curr.left;
					} else {
						pre.right = null;
						curr = curr.right;
					}
				}
			}
			return preorder.substring(0, preorder.length() - 1);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			if (data.isEmpty()) {
				return null;
			}
			String[] strs = data.split(",");
			int n = strs.length;
			int[] preorder = new int[n], inorder = new int[n];
			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(strs[i]);
				preorder[i] = num;
				inorder[i] = num;
			}
			Arrays.sort(inorder);
			Map<Integer, Integer> inorderMap = new HashMap<>();
			for (int i = 0; i < n; i++) {
				inorderMap.put(inorder[i], i);
			}
			return build(0, n - 1,
					0, n - 1, preorder, inorderMap);
		}

		private TreeNode build(int preLeft, int preRight, int inLeft, int inRight,
							   int[] preorder, Map<Integer, Integer> inorder) {
			// #0105
			if (preLeft > preRight || inLeft > inRight) {
				return null;
			}
			TreeNode root = new TreeNode(preorder[preLeft]);
			int index = inorder.get(preorder[preLeft]);
			root.left = build(preLeft + 1, preLeft + index - inLeft,
					inLeft, index - 1, preorder, inorder);
			root.right = build(preLeft + index - inLeft + 1, preRight,
					index + 1, inRight, preorder, inorder);
			return root;
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
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
}
