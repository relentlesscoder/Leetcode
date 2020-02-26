package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/16/2019.
 * #0428 https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 */
public class SerializeAndDeserializeNaryTree {
	// Encodes a tree to a single string.
	public String serialize(NaryTreeNode root) {
		return dfs(root);
	}

	private String dfs(NaryTreeNode node){
		if(node == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(node.val);
		if(node.children.size() > 0){
			sb.append("[");
			for(NaryTreeNode child : node.children){
				sb.append(dfs(child)).append(" ");
			}
			sb.setCharAt(sb.length() - 1, ']');
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public NaryTreeNode deserialize(String data) {
		if(data.length() == 0){
			return null;
		}
		int i = data.indexOf("[");
		if(i == -1){
			return new NaryTreeNode(Integer.parseInt(data), new ArrayList<>());
		}
		Stack<NaryTreeNode> stack = new Stack<>();
		StringBuilder sb = new StringBuilder(data.substring(0, i));
		for(; i < data.length(); i++){
			char c = data.charAt(i);
			if(c == ' ' && data.charAt(i - 1) == ']'){
				continue;
			}
			if(c >= '0' && c <= '9'){
				sb.append(c);
			}else{
				if(c == '['){
					int val = Integer.parseInt(sb.toString());
					stack.push(new NaryTreeNode(val, new ArrayList<NaryTreeNode>()));
				}else{
					if(sb.length() > 0){
						int val = Integer.parseInt(sb.toString());
						stack.peek().children.add(new NaryTreeNode(val, new ArrayList<NaryTreeNode>()));
					}
					if(c == ']'){
						NaryTreeNode cur = stack.pop();
						if(!stack.isEmpty()){
							stack.peek().children.add(cur);
						}else{
							return cur;
						}
					}
				}
				sb = new StringBuilder();
			}
		}
		return null;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
