package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/26/2016.
 * #0142 https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycleII {
	// time O(n), space O(1)
	public LinkedListNode detectCycle(LinkedListNode head) {
		if(head == null){
			return null;
		}
		LinkedListNode fast = head, slow = head;
		boolean cycle = false;
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow){
				cycle = true;
				break;
			}
		}
		if(!cycle){
			return null;
		}
		// if there is a cycle, the distance of the fast
		// node is twice of that of the second, so reset
		// the fast to the head and let them walk at the
		// same speed, the node they meet at this time
		// is the result.
		fast = head;
		while(fast != slow){
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	// time O(n), space O(n)
	public LinkedListNode detectCycleHashMap(LinkedListNode head) {
		Map<LinkedListNode, Integer> map = new HashMap<>();
		LinkedListNode cur = head;
		int i = 0;
		while(cur != null){
			if(map.containsKey(cur)){
				return cur;
			}
			map.put(cur, i++);
			cur = cur.next;
		}
		return null;
	}
}
