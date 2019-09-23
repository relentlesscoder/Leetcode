package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #23 https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
	public LinkedListNode mergeKLists(LinkedListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		int len = lists.length;
		return mergeKListsUtil(lists, 0, len - 1);
	}

	private LinkedListNode mergeKListsUtil(LinkedListNode[] lists, int p, int q) {
		if (p == q) {
			return lists[p];
		} else {
			int r = p + (q - p) / 2;
			LinkedListNode left = mergeKListsUtil(lists, p, r);
			LinkedListNode right = mergeKListsUtil(lists, r + 1, q);
			return mergeTwoLists(left, right);
		}
	}

	private LinkedListNode mergeTwoLists(LinkedListNode p, LinkedListNode q) {
		LinkedListNode root = new LinkedListNode(-1);
		LinkedListNode curr = root;
		while (p != null || q != null) {
			if (p == null) {
				curr.next = q;
				break;
			}
			if (q == null) {
				curr.next = p;
				break;
			}
			if (p.val < q.val) {
				curr.next = p;
				p = p.next;
			} else {
				curr.next = q;
				q = q.next;
			}
			curr = curr.next;
		}
		return root.next;
	}
}
