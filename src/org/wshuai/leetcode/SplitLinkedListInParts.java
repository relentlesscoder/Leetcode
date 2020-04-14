package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2019.
 * #0725 https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListInParts {
	// time O(n)
	public LinkedListNode[] splitListToParts(LinkedListNode root, int k) {
		LinkedListNode[] res = new LinkedListNode[k];
		if (k == 1) {
			res[0] = root;
			return res;
		}
		if (root == null) {
			return res;
		}
		int count = 0;
		LinkedListNode cur = root, prev = null;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		cur = root;
		int base = count / k, extra = count % k, i = 0, j;
		while (cur != null && i < k) {
			res[i++] = cur;
			j = 0;
			int group = base + (extra-- > 0 ? 1 : 0);
			while (cur != null && j++ < group) {
				prev = cur;
				cur = cur.next;
			}
			prev.next = null;
		}
		return res;
	}
}
