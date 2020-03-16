package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 03/16/2020.
 * #1382 https://leetcode.com/problems/balance-a-binary-search-tree/
 */
public class BalanceABinarySearchTree {
	// time O(n), space O(n)
	public TreeNode balanceBST(TreeNode root) {
		List<TreeNode> nodes = new ArrayList<>();
		//nodes = morrisTraversal(root);
		inorder(root, nodes);
		return rebalanceBST(nodes, 0, nodes.size() - 1);
	}

	private TreeNode rebalanceBST(List<TreeNode> nodes, int i, int j){
		if(i > j){
			return null;
		}
		int mid = i + (j - i) / 2;
		TreeNode root = nodes.get(mid);
		root.left = rebalanceBST(nodes, i, mid - 1);
		root.right = rebalanceBST(nodes, mid + 1, j);
		return root;
	}

	private void inorder(TreeNode root, List<TreeNode> nodes){
		if(root == null){
			return;
		}
		inorder(root.left, nodes);
		nodes.add(root);
		inorder(root.right, nodes);
	}

    /*
    private List<TreeNode> morrisTraversal(TreeNode root){
        List<TreeNode> res = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.left != null){
                TreeNode pred = cur.left;
                while(pred.right != null && pred.right != cur){
                    pred = pred.right;
                }
                if(pred.right == null){
                    pred.right = cur;
                    cur = cur.left;
                }else{
                    pred.right = null;
                    res.add(cur);
                    cur = cur.right;
                }
            }else{
                res.add(cur);
                cur = cur.right;
            }
        }
        return res;
    }
    */
}
