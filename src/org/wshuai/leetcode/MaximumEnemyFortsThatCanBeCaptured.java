package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2511 https://leetcode.com/problems/maximum-enemy-forts-that-can-be-captured/
 */
public class MaximumEnemyFortsThatCanBeCaptured {

    // time O(n), space O(1)
    public int captureForts(int[] forts) {
        int res = 0;
        int[] last = new int[]{-1, -1, -1}; // denotes the last index of -1, 0, 1
        for (int i = 0; i < forts.length; i++) {
            if (forts[i] == -1) { // if current value is -1, find last 1
                if (last[2] != -1 && (last[0] == -1 || last[0] < last[2])) {
                    res = Math.max(res, i - last[2] - 1);
                }
            } else if (forts[i] == 1) { // if current value is 1, find last -1
                if (last[0] != -1 && (last[2] == -1 || last[2] < last[0])) {
                    res = Math.max(res, i - last[0] - 1);
                }
            }
            last[forts[i] + 1] = i; // update the last index
        }
        return res;
    }
}
