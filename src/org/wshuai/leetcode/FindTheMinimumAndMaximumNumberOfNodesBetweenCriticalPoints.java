package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #2058 https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 */
public class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {

    // time O(n), space O(1)
    public int[] nodesBetweenCriticalPoints(ListNode head) {
		// 遍历链表，临界点距离最小值是所有相邻临界点距离的最小值而最大值是
		// 最后一个和第一个临界点的距离。
        int[] res = new int[]{-1, -1};
		// id 表示当前节点的编号，first 是第一和临界点的编号 而 last 是上
		// 一个临界点的编号
        int id = 0, first = -1, last = -1;
		// prev 表示前一个节点，curr 表示当前节点
        ListNode prev = null, curr = head;
        while (curr != null) {
            id++;
            if (prev != null && curr.next != null) {
                int v = curr.val, v1 = prev.val, v2 = curr.next.val;
                if ((v > v1 && v > v2) || (v < v1 && v < v2)) {
                    if (first == -1) { // 发现第一个临界点
                        first = id;
                        last = id;
                    } else {
						// 更新最大距离
                        res[1] = id - first;
						// 更新最小距离
                        res[0] = res[0] == -1 ? id - last : Math.min(res[0], id - last);
                        last = id;
                    }
                }
            }
            prev = curr;
            curr = curr.next;
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     **/
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
