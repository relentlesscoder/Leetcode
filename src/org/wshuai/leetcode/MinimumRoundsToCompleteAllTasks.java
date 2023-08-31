package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/31/2023.
 * #2244 https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
 */
public class MinimumRoundsToCompleteAllTasks {

    // time O(n), space O(n)
    public int minimumRounds(int[] tasks) {
        int res = 0;
        Map<Integer, Integer> taskMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            taskMap.put(tasks[i], taskMap.getOrDefault(tasks[i], 0) + 1);
        }
        for (int count : taskMap.values()) {
            if (count == 1) {
                return -1;
            }
            res += count / 3;
            if (count % 3 != 0) { // https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/editorial/
                res++;
            }
        }
        return res;
    }
}
