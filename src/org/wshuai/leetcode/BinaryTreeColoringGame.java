package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/19.
 * #1145 https://leetcode.com/problems/binary-tree-coloring-game/
 */
public class BinaryTreeColoringGame {
	// good explanation
	// https://leetcode.com/problems/binary-tree-coloring-game/discuss/350692/c%2B%2B0ms-modular-beats-100-(both-time-and-memory)-with-algo-and-image
	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		if(root == null){
			return false;
		}

		if(root.val == x){
			int left = count(root.left);
			int right = count(root.right);
			int parent = n - (left + right + 1);

			return parent > (left + right)
				|| left > (parent + right)
				|| right > (parent + left);
		}

		return btreeGameWinningMove(root.left, n, x)
			|| btreeGameWinningMove(root.right, n, x);
	}

	private int count(TreeNode node){
		if(node == null){
			return 0;
		}
		return count(node.left) + count(node.right) + 1;
	}
}
