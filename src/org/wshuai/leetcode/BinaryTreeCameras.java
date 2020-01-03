package org.wshuai.leetcode;

/**
 * Created by Wei on 12/10/2019.
 * #968 https://leetcode.com/problems/binary-tree-cameras/
 */
public class BinaryTreeCameras {
	private int NOT_MONITORED = 0;
	private int MONITORED_NOCAM = 1;
	private int MONITORED_WITHCAM = 2;
	private int cameras = 0;

	// https://leetcode.com/problems/binary-tree-cameras/discuss/211966/Super-Clean-Java-solution-beat-100-DFS-O(n)-time-complexity
	public int minCameraCover(TreeNode root) {
		if (root == null) return 0;
		int top = dfs(root);
		return cameras + (top == NOT_MONITORED ? 1 : 0);
	}

	private int dfs(TreeNode root) {
		if (root == null) return MONITORED_NOCAM;
		int left = dfs(root.left);
		int right = dfs(root.right);
		if (left == MONITORED_NOCAM && right == MONITORED_NOCAM) {
			return NOT_MONITORED;
		} else if (left == NOT_MONITORED || right == NOT_MONITORED) {
			cameras++;
			return MONITORED_WITHCAM;
		} else {
			return MONITORED_NOCAM;
		}
	}
}
