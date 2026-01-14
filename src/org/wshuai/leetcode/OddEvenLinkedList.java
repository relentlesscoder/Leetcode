package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0328 https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {

    // time O(n), space O(1)
    public ListNode oddEvenList(ListNode head) {
        int id = 1;
        ListNode r1 = new ListNode(-1), // 奇数节点链表 dummy 根结点
				r2 = new ListNode(-1), // 偶数节点链表 dummy 根结点
				odd = r1, // 奇数节点指针
				even = r2; // 偶数节点指针
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (id % 2 == 1) { // 加到奇数链表结尾
                odd.next = head;
                odd = odd.next;
            } else { // 加到偶数链表结尾
                even.next = head;
                even = even.next;
            }
            head = next;
            id++;
        }
        odd.next = r2.next;
        return r1.next;
    }

    /**
     * Definition for singly-linked list.
     **/
    private class ListNode {
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
