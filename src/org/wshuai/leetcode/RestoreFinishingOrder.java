package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2025.
 * #3668 https://leetcode.com/problems/restore-finishing-order/
 */
public class RestoreFinishingOrder {

    // time O(m + n), space O(m + n)
    public int[] recoverOrder(int[] order, int[] friends) {
        int[] map = new int[101];
        for (int f : friends) {
            map[f] = 1;
        }
        for (int i = 0, j = 0; j < order.length; j++) {
            if (map[order[j]] == 1) {
                friends[i++] = order[j];
            }
        }
        return friends;
    }
}
