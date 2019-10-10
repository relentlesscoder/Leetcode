package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #116 https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PopulatingNextRightPointersInEachNode {
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			root.left.next = root.right;
			root.right.next = root.next == null ? null : root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}
}
