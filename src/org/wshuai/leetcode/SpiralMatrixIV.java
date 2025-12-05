package org.wshuai.leetcode;

/**
 * Created by Wei on 04/19/2025.
 * #2326 https://leetcode.com/problems/spiral-matrix-iv/
 */
public class SpiralMatrixIV {

    // time O(m * n), space O(m * n)
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int row = 0, col = 0, dir = 0;
        int[][] movement = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = -1;
            }
        }
        while (head != null) {
            res[row][col] = head.val;
            int nextRow = row + movement[dir][0], nextCol = col + movement[dir][1];
            if (nextRow < 0
                    || nextCol < 0
                    || nextRow >= m
                    || nextCol >= n
                    || res[nextRow][nextCol] != -1) {
                dir = (dir + 1) % 4;
            }
            row += movement[dir][0];
            col += movement[dir][1];
            head = head.next;
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     */
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
