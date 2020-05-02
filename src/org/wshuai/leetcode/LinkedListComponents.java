package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/22/2019.
 * #0817 https://leetcode.com/problems/linked-list-components/
 */
public class LinkedListComponents {
	// time O(n), space O(n)
	public int numComponents(LinkedListNode head, int[] G) {
		int res = 0;
		Set<Integer> nodes = new HashSet<>();
		for(int g : G){
			nodes.add(g);
		}
		LinkedListNode cur = head;
		boolean flag = false;
		while(cur != null){
			if(nodes.contains(cur.val) && !flag){
				flag = true;
				res++;
			}
			if(!nodes.contains(cur.val) && flag){
				flag = false;
			}
			cur = cur.next;
		}
		return res;
	}
}
