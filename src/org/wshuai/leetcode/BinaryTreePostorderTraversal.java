package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/20/2016.
 * #0145 https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal {

	// time O(n), space O(1)
	public List<Integer> postorderTraversalMorris(TreeNode root) {
        // Modified pre-order Morris traversal #0144
        // Traverse tree in order node -> right -> left and reverse
        // the output left -> right -> node which is post-order
        List<Integer> res = new ArrayList<>();
		TreeNode cur = root, pre = null;
		while (cur != null) {
			if (cur.right == null) {
				res.add(cur.val);
				cur = cur.left;
			} else {
				pre = cur.right;
				while (pre.left != null && pre.left != cur) {
					pre = pre.left;
				}
				if (pre.left == null) {
					res.add(cur.val);
					pre.left = cur;
					cur = cur.right;
				} else {
					pre.left = null;
					cur = cur.left;
				}
			}
		}
		Collections.reverse(res);
		return res;
	}

    // time O(n), space O(n)
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // Traverse in order node -> right -> left
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        // Reverse the order to left -> right -> node
        Collections.reverse(res);
        return res;
    }

    // time O(n), space a: O(log(n)) w: O(n)
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.val);
    }
}
