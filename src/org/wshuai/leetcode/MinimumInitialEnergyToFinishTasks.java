package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/10/2020.
 * #1665 https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
 */
public class MinimumInitialEnergyToFinishTasks {

    // time O(n*log(n))
    public int minimumEffort(int[][] tasks) {
        // greedily keep max (min - actual) difference
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int res = tasks[0][1], cur = tasks[0][1] - tasks[0][0];
        for(int i = 1; i < tasks.length; i++){
            if(cur < tasks[i][1]){
                res += tasks[i][1] - cur;
                cur = tasks[i][1];
            }
            cur -= tasks[i][0];
        }
        return res;
    }
}
