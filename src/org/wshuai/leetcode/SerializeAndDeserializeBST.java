package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/25/2017.
 * #0449 https://leetcode.com/problems/serialize-and-deserialize-bst/
 */
public class SerializeAndDeserializeBST {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		preorder(root, sb);
		return sb.substring(0, sb.length() - 1);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data.isEmpty()){
			return null;
		}
		String[] strs = data.split(",");
		int[] preorder = new int[strs.length], inorder = new int[strs.length];
		for(int i = 0; i < preorder.length; i++){
			preorder[i] = Integer.parseInt(strs[i]);
			inorder[i] = preorder[i];
		}
		Arrays.sort(inorder);
		return buildTree(preorder, inorder);
	}

	private void preorder(TreeNode root, StringBuilder sb){
		if(root == null){
			return;
		}
		sb.append(root.val + ",");
		preorder(root.left, sb);
		preorder(root.right, sb);
	}

	// #0105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return dfs(preorder, 0, preorder.length - 1, 0, preorder.length - 1, map);
	}

	private TreeNode dfs(int[] preorder, int i, int j, int m, int n, Map<Integer, Integer> map){
		if(i > j){
			return null;
		}
		TreeNode root = new TreeNode(preorder[i]);
		if(i == j){
			return root;
		}
		int k = map.get(preorder[i]) - m;
		root.left = dfs(preorder, i + 1, i + k, m, m + k - 1, map);
		root.right = dfs(preorder, i + k + 1, j, m + k + 1, n, map);
		return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
