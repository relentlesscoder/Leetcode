package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2025.
 * #2074 https://leetcode.com/problems/reverse-nodes-in-even-length-groups/
 */
public class ReverseNodesInEvenLengthGroups {

    // time O(n), space O(1)
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        int group = 1; // group number
        // head - head node for each group,
        // tail - tail node for each group,
        // pre - previous node of each group (last node of last group),
        // cur - current node
        ListNode cur = head, pre = dummyNode, tail = dummyNode;
        while (head != null) {
            int count = 0; // node number of the current group
            while (count < group && cur != null) {
                tail = cur;
                cur = cur.next;
                count++;
            }
            // cur is at head of the next group
            if (count % 2 == 0) { // even group or last group that has even nodes
                // reverse
                ListNode newHead = reverse(head, cur);
                // after reverse:
                // newHead (last node in the group) become head,
                // set the next of pre (last node of last group) to newHead
                pre.next = newHead;
                // head become the last node of the group, set its next point to the
                // head of the next group
                head.next = cur;
                // update pre to point to the head (current last node of the group)
                pre = head;
            } else { // odd group or last group that has odd nodes
                // no need to reverse, update pre to point to the tail of the current group
                pre = tail;
            }
            // update head to point to head of the next group
            head = cur;
            // increase group number by 1
            group++;
        }
        return dummyNode.next;
    }

    private ListNode reverse(ListNode start, ListNode end) {
        ListNode cur = start, pre = null;
        // reverse until pointer reaches end node
        while (cur != end) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // return the new head (last node of the group before reversal)
        return pre;
    }

    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
