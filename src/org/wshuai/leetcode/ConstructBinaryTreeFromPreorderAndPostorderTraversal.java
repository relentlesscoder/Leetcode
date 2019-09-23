package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/12/2019.
 * #889 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		int len = pre.length;
		if (len == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		if (len == 1) {
			return root;
		}

		int D = 0;
		for (int i = 0; i < post.length; i++) {
			if (post[i] == pre[1]) {
				D = i + 1;
			}
		}
		// based on the tree traversal property to write the recursive function
		// pre-order is (root)+(left tree pre-order traversal)+(right tree pre-order traversal)
		// post-order is (left tree post-order traversal)+(right tree post-order traversal)+(root)
		root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, D + 1),
				Arrays.copyOfRange(post, 0, D));
		root.right = constructFromPrePost(Arrays.copyOfRange(pre, D + 1, len),
				Arrays.copyOfRange(post, D, len - 1));
		return root;
	}
}
