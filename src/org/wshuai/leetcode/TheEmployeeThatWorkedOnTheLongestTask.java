package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2432 https://leetcode.com/problems/the-employee-that-worked-on-the-longest-task/
 */
public class TheEmployeeThatWorkedOnTheLongestTask {

    // time O(n), space O(1)
    public int hardestWorker(int n, int[][] logs) {
        int res = 1_000, time = 0, longest = 0;
        for (int[] log : logs) {
            int curr = log[1] - time;
            if (curr > longest || (curr == longest && log[0] < res)) {
                longest = curr;
                res = log[0];
            }
            time = log[1];
        }
        return res;
    }
}
