package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 9/22/19.
 * #817 https://leetcode.com/problems/linked-list-components/
 */
public class LinkedListComponents {
	public int numComponents(LinkedListNode head, int[] G) {
		int count = 0;
		Set<Integer> set = new HashSet<>();
		for(int g: G){
			set.add(g);
		}
		LinkedListNode node = head;
		while(node != null){
			if(set.contains(node.val)){
				count++;
				while(node.next != null && set.contains(node.next.val)){
					node = node.next;
				}
			}
			node = node.next;
		}
		return count;
	}
}
