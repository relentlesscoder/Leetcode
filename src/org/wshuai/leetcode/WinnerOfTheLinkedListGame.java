package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2026.
 * #3062 https://leetcode.com/problems/winner-of-the-linked-list-game/
 */
public class WinnerOfTheLinkedListGame {

    // time O(n), space O(1)
    public String gameResult(ListNode head) {
        int score = 0;
        while (head != null) {
            ListNode next = head.next;
            if (next != null) {
                // 比较两数大小
                int x = head.val, y = next.val, max = Math.max(x, y);
                // 根据比较的结果更新比分
                if ((max & 1) == 1) {
                    score++;
                } else {
                    score--;
                }
                head = next.next;
            } else {
                break;
            }
        }
        // 正数表示奇数获胜，负数表示偶数获胜，0 表示平局。
        return score == 0 ? "Tie" : (score > 0 ? "Odd" : "Even");
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
