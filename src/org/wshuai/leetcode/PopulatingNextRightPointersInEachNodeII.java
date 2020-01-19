package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 03/07/2017.
 * #0117 https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class PopulatingNextRightPointersInEachNodeII {
	// time O(n), space O(n)
	public TreeLinkNode connect(TreeLinkNode root) {
		if(root == null){
			return null;
		}
		LinkedList<TreeLinkNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			TreeLinkNode prev = null;
			while(size-- > 0){
				TreeLinkNode cur = queue.pollFirst();
				if(prev != null){
					prev.next = cur;
				}
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
				prev = cur;
			}
		}
		return root;
	}
}
