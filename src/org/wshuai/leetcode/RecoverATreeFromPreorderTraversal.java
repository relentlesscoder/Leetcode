package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/11/2019.
 * #1028 https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
 */
public class RecoverATreeFromPreorderTraversal {
	Map<Integer, List<TreeNode>> map;

	public TreeNode recoverFromPreorder(String S) {
		map = new HashMap<>();
		int depth = 0;
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '-') {
				depth++;
			} else {
				int j = i + 1;
				while (j < S.length() && Character.isDigit(S.charAt(j))) {
					j++;
				}
				int val = Integer.parseInt(S.substring(i, j));
				i = j - 1;
				if (!map.containsKey(depth)) {
					map.put(depth, new ArrayList<TreeNode>());
				}
				TreeNode node = new TreeNode(val);
				map.get(depth).add(node);
				if (depth != 0) {
					List<TreeNode> prev = map.get(depth - 1);
					TreeNode parent = prev.get(prev.size() - 1);
					if (parent.left == null) {
						parent.left = node;
					} else {
						parent.right = node;
					}
				}
				depth = 0;
			}
		}
		return map.get(0).get(0);
	}
}
