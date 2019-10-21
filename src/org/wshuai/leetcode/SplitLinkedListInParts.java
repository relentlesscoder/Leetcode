package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2019.
 * #725 https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListInParts {
	public LinkedListNode[] splitListToParts(LinkedListNode root, int k) {
		int len = 0;
		LinkedListNode curr = root;
		while(curr != null){
			len++;
			curr = curr.next;
		}

		int n = len / k;
		int r = len % k;
		curr = root;
		LinkedListNode[] res = new LinkedListNode[k];
		for(int i = 0; i < k; i++){
			res[i] = curr;
			int c = n;
			if(r > 0){
				r--;
				c++;
			}
			LinkedListNode prev = null;
			while(c > 0){
				prev = curr;
				curr = curr.next;
				c--;
			}
			if(prev != null){
				prev.next = null;
			}
		}
		return res;
	}
}
