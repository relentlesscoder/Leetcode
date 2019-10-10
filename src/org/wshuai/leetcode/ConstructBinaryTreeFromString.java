package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/2019.
 * #536 https://leetcode.com/problems/construct-binary-tree-from-string/
 */
public class ConstructBinaryTreeFromString {
	public TreeNode str2tree(String s) {
		// handle corner case
		if(s.length() == 0){
			return null;
		}
		// parse root value
		int idx = s.indexOf("(");
		idx = idx == -1 ? s.length() : idx;
		TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, idx)));
		String children = s.substring(idx);
		if(children.length() == 0){
			return root;
		}
		int i = 1;
		int count = 0;
		while(i < s.length()){
			if(s.charAt(i) == '('){
				count++;
			}else if(s.charAt(i) == ')'){
				count--;
				if(count == 0){
					break;
				}
			}
			i++;
		}
		// construct left tree
		root.left = str2tree(s.substring(idx + 1, i));
		// construct right tree
		if(i + 1 != s.length()){
			root.right = str2tree(s.substring(i + 2, s.length() - 1));
		}
		return root;
	}
}
